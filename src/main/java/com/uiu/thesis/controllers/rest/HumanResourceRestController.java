package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.services.interfaces.HumanResourceService;
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

    /**
     *
     * @param key
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/{key}/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getHumanResources(
            @PathVariable("key") String key,
            @PathVariable("id") long id) {

        if (key != null && !key.isEmpty() && id > 0) {

            ObjectMapper objectMapper = new ObjectMapper();

            List<HumanResource> humanResources = null;

            switch (key) {

                case "type":

                    humanResources = humanResourceService.getHumanResourcesByType(id);
                    break;

                case "role":

                    humanResources = humanResourceService.getHumanResourcesByRole(id);
                    break;

                case "access":

                    humanResources = humanResourceService.getHumanResourcesByAccess(id);
                    break;

                default:
                    break;
            }

            if (humanResources != null && humanResources.size() > 0) {

                try {

                    return objectMapper.writeValueAsString(humanResources);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param hrId
     * @param typeId
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/change/type",
            params = {"hr_id", "type_id"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String changeHRType(
            @RequestParam("hr_id") long hrId,
            @RequestParam("type_id") long typeId) {

        if (hrId > 0 && typeId > 0) {

            int value = humanResourceService.changeHumanResourceType(hrId, typeId);
            if (value != 0) {

                return "{\"change\":\"true\"}";
            }
        }

        return "{\"change\":\"false\"}";
    }
}
