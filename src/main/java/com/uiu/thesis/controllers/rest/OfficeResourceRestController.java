package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.OfficeResourceDAO;
import com.uiu.thesis.dao.interfaces.OfficeResourceTypeDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.forum.json.OfficeResourceJson;
import com.uiu.thesis.models.object_resource.OfficeResource;
import com.uiu.thesis.models.object_resource.OfficeResourceType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
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
@RestController(value = "/api/service/office/officeresource")
public class OfficeResourceRestController {

    @Autowired
    private OfficeResourceDAO officeResourceDAO;

    @Autowired
    private OfficeResourceTypeDAO officeResourceTypeDAO;

    @Autowired
    private TokenDAO tokenDAO;

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

    @RequestMapping(
            value = "/api/service/office/officeresource",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addOfficeResource(
            HttpSession session,
            @RequestParam("type") String type,
            @RequestParam("floor") String floor,
            @RequestParam("room") String room,
            @RequestParam("quantity") int quantity) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            if (type != null && !type.trim().isEmpty()
                    && floor != null && !floor.trim().isEmpty()
                    && room != null && !room.trim().isEmpty()
                    && quantity > 0) {

                OfficeResourceType resourceType = officeResourceTypeDAO.getOfficeResourceType(type);

                if (resourceType != null) {

                    OfficeResource officeResource = new OfficeResource();

                    officeResource.setRoom(room);
                    officeResource.setQuantity(quantity);
                    officeResource.setFloor(floor);
                    officeResource.setType(resourceType);

                    int value = officeResourceDAO.addOfficeResource(officeResource);
                    if (value != 0) {

                        return "{\"add\":\"true\"}";
                    }
                }
            }
        }

        return "{\"add\":\"false\"}";
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

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/officeresourcetype",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getOfficeResourceTypes(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper obejMapper = new ObjectMapper();

            List<OfficeResourceType> resourceTypes = officeResourceTypeDAO.getAllOfficeResourceTypes();

            if (resourceTypes != null && resourceTypes.size() > 0) {

                try {

                    return obejMapper.writeValueAsString(resourceTypes);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/officeresource/types/all",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllTypes(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();

            List<OfficeResourceType> resourceTypes = officeResourceTypeDAO.getAllOfficeResourceTypes();

            if (resourceTypes != null && resourceTypes.size() > 0) {

                List<OfficeResourceJson> officeResourceJsons = new ArrayList<>();

                for (OfficeResourceType resourceType : resourceTypes) {

                    OfficeResourceJson officeResourceJson = getOfficeResourceJson(resourceType);

                    officeResourceJsons.add(officeResourceJson);
                }

                try {

                    return objectMapper.writeValueAsString(officeResourceJsons);

                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }

        return "[]";
    }

    /**
     *
     * @param resourceType
     * @return
     */
    private OfficeResourceJson getOfficeResourceJson(OfficeResourceType resourceType) {

        if (resourceType == null) {

            return null;
        }

        OfficeResourceJson officeResourceJson = new OfficeResourceJson();

        officeResourceJson.setId(resourceType.getId());
        officeResourceJson.setResourceType(resourceType.getResourceType());
        officeResourceJson.setResources(
                officeResourceDAO.getOfficeResourcesByType(resourceType.getId()));

        return officeResourceJson;
    }
}
