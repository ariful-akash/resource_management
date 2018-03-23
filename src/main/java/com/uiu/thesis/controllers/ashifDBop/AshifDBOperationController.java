package com.uiu.thesis.controllers.ashifDBop;

import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.RoleDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.Role;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
     * Read access type test (url: /read/accesstype)
     *
     * @return
     */
    @RequestMapping(value = "/read/accesstype")
    public String readAccessTypes() {

        List<AccessType> accessTypes = accessTypeDAO.getAllAccessTypes();
        Iterator accessTypesIterator = accessTypes.iterator();

        while (accessTypesIterator.hasNext()) {

            AccessType at = (AccessType) accessTypesIterator.next();

            System.out.println("ID: " + at.getId() + ", Access Type: " + at.getDescription());
        }

        return "success";
    }

    /**
     * Read role test (url: /read/role)
     *
     * @return
     */
    @RequestMapping(value = "/read/role")
    public String readRoles() {

        List<Role> roles = roleDAO.getAllRoles();
        Iterator roleIterator = roles.iterator();

        while (roleIterator.hasNext()) {

            Role role = (Role) roleIterator.next();
            System.out.println("ID: " + role.getId() + ", Role: " + role.getRole());
        }

        return "success";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/map/roleaccesstype")
    public String mapAccessRole() {

        List<Role> roles = roleDAO.getAllRoles();
        List<AccessType> allAccessTypes = accessTypeDAO.getAllAccessTypes();

        Role role;
        Set<AccessType> roleSpecificAccessTypes = new HashSet<>();

        Iterator rolesIterator = roles.iterator();

        while (rolesIterator.hasNext()) {

            role = (Role) rolesIterator.next();

            switch (role.getId().intValue()) {

                case 1:

                    System.out.println(allAccessTypes.get(0).getDescription());

                    roleSpecificAccessTypes.add(allAccessTypes.get(0));
                    roleSpecificAccessTypes.add(allAccessTypes.get(1));
                    roleSpecificAccessTypes.add(allAccessTypes.get(2));
                    roleSpecificAccessTypes.add(allAccessTypes.get(3));
                    roleSpecificAccessTypes.add(allAccessTypes.get(4));
                    roleSpecificAccessTypes.add(allAccessTypes.get(5));
                    roleSpecificAccessTypes.add(allAccessTypes.get(6));
                    roleSpecificAccessTypes.add(allAccessTypes.get(11));
                    roleSpecificAccessTypes.add(allAccessTypes.get(12));
                    roleSpecificAccessTypes.add(allAccessTypes.get(17));
                    roleSpecificAccessTypes.add(allAccessTypes.get(18));

                    role.setAccessTypes(roleSpecificAccessTypes);

                    roleDAO.updateRole(role);

                    roleSpecificAccessTypes.clear();

                    break;
                case 2:
                    break;
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
