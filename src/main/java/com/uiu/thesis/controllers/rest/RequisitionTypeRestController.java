package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.services.interfaces.RequisitionTypeService;
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
@RestController(value = "/api/service/office/requisitiontype")
public class RequisitionTypeRestController {

    @Autowired
    private RequisitionTypeService requisitionTypeService;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     *
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/requisitiontype",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)

    public String getAllRequisitions() {

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

        return "[]";
    }
}
