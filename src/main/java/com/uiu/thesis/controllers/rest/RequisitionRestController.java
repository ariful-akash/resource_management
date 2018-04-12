package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.services.interfaces.RequisitionService;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
