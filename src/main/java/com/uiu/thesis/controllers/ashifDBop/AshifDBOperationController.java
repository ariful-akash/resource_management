package com.uiu.thesis.controllers.ashifDBop;

import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.RoleDAO;
import com.uiu.thesis.models.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ashif
 */
@Controller
public class AshifDBOperationController {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/insert/role")
    public String insertRole() {

        int id = 0;
        Role role = new Role();
        String[] roles = {
            "super admin-rw",
            "super admin-r",
            "database admin",
            "it admin",
            "department admin",
            "school admin",
            "service admin",
            "data operator",
            "user"
        };

        for (String roleString : roles) {

            role.setRole(roleString);
            id = roleDAO.addRole(role);
        }

        if (id != 0) {

            return "success";
        } else {

            return "fail";
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/insert/hr")
    private String insertHumanResource() {

        return "";
    }
}
