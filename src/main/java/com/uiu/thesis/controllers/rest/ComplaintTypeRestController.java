package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.complaint.ComplaintType;
import com.uiu.thesis.services.interfaces.ComplaintTypeService;
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
@RestController(value = "/api/service/office/complainttype")
public class ComplaintTypeRestController {

    @Autowired
    private ComplaintTypeService complaintTypeService;

    @Autowired
    private TokenDAO tokenDAO;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     * Returns all the the complaint types
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complainttype",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getComplaintTypes(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            List<ComplaintType> complaintTypes = complaintTypeService.getAllComplaintTypes();

            if (complaintTypes != null && complaintTypes.size() > 0) {

                try {

                    return objectMapper.writeValueAsString(complaintTypes);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }
        return "[]";
    }

    /**
     * Add a new complaint type
     *
     * @param complaintTypeJson
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/complainttype",
            produces = {"application/json;charset;UTF-8"},
            params = {"complaint_type"},
            method = RequestMethod.POST)
    public String addComplaint(
            @RequestParam("complaint_type") String complaintTypeJson) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            ComplaintType complaintType = objectMapper.readValue(complaintTypeJson, ComplaintType.class);

            int value = complaintTypeService.addComplaint(complaintType);
            if (value != 0) {

                return "{\"add\":\"true\"}";
            }

        } catch (IOException e) {

            System.err.println(e.toString());
        }

        return "{\"add\":\"false\"}";
    }
}
