package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.OfficeResourceDAO;
import com.uiu.thesis.models.object_resource.OfficeResource;
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
@RestController(value = "/api/service/office/officeresource")
public class OfficeResourceRestController {

    @Autowired
    private OfficeResourceDAO officeResourceDAO;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     *
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/officeresource",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllOfficeResources() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);

        List<OfficeResource> officeResources = officeResourceDAO.getAllOfficeResources();

        if (officeResources != null && officeResources.size() > 0) {

            try {

                return objectMapper.writeValueAsString(officeResources);
            } catch (JsonProcessingException e) {

                System.err.println(e.toString());
            }
        }

        return "[]";
    }

    /**
     *
     * @param key
     * @param number
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/officeresource/{key}/{number}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getOfficeResource(
            @PathVariable("key") String key,
            @PathVariable("number") String number) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);

        if (number != null && !number.isEmpty()
                && key != null && !key.isEmpty()) {

            List<OfficeResource> officeResources = null;

            switch (key) {

                case "room":

                    officeResources = officeResourceDAO.getOfficeResourcesByRoom(number);
                    break;

                case "floor":

                    officeResources = officeResourceDAO.getOfficeResourcesByFloor(number);
                    break;

                default:
                    break;
            }

            if (officeResources != null) {

                try {

                    return objectMapper.writeValueAsString(officeResources);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
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
            value = "/api/service/office/officeresource/type/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getOfficeResourceByType(@PathVariable("id") long id) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);

        if (id > 0) {

            List<OfficeResource> officeResources = officeResourceDAO.getOfficeResourcesByType(id);
            if (officeResources != null && officeResources.size() > 0) {

                try {

                    return objectMapper.writeValueAsString(officeResources);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }

        return "[]";
    }
}
