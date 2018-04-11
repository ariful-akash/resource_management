/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.services.interfaces.ComplaintService;
import java.text.SimpleDateFormat;
import java.util.List;
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
@RestController(value = "/service/office/complaint")
public class ComlaintRestController {

    @Autowired
    private ComplaintService complaintService;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     *
     * @param typeId
     * @return
     */
    @RequestMapping(
            value = "/service/office/complaint/type/get",
            params = {"typeid"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getComplaintsByType(@RequestParam("typeid") long typeId) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);

        if (typeId > 0) {

            List<Complaint> complaints = complaintService.getComplaintsByType(typeId);

            if (complaints != null && complaints.size() > 0) {

                try {

                    return objectMapper.writeValueAsString(complaints);
                } catch (JsonProcessingException ex) {

                    System.err.println(ex.toString());
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/service/office/complaint/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getComplaint(@PathVariable("id") Long id) {

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

        return "[]";
    }
}
