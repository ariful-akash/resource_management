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
import java.io.IOException;
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
@RestController(value = "/api/service/office/complaint")
public class ComlaintRestController {

    @Autowired
    private ComplaintService complaintService;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     * Returns all the complaints
     *
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllComplaints() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);

        List<Complaint> complaints = complaintService.getAllComplaints();
        if (complaints != null && complaints.size() > 0) {

            try {

                return objectMapper.writeValueAsString(complaints);
            } catch (JsonProcessingException ex) {

                System.err.println(ex.toString());
            }
        }

        return "[]";
    }

    /**
     * Add a new complaint
     *
     * @param complaintJson
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint",
            produces = {"application/json;charset:UTF-8"},
            params = {"complaint"},
            method = RequestMethod.POST)
    public String addComplaint(@RequestParam("complaint") String complaintJson) {

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

    /**
     * Returns complaints(by solver_id / creator-id / type_id)
     *
     * @param id
     * @param key
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/{key}/{id}",
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
     * Returns complaint of specified id
     *
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/{id}",
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

    /**
     * Returns solved/unsolved complaints as specified in {value}
     *
     * @param solved
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complaint/solved/{value}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getComplaintsBySolved(@PathVariable("value") boolean solved) {

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
        return "[]";
    }
}
