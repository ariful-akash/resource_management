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
     * @param id
     * @param key
     * @return
     */
    @RequestMapping(
            value = "/service/office/complaint/{key}/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getComplaintsByType(
            @PathVariable("id") long id,
            @PathVariable("key") String key) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);

        if (key != null && !key.isEmpty() && id > 0) {

            List<Complaint> complaints;
            switch (key) {
                case "type":

                    complaints = complaintService.getComplaintsByType(id);
                    if (complaints != null && complaints.size() > 0) {

                        try {

                            return objectMapper.writeValueAsString(complaints);
                        } catch (JsonProcessingException ex) {

                            System.err.println(ex.toString());
                        }
                    }
                    break;

                case "solver":

                    complaints = complaintService.getComplaintsBySolver(id);
                    if (complaints != null && complaints.size() > 0) {

                        try {

                            return objectMapper.writeValueAsString(complaints);
                        } catch (JsonProcessingException e) {

                            System.err.println(e.toString());
                        }
                    }
                    break;

                case "creator":

                    complaints = complaintService.getComplaintsByCreator(id);
                    if (complaints != null && complaints.size() > 0) {

                        try {

                            return objectMapper.writeValueAsString(complaints);
                        } catch (JsonProcessingException e) {

                            System.err.println(e.toString());
                        }
                    }
                    break;

                default:
                    break;
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
