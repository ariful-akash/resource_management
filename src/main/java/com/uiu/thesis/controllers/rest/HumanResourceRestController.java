package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceTypeDAO;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.HumanResourceType;
import com.uiu.thesis.services.interfaces.HumanResourceService;
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
@RestController(value = "/api/service/office/hr")
public class HumanResourceRestController {

    @Autowired
    private HumanResourceService humanResourceService;

    @Autowired
    private HumanResourceTypeDAO hrTypeDAO;

    @Autowired
    private AccessTypeDAO accessTypeDAO;

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
     * @param user
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr",
            params = {"user"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addUser(@RequestParam("user") String user) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            HumanResource hr = objectMapper.readValue(user, HumanResource.class);
            if (hr != null
                    && hr.getId() == null
                    && hr.getFirstName() != null
                    && hr.getEmail() != null
                    && hr.getPhone() != null
                    && hr.getDepartment() != null) {

                int value = humanResourceService.addHumanResource(hr);
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
     *
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getUserById(@PathVariable("id") long id) {

        ObjectMapper objectMapper = new ObjectMapper();

        if (id > 0) {

            HumanResource hr = humanResourceService.getHumanResourceById(id);

            if (hr != null) {

                try {

                    return objectMapper.writeValueAsString(hr);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
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
            value = "/api/service/office/hr/type/change",
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

    /**
     *
     * @param change
     * @param hrId
     * @param accessId
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/access/{change}",
            params = {"hr_id", "access_id"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String changeHRAccess(
            @PathVariable("change") String change,
            @RequestParam("hr_id") long hrId,
            @RequestParam("access_id") long accessId) {

        if (hrId > 0 && accessId > 0) {

            int value = 0;

            if (change.equals("add")) {

                value = humanResourceService.addAccess(hrId, accessId);
            } else if (change.equals("remove")) {

                value = humanResourceService.removeAccess(hrId, accessId);
            }

            if (value != 0) {

                return "{\"change\":\"true\"}";
            }
        }

        return "{\"change\":\"false\"}";
    }

    /**
     *
     * @param hr_id
     * @param role_id
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/role/change",
            params = {"hr_id", "role_id"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String changeRole(
            @RequestParam("hr_id") long hr_id,
            @RequestParam("role_id") long role_id) {

        if (hr_id > 0 && role_id > 0) {

            int value = humanResourceService.changeHumanResourceRole(hr_id, role_id);
            if (value != 0) {

                return "{\"change\":\"true\"}";
            }
        }

        return "{\"change\":\"false\"}";
    }

    /**
     *
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/hrtype",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getHRTypes() {

        ObjectMapper objectMapper = new ObjectMapper();

        List<HumanResourceType> hrTypes = hrTypeDAO.getAllHRType();

        if (hrTypes != null && hrTypes.size() > 0) {

            try {

                return objectMapper.writeValueAsString(hrTypes);
            } catch (JsonProcessingException ex) {

                System.err.println(ex.toString());
            }
        }

        return "[]";
    }

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/hrtype",
            params = {"resource_name"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addHRType(@RequestParam("resource_name") String name) {

        if (name != null && !name.isEmpty()) {

            String resourceName = name.toLowerCase();

            HumanResourceType dbHRType = hrTypeDAO.getHumanResourceType(resourceName);

            if (dbHRType == null) {

                HumanResourceType hrType = new HumanResourceType();
                hrType.setResourceName(resourceName);

                int value = hrTypeDAO.addHRType(hrType);

                if (value != 0) {

                    return "{\"add\":\"true\"}";
                }
            }
        }

        return "{\"add\":\"false\"}";
    }
}
