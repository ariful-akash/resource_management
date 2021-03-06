package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.services.interfaces.RequisitionTypeService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController(value = "/api/service/office/requisitiontype")
public class RequisitionTypeRestController {

    @Autowired
    private RequisitionTypeService requisitionTypeService;

    @Autowired
    private TokenDAO tokenDAO;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     * Returns all the requisitions
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisitiontype",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)

    public String getAllRequisitions(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && !token.isEmpty()) {

            long userId = tokenDAO.getUserId(token);
            if (userId > 0) {

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setDateFormat(df);

                List<RequisitionType> requisitionTypes
                        = requisitionTypeService.getAllRequisitionTypes();

                if (requisitionTypes != null && requisitionTypes.size() > 0) {

                    try {

                        return objectMapper.writeValueAsString(requisitionTypes);
                    } catch (JsonProcessingException ex) {

                        System.err.println(ex.toString());
                    }
                }
            }
        }

        return "[]";
    }

    /**
     * Add a new requisition type
     *
     * @param requisitionTypeJson
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisitiontype",
            params = {"requisition_type"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addRequisition(
            @RequestParam("requisition_type") String requisitionTypeJson,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && !token.isEmpty()) {

            long userId = tokenDAO.getUserId(token);

            if (userId > 0) {

                ObjectMapper objectMapper = new ObjectMapper();
                if (requisitionTypeJson != null) {

                    try {

                        RequisitionType requisitionType
                                = objectMapper.readValue(requisitionTypeJson, RequisitionType.class);

                        int value = requisitionTypeService.addRequisitionType(requisitionType);
                        if (value != 0) {

                            return "{\"add\":\"true\"}";
                        }

                    } catch (IOException ex) {

                        System.err.println(ex.toString());
                    }
                }
            }
        }

        return "{\"add\":\"false\"}";
    }
}
