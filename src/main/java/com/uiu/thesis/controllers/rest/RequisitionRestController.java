package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.services.interfaces.RequisitionService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     *
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllRequisitins() {

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

        return "[]";
    }

    /**
     *
     * @param requisitionJson
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisition",
            params = {"requisition"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addRequisition(
            @RequestParam("requisition") String requisitionJson) {

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

        return "{\"add\":\"false\"}";
    }
}
