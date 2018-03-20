package com.uiu.thesis.controllers.ashifDBop;

import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.RoleDAO;
import com.uiu.thesis.models.user.AccessType;
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

    @Autowired
    private AccessTypeDAO accessTypeDAO;

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

            if (id == 0) {

                return "fail";
            }
        }

        return "success";
    }

    /**
     * Insert data in db of "access_type" table
     *
     * @return
     */
    @RequestMapping(value = "/insert/accesstype")
    public String insertAccessType() {

        int id = 0;
        AccessType accessType = new AccessType();
        String[] accesses = {
            "add user",
            "change user access",
            "change user role",
            "add office resource",
            "update office resource",
            "delete office resource",
            "add requisition",
            "handle requisition stationary",
            "handle requisition computer",
            "handle requisition computer part",
            "handle requisition office resource",
            "handle requisition car",
            "add complain",
            "handle complain stationary",
            "handle complain computer",
            "handle complain computer part",
            "handle complain office resource",
            "read office resource"
        };

        for (String access : accesses) {

            accessType.setDescription(access);
            id = accessTypeDAO.addAccessType(accessType);

            if (id == 0) {

                return "fail";
            }
        }

        return "success";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/insert/hr")
    public String insertHumanResource() {

        return "";
    }
}
