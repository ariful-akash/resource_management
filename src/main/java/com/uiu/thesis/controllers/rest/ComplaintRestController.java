package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.ComplaintDAO;
import com.uiu.thesis.dao.interfaces.ComplaintTypeDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.models.complaint.ComplaintType;
import com.uiu.thesis.models.forum.json.AdminComplainRequisitionJson;
import com.uiu.thesis.models.forum.json.ComplaintJson;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.services.interfaces.ComplaintService;
import com.uiu.thesis.services.interfaces.HumanResourceService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private ComplaintTypeDAO complaintTypeDAO;

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
     *
     * @param year
     * @param month
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/admin/{year}/{month}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAdminComplaint(
            @PathVariable("year") String year,
            @PathVariable("month") String month,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                ObjectMapper objectMapper = new ObjectMapper();

                List<ComplaintType> complaintTypes = complaintTypeDAO.getComplaintTypes();
                List<AdminComplainRequisitionJson> adminCRJs = new ArrayList<>();

                if (year.equals("All")) {

                    for (ComplaintType complaintType : complaintTypes) {

                        AdminComplainRequisitionJson adminCRJ = new AdminComplainRequisitionJson();

                        List<Complaint> complaintsTrue = complaintDAO.getComplaintsByType(complaintType.getId(), true);
                        List<Complaint> complaintsFalse = complaintDAO.getComplaintsByType(complaintType.getId(), false);

                        adminCRJ.setType(complaintType.getType());

                        if (complaintsTrue == null) {

                            adminCRJ.setSolved(0);
                        } else {

                            adminCRJ.setSolved(complaintsTrue.size());
                        }

                        if (complaintsFalse == null) {

                            adminCRJ.setUnsolved(0);
                        } else {

                            adminCRJ.setUnsolved(complaintsFalse.size());
                        }

                        adminCRJs.add(adminCRJ);
                    }
                }

                try {

                    return objectMapper.writeValueAsString(adminCRJs);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/admin/years",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAdminYears(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                ObjectMapper objectMapper = new ObjectMapper();

                List<String> years = complaintDAO.getYears();

                if (years != null && years.size() > 0) {

                    try {

                        return objectMapper.writeValueAsString(years);
                    } catch (JsonProcessingException e) {

                        System.err.println(e.toString());
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
     * @param typeId
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint",
            produces = {"application/json;charset:UTF-8"},
            params = {"complaint", "type_id"},
            method = RequestMethod.POST)
    public String addComplaint(
            @RequestParam("complaint") String complaintJson,
            @RequestParam("type_id") long typeId,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);

            ObjectMapper objectMapper = new ObjectMapper();

            try {

                Complaint complaint = objectMapper.readValue(complaintJson, Complaint.class);
                complaint.setCreatorId(userId);
                complaint.setComplaintPlacingDate(new Date());
                complaint.setType(complaintTypeDAO.getComplaintTypeById(typeId));

                int value = complaintService.addNewComplaint(complaint);

                if (value != 0) {

                    return "{\"add\":\"true\"}";
                }

            } catch (IOException e) {

                System.err.println(e.toString());
            }

            return "{\"add\":\"false\"}";
        }

        return "{}";
    }

    /**
     * Updates a requisition if it is solved
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/update",
            params = {"id"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String updateComplaint(
            @RequestParam("id") long id,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            if (id > 0) {

                Complaint complaint = complaintService.getComplaintById(id);

                if (complaint != null
                        && complaint.getComplaintSolvedDate() == null
                        && complaint.isIsSolved() == false) {

                    complaint.setIsSolved(true);
                    complaint.setSolverId(tokenDAO.getUserId(token));
                    complaint.setComplaintSolvedDate(new Date());

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
     * Returns the complaints that will be handled by current user
     *
     * @param solved
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/incoming/{solved}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getOwnHandleComplaints(
            @PathVariable("solved") boolean solved,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            long userId = tokenDAO.getUserId(token);
            HumanResource user = humanResourceDAO.getHumanResource(userId);

            if (user != null) {

                Set<AccessType> userAllAccess = user.getAccess();
                List<Complaint> complaints = new ArrayList<>();

                long comPart[] = {3, 13, 19, 20, 21, 22};
                long officeRes[] = {4, 5, 6, 7, 8, 9, 10,
                    11, 12, 14, 15, 16, 17, 18, 23, 24, 25,
                    26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};

                for (AccessType userAcces : userAllAccess) {

                    int accessId = Integer.valueOf(userAcces.getId().toString());

                    switch (accessId) {

                        case 14:

                            complaints.addAll(complaintDAO.getComplaintsByType((long) 1, solved));
                            break;
                        case 15:

                            complaints.addAll(complaintDAO.getComplaintsByType((long) 2, solved));
                            break;
                        case 16:

                            for (long value : comPart) {

                                complaints.addAll(complaintDAO.getComplaintsByType(value, solved));
                            }
                            break;
                        case 17:

                            for (long value : officeRes) {

                                complaints.addAll(complaintDAO.getComplaintsByType(value, solved));
                            }
                            break;
                        default:
                    }
                }

                List<ComplaintJson> complaintJsons = new ArrayList<>();

                for (Complaint complaint : complaints) {

                    ComplaintJson complaintJson = getComplaintJson(complaint);

                    complaintJsons.add(complaintJson);
                }

                try {

                    return objectMapper.writeValueAsString(complaintJsons);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
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
