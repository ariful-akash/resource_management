package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.RoleDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.user.Role;
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
@RestController(value = "/api/service/user/role")
public class RoleRestController {

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    @Autowired
    private RoleDAO roleDAO;

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/user/role",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getAllRoles(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();

            long userId = tokenDAO.getUserId(token);
            if (humanResourceDAO.hasAccess(userId, (long) 19)) {

                List<Role> roles = roleDAO.getAllRoles();

                if (roles != null && roles.size() > 0) {

                    try {

                        return objectMapper.writeValueAsString(roles);
                    } catch (JsonProcessingException e) {

                        System.err.println(e.toString());
                    }
                }
            }
        }

        return "[]";
    }
}
