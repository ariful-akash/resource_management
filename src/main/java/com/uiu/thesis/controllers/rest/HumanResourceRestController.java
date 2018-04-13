package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.services.interfaces.HumanResourceService;
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
@RestController(value = "/api/service/office/hr")
public class HumanResourceRestController {

    @Autowired
    private HumanResourceService humanResourceService;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     *
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllHumanResources() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);

        List<HumanResource> humanResources = humanResourceService.getHumanResources();

        if (humanResources != null && humanResources.size() > 0) {

            try {

                return objectMapper.writeValueAsString(humanResources);
            } catch (JsonProcessingException ex) {

                System.err.println(ex.toString());
            }
        }
        return "[]";
    }
}
