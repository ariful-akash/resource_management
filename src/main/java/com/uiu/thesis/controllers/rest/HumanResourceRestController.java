package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceTypeDAO;
import com.uiu.thesis.dao.interfaces.RoleDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.forum.json.HumanResourceJson;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.HumanResourceType;
import com.uiu.thesis.services.interfaces.HumanResourceService;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    @Autowired
    private HumanResourceTypeDAO humanResourceTypeDAO;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private RoleDAO roleDAO;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     * Return all the users
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllHumanResources(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);

            List<HumanResource> justHumanResources = humanResourceService.getHumanResources();

            //Attaching designation
            List<HumanResource> humanResources = new ArrayList<>();

            for (HumanResource hr : justHumanResources) {

                HumanResourceJson hrJson = new HumanResourceJson();

                hrJson.setId(hr.getId());
                hrJson.setAccess(hr.getAccess());
                hrJson.setDepartment(hr.getDepartment());
                hrJson.setEmail(hr.getEmail());
                hrJson.setFirstName(hr.getFirstName());
                hrJson.setLastName(hr.getLastName());
                hrJson.setImage(hr.getImage());
                hrJson.setPhone(hr.getPhone());
                hrJson.setRoleId(roleDAO.getRoleIdByUser(hr.getId()));
                hrJson.setDesignation(hrTypeDAO.getHRTypeByHRId(hr.getId()).getResourceName());

                humanResources.add(hrJson);
            }

            if (humanResources.size() > 0) {

                try {

                    return objectMapper.writeValueAsString(humanResources);
                } catch (JsonProcessingException ex) {

                    System.err.println(ex.toString());
                }
            }
        }
        return "[]";
    }

    /**
     *
     * @param user
     * @param typeId
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr",
            params = {"user", "type_id"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addUser(
            @RequestParam("user") String user,
            @RequestParam("type_id") long typeId,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();

            try {

                HumanResource hr = objectMapper.readValue(user, HumanResource.class);
                if (hr != null
                        && hr.getId() == null
                        && hr.getFirstName() != null
                        && hr.getEmail() != null
                        && hr.getPhone() != null
                        && hr.getDepartment() != null) {

                    HumanResource hrDB = humanResourceDAO.getHumanResource(hr.getEmail());

                    if (hrDB == null) {

                        hr.setPassword("1234".getBytes());
                        int value = humanResourceService.addHumanResource(hr);
                        if (value != 0) {

                            HumanResourceType hrType = humanResourceTypeDAO.getHumanResourceType(typeId);

                            if (hrType != null) {

                                value = humanResourceDAO.updateHumanResourceType(hr.getId(), typeId);

                                if (value != 0) {

                                    return "{\"add\":\"true\"}";
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {

                System.err.println(e.toString());
            }

            return "{\"add\":\"false\"}";
        }

        return "{}";
    }

    /**
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getUserById(
            @PathVariable("id") long id,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

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
        }

        return "[]";
    }

    @RequestMapping(
            value = "/api/service/office/hr/current",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getLogedInUser(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            long id = tokenDAO.getUserId(token);
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
        }

        return "[]";
    }

    /**
     *
     * @param key
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/{key}/{id}",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getHumanResources(
            @PathVariable("key") String key,
            @PathVariable("id") long id,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

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
     * Add/modify access of user
     *
     * @param change
     * @param hrId
     * @param accessId
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/access/{change}",
            params = {"hr_id", "access_id"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String changeHRAccess(
            @PathVariable("change") String change,
            @RequestParam("hr_id") long hrId,
            @RequestParam("access_id") long accessId,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

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

        return "{}";
    }

    /**
     * Change role of user
     *
     * @param hr_id
     * @param role_id
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/role/change",
            params = {"hr_id", "role_id"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String changeRole(
            @RequestParam("hr_id") long hr_id,
            @RequestParam("role_id") long role_id,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            if (hr_id > 0 && role_id > 0) {

                int value = humanResourceService.changeHumanResourceRole(hr_id, role_id);
                if (value != 0) {

                    return "{\"change\":\"true\"}";
                }
            }

            return "{\"change\":\"false\"}";
        }

        return "{}";
    }

    /**
     * Return all hr types
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/hrtype",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getHRTypes(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            Long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)
                    || humanResourceDAO.hasAccess(userId, (long) 1)
                    || humanResourceDAO.hasAccess(userId, (long) 2)
                    || humanResourceDAO.hasAccess(userId, (long) 3)) {

                ObjectMapper objectMapper = new ObjectMapper();

                List<HumanResourceType> hrTypes = hrTypeDAO.getAllHRType();

                if (hrTypes != null && hrTypes.size() > 0) {

                    try {

                        return objectMapper.writeValueAsString(hrTypes);
                    } catch (JsonProcessingException ex) {

                        System.err.println(ex.toString());
                    }
                }
            }
        }

        return "[]";
    }

    /**
     * Add HR type
     *
     * @param name
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/hrtype",
            params = {"resource_name"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addHRType(
            @RequestParam("resource_name") String name,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

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

        return "{}";
    }

    /**
     * update user's first/last name, email, password
     *
     * @param key
     * @param value
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/change/{key}",
            params = {"value"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String changeinfo(
            @PathVariable("key") String key,
            @RequestParam("value") String value,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            HumanResource humanResource = humanResourceDAO.getHumanResource(tokenDAO.getUserId(token));

            int retVal = 0;

            switch (key) {

                case "firstname":

                    humanResource.setFirstName(value);
                    retVal = humanResourceDAO.updateHumanResource(humanResource);
                    break;

                case "lastname":
                    humanResource.setLastName(value);
                    retVal = humanResourceDAO.updateHumanResource(humanResource);
                    break;

                case "email":
                    humanResource.setEmail(value);
                    retVal = humanResourceDAO.updateHumanResource(humanResource);
                    break;

                case "phone":
                    humanResource.setPhone(value);
                    retVal = humanResourceDAO.updateHumanResource(humanResource);
                    break;

                default:
            }

            if (retVal != 0) {

                return "{\"update\":\"true\"}";
            }
        }

        return "{\"update\":\"false\"}";
    }

    /**
     * Upload image of user
     *
     * @param image
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/change/image",
            method = RequestMethod.POST)
    public String changeImage(
            @RequestParam("image") MultipartFile image,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            HumanResource hr = humanResourceDAO.getHumanResource(userId);

            try {

                hr.setImage(image.getBytes());
                int retVal = humanResourceDAO.updateHumanResource(hr);

                if (retVal != 0) {

                    return "{\"upload\":\"true\"}";
                }
            } catch (IOException ex) {

                System.err.println(ex.toString());
            }
        }

        return "{\"upload\":\"false\"}";
    }

    /**
     *
     * @param oldPass
     * @param newpass
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/hr/change/password",
            produces = {"application/json;charset:UTF-8"},
            params = {"oldpass", "newpass"},
            method = RequestMethod.POST)
    public String changePassword(
            @RequestParam("oldpass") String oldPass,
            @RequestParam("newpass") String newpass,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            HumanResource hr = humanResourceDAO.getHumanResource(userId);

            if (hr != null) {

                String oldDBPass = new String(hr.getPassword());
                if (oldDBPass.equals(oldPass)) {

                    hr.setPassword(newpass.getBytes());

                    int retVal = humanResourceDAO.updateHumanResource(hr);
                    if (retVal != 0) {

                        return "{\"change\":\"true\"}";
                    }
                }
            }
        }

        return "{\"change\":\"false\"}";
    }
}
