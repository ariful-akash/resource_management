package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.user.AccessType;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController(value = "/api/service/user/access")
public class AccessRestController {

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private AccessTypeDAO accessTypeDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/user/access",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllAccess(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                List<AccessType> accessTypes = accessTypeDAO.getAllAccessTypes();

                if (accessTypes != null && accessTypes.size() > 0) {

                    try {

                        return objectMapper.writeValueAsString(accessTypes);
                    } catch (JsonProcessingException e) {

                        System.err.println(e.toString());
                    }
                }
            }
        }

        return "[]";
    }
}
