package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.forum.json.RequisitionJson;
import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.services.interfaces.RequisitionService;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
@RestController(value = "/api/service/office/requisition")
public class RequisitionRestController {

    @Autowired
    private RequisitionService requisitionService;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllRequisitins(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setDateFormat(df);

                List<Requisition> requisitions = requisitionService.getAllRequisitions();

                if (requisitions != null && requisitions.size() > 0) {

                    try {

                        return objectMapper.writeValueAsString(requisitions);
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
     * @param requisitionJson
     * @param token
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition",
            params = {"requisition", "token"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addRequisition(
            @RequestParam("requisition") String requisitionJson,
            @RequestParam("token") String token
    ) {

        long userId = tokenDAO.getUserId(token);

        if (userId > 0) {

            ObjectMapper objectMapper = new ObjectMapper();

            if (requisitionJson != null && !requisitionJson.isEmpty()) {

                try {

                    Requisition requisition
                            = objectMapper.readValue(requisitionJson, Requisition.class);

                    int value = requisitionService.addRequisition(requisition);
                    if (value != 0) {

                        return "{\"add\":\"true\"}";
                    }

                } catch (IOException ex) {

                    System.err.println(ex.toString());
                }
            }
        }

        return "{\"add\":\"false\"}";
    }

    /**
     *
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/{id}",
            produces = {"application/json;charset:UTF-8"},
            params = {"token"},
            method = RequestMethod.GET)
    public String getRequisition(
            @PathVariable("id") long id,
            @RequestParam("token") String token
    ) {

        long userId = tokenDAO.getUserId(token);

        if (userId > 0) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            if (id > 0) {

                Requisition requisition = requisitionService.getRequisitionById(id);

                if (requisition != null) {

                    try {

                        return objectMapper.writeValueAsString(requisition);
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
     * @param key
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/{key}/{id}",
            produces = {"application/json;charset:UTF-8"},
            params = {"token"},
            method = RequestMethod.GET)
    public String getRequisitionByKey(
            @PathVariable("key") String key,
            @PathVariable("id") long id,
            @RequestParam("token") String token
    ) {

        long userId = tokenDAO.getUserId(token);
        if (userId > 0) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            if (key != null && !key.isEmpty() && id > 0) {

                List<Requisition> requisitions;
                switch (key) {
                    case "type":

                        requisitions = requisitionService.getRequisitionsByType(id);
                        if (requisitions != null && requisitions.size() > 0) {

                            try {

                                return objectMapper.writeValueAsString(requisitions);
                            } catch (JsonProcessingException ex) {

                                System.err.println(ex.toString());
                            }
                        }
                        break;

                    case "solver":

                        requisitions = requisitionService.getRequisitionsBySolver(id);
                        if (requisitions != null && requisitions.size() > 0) {

                            try {

                                return objectMapper.writeValueAsString(requisitions);
                            } catch (JsonProcessingException e) {

                                System.err.println(e.toString());
                            }
                        }
                        break;

                    case "creator":

                        requisitions = requisitionService.getRequisitionsByCreator(id);
                        if (requisitions != null && requisitions.size() > 0) {

                            try {

                                return objectMapper.writeValueAsString(requisitions);
                            } catch (JsonProcessingException e) {

                                System.err.println(e.toString());
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
     *
     * @param value
     * @param token
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/solved/{value}",
            produces = {"application/json;charset:UTF-8"},
            params = {"token"},
            method = RequestMethod.GET)
    public String getRequisitionsBySolved(
            @PathVariable("value") boolean value,
            @RequestParam("token") String token
    ) {

        long userId = tokenDAO.getUserId(token);
        if (userId > 0) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            List<Requisition> requisitions = requisitionService.getRequisitionsBySU(value);
            if (requisitions != null && requisitions.size() > 0) {

                try {

                    return objectMapper.writeValueAsString(requisitions);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param id
     * @param solverId
     * @param solvedDate
     * @param token
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition/update",
            params = {"id", "solver_id", "solved_date", "token"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String updateRequisition(
            @RequestParam("id") long id,
            @RequestParam("solver_id") long solverId,
            @RequestParam("solved_date") long solvedDate,
            @RequestParam("token") String token
    ) {

        long userId = tokenDAO.getUserId(token);
        if (userId > 0) {

            if (id > 0) {

                Requisition requisition = requisitionService.getRequisitionById(id);

                if (requisition != null
                        && requisition.getRequisitionSolvedDate() == null
                        && requisition.isSolved() == false) {

                    requisition.setSolved(true);
                    requisition.setSolverId(solverId);
                    requisition.setRequisitionSolvedDate(new Date(solvedDate));

                    int value = requisitionService.updateRequisition(requisition);
                    if (value != 0) {

                        return "{\"update\":\"true\"}";
                    }
                }
            }
        }

        return "{\"update\":\"false\"}";
    }

    /**
     * Util method to create Json for Requisition
     *
     * @param requisition
     * @return
     */
    private RequisitionJson getComplaintJson(Requisition requisition) {

        RequisitionJson requisitionJson = new RequisitionJson();

        if (requisition != null) {

            requisitionJson.setCreator(humanResourceDAO.getHumanResource(requisition.getCreatorId()));
            requisitionJson.setCreatorId(requisition.getCreatorId());
            requisitionJson.setId(requisition.getId());
            requisitionJson.setPurpose(requisition.getPurpose());
            requisitionJson.setQuantity(requisition.getQuantity());
            requisitionJson.setRemarks(requisition.getRemarks());
            requisitionJson.setRequisitionNeedDate(requisition.getRequisitionNeedDate());
            requisitionJson.setRequisitionPlacingDate(requisition.getRequisitionPlacingDate());
            requisitionJson.setRequisitionSolvedDate(requisition.getRequisitionSolvedDate());
            requisitionJson.setSolved(requisition.isSolved());
            requisitionJson.setSolver(humanResourceDAO.getHumanResource(requisition.getSolverId()));
            requisitionJson.setType(requisition.getType());

            return requisitionJson;
        }

        return null;
    }
}
