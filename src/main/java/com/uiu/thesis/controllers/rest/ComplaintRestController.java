package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.ComplaintDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.models.forum.json.ComplaintJson;
import com.uiu.thesis.services.interfaces.ComplaintService;
import com.uiu.thesis.services.interfaces.HumanResourceService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController(value = "/api/service/office/complaint")
public class ComplaintRestController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private HumanResourceService humanResourceService;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    @Autowired
    private ComplaintDAO complaintDAO;

    @Autowired
    private TokenDAO tokenDAO;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     * Returns all the complaints
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllComplaints(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setDateFormat(df);

                List<Complaint> complaints = complaintService.getAllComplaints();
                List<ComplaintJson> complaintJsons = new ArrayList<>();

                if (complaints != null && complaints.size() > 0) {

                    for (Complaint complaint : complaints) {

                        ComplaintJson complaintJson = getComplaintJson(complaint);
                        complaintJsons.add(complaintJson);
                    }

                    try {

                        return objectMapper.writeValueAsString(complaintJsons);
                    } catch (JsonProcessingException ex) {

                        System.err.println(ex.toString());
                    }
                }
            }
        }
        return "[]";
    }

    /**
     * Add a new complaint
     *
     * @param complaintJson
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint",
            produces = {"application/json;charset:UTF-8"},
            params = {"complaint"},
            method = RequestMethod.POST)
    public String addComplaint(
            @RequestParam("complaint") String complaintJson,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                ObjectMapper objectMapper = new ObjectMapper();

                try {

                    Complaint complaint = objectMapper.readValue(complaintJson, Complaint.class);
                    if (complaint != null) {

                        int value = complaintService.addNewComplaint(complaint);

                        if (value != 0) {

                            return "{\"add\":\"true\"}";
                        }
                    }
                } catch (IOException e) {

                    System.err.println(e.toString());
                }

                return "{\"add\":\"false\"}";
            }
        }

        return "{}";
    }

    /**
     * Updates a requisition if it is solved
     *
     * @param date
     * @param solverId
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/update",
            params = {"id", "solver_id", "solved_date"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String updateComplaint(
            @RequestParam("id") long id,
            @RequestParam("solver_id") long solverId,
            @RequestParam("solved_date") long date,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            if (id > 0) {

                Complaint complaint = complaintService.getComplaintById(id);

                if (complaint != null
                        && complaint.getComplaintSolvedDate() == null
                        && complaint.isIsSolved() == false) {

                    complaint.setIsSolved(true);
                    complaint.setSolverId(null);
                    complaint.setComplaintSolvedDate(new Date(date));

                    int value = complaintService.updateComplaint(complaint);
                    if (value != 0) {

                        return "{\"update\":\"true\"}";
                    }
                }
            }

            return "{\"update\":\"false\"}";
        }

        return "{}";
    }

    /**
     * Returns complaints(by solver_id / creator-id / type_id)
     *
     * @param id
     * @param key
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/{key}/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getComplaintsByType(
            @PathVariable("id") long id,
            @PathVariable("key") String key,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            if (key != null && !key.isEmpty() && id > 0) {

                List<Complaint> complaints;
                List<ComplaintJson> complaintJsons = new ArrayList<>();

                switch (key) {
                    case "type":

                        complaints = complaintService.getComplaintsByType(id);

                        if (complaints != null && complaints.size() > 0) {

                            for (Complaint complaint : complaints) {

                                ComplaintJson complaintJson = getComplaintJson(complaint);
                                complaintJsons.add(complaintJson);
                            }

                            try {

                                return objectMapper.writeValueAsString(complaintJsons);
                            } catch (JsonProcessingException ex) {

                                System.err.println(ex.toString());
                            }
                        }
                        break;

                    case "solver":

                        complaints = complaintService.getComplaintsBySolver(id);

                        if (complaints != null && complaints.size() > 0) {

                            for (Complaint complaint : complaints) {

                                ComplaintJson complaintJson = getComplaintJson(complaint);
                                complaintJsons.add(complaintJson);
                            }

                            try {

                                return objectMapper.writeValueAsString(complaintJsons);
                            } catch (JsonProcessingException ex) {

                                System.err.println(ex.toString());
                            }
                        }
                        break;

                    case "creator":

                        complaints = complaintService.getComplaintsByCreator(id);

                        if (complaints != null && complaints.size() > 0) {

                            for (Complaint complaint : complaints) {

                                ComplaintJson complaintJson = getComplaintJson(complaint);
                                complaintJsons.add(complaintJson);
                            }

                            try {

                                return objectMapper.writeValueAsString(complaintJsons);
                            } catch (JsonProcessingException ex) {

                                System.err.println(ex.toString());
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        }

        return "[]";
    }

    /**
     * Returns complaint of specified id
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getComplaint(
            @PathVariable("id") Long id,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            if (id != null && id > 0) {

                Complaint complaint = complaintService.getComplaintById(id);
                if (complaint != null) {

                    try {

                        return objectMapper.writeValueAsString(complaint);
                    } catch (JsonProcessingException ex) {

                        System.err.println(ex.toString());
                    }
                }
            }

        }

        return "[]";
    }

    /**
     * Returns solved/unsolved complaints as specified in {value}
     *
     * @param solved
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/solved/{value}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getComplaintsBySolved(
            @PathVariable("value") boolean solved,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setDateFormat(df);

                List<Complaint> complaints = complaintService.getComplaintsSU(solved);

                if (complaints != null && complaints.size() > 0) {

                    try {

                        return objectMapper.writeValueAsString(complaints);
                    } catch (JsonProcessingException e) {

                        System.err.println(e.toString());
                    }
                }
            }
        }
        return "[]";
    }

    /**
     * Returns current user solved/unsolved complaints
     *
     * @param solved
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/own/{solved}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getOwnComplaints(
            @PathVariable("solved") boolean solved,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            List<Complaint> complaints = complaintDAO.getComplaintsByCreator(userId, solved);

            List<ComplaintJson> complaintJsons = new ArrayList<>();

            for (Complaint complaint : complaints) {

                ComplaintJson complaintJson = getComplaintJson(complaint);
                complaintJsons.add(complaintJson);
            }

            try {

                return objectMapper.writeValueAsString(complaintJsons);
            } catch (JsonProcessingException ex) {

                System.err.println(ex.toString());
            }

        }

        return "[]";
    }

    /**
     *
     * @param complaint
     * @return
     */
    private ComplaintJson getComplaintJson(Complaint complaint) {

        ComplaintJson complaintJson = new ComplaintJson();

        if (complaint != null) {

            complaintJson.setComplaintPlacingDate(complaint.getComplaintPlacingDate());
            complaintJson.setComplaintSolvedDate(complaint.getComplaintSolvedDate());
            complaintJson.setCreator(humanResourceService.getHumanResourceById(complaint.getCreatorId()));
            complaintJson.setDescription(complaint.getDescription());
            complaintJson.setId(complaint.getId());
            complaintJson.setIsSolved(complaint.isIsSolved());
            complaintJson.setRemarks(complaint.getRemarks());
            complaintJson.setSolver(humanResourceService.getHumanResourceById(complaint.getSolverId()));
            complaintJson.setType(complaint.getType());

            return complaintJson;
        }

        return null;
    }
}
