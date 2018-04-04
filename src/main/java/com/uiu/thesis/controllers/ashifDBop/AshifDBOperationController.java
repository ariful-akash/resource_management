package com.uiu.thesis.controllers.ashifDBop;

import com.github.javafaker.Faker;
import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceTypeDAO;
import com.uiu.thesis.dao.interfaces.RoleDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.HumanResourceType;
import com.uiu.thesis.models.user.Role;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController
public class AshifDBOperationController {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    @Autowired
    private HumanResourceTypeDAO humanResourceTypeDAO;

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
            "read office resource",
            "read admin data"
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

//            System.out.println("ID: " + at.getId() + ", Access Type: " + at.getDescription() + ", Roles" + at.getRoles());
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

        List<Role> allRoles = roleDAO.getAllRoles();

        for (Role role : allRoles) {

            System.out.println(role);
            System.out.println(role.getHumanResources().size() + "\n\n");

            for (HumanResource hr : role.getHumanResources()) {

                System.out.println(hr);
                System.out.println(hr.getAccess() + "\n\n");
            }

            System.out.println("\n\n\n");
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

                    roleSpecificAccessTypes.clear();

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

                    break;
                case 2:

                    roleSpecificAccessTypes.clear();

                    roleSpecificAccessTypes.add(allAccessTypes.get(6));
                    roleSpecificAccessTypes.add(allAccessTypes.get(11));
                    roleSpecificAccessTypes.add(allAccessTypes.get(12));
                    roleSpecificAccessTypes.add(allAccessTypes.get(17));
                    roleSpecificAccessTypes.add(allAccessTypes.get(18));

                    role.setAccessTypes(roleSpecificAccessTypes);
                    roleDAO.updateRole(role);

                    break;
                case 3:

                    roleSpecificAccessTypes.clear();

                    roleSpecificAccessTypes.add(allAccessTypes.get(0));
                    roleSpecificAccessTypes.add(allAccessTypes.get(1));
                    roleSpecificAccessTypes.add(allAccessTypes.get(2));
                    roleSpecificAccessTypes.add(allAccessTypes.get(3));
                    roleSpecificAccessTypes.add(allAccessTypes.get(4));
                    roleSpecificAccessTypes.add(allAccessTypes.get(5));
                    roleSpecificAccessTypes.add(allAccessTypes.get(6));
                    roleSpecificAccessTypes.add(allAccessTypes.get(13));

                    role.setAccessTypes(roleSpecificAccessTypes);
                    roleDAO.updateRole(role);

                    break;
                case 4:

                    role.setAccessTypes(roleSpecificAccessTypes);
                    roleDAO.updateRole(role);

                    break;
                case 5:

                    roleSpecificAccessTypes.clear();

                    roleSpecificAccessTypes.add(allAccessTypes.get(8));
                    roleSpecificAccessTypes.add(allAccessTypes.get(11));
                    roleSpecificAccessTypes.add(allAccessTypes.get(6));
                    roleSpecificAccessTypes.add(allAccessTypes.get(12));
                    roleSpecificAccessTypes.add(allAccessTypes.get(14));
                    roleSpecificAccessTypes.add(allAccessTypes.get(17));

                    role.setAccessTypes(roleSpecificAccessTypes);
                    roleDAO.updateRole(role);

                    break;
                case 6:

                    role.setAccessTypes(roleSpecificAccessTypes);
                    roleDAO.updateRole(role);

                    break;
                case 7:

                    roleSpecificAccessTypes.clear();

                    roleSpecificAccessTypes.add(allAccessTypes.get(0));
                    roleSpecificAccessTypes.add(allAccessTypes.get(1));
                    roleSpecificAccessTypes.add(allAccessTypes.get(2));
                    roleSpecificAccessTypes.add(allAccessTypes.get(6));
                    roleSpecificAccessTypes.add(allAccessTypes.get(12));
                    roleSpecificAccessTypes.add(allAccessTypes.get(7));
                    roleSpecificAccessTypes.add(allAccessTypes.get(9));
                    roleSpecificAccessTypes.add(allAccessTypes.get(8));
                    roleSpecificAccessTypes.add(allAccessTypes.get(10));
                    roleSpecificAccessTypes.add(allAccessTypes.get(13));
                    roleSpecificAccessTypes.add(allAccessTypes.get(14));
                    roleSpecificAccessTypes.add(allAccessTypes.get(15));
                    roleSpecificAccessTypes.add(allAccessTypes.get(16));

                    role.setAccessTypes(roleSpecificAccessTypes);
                    roleDAO.updateRole(role);

                    break;
                case 8:

                    roleSpecificAccessTypes.clear();

                    roleSpecificAccessTypes.add(allAccessTypes.get(3));
                    roleSpecificAccessTypes.add(allAccessTypes.get(4));
                    roleSpecificAccessTypes.add(allAccessTypes.get(5));
                    roleSpecificAccessTypes.add(allAccessTypes.get(17));

                    role.setAccessTypes(roleSpecificAccessTypes);
                    roleDAO.updateRole(role);

                    break;
                case 9:

                    roleSpecificAccessTypes.clear();

                    roleSpecificAccessTypes.add(allAccessTypes.get(6));
                    roleSpecificAccessTypes.add(allAccessTypes.get(12));

                    role.setAccessTypes(roleSpecificAccessTypes);
                    roleDAO.updateRole(role);
                    break;
                default:
                    break;
            }
        }

        return "success";
    }

    @RequestMapping(value = "/test/read/role")
    public String testRole() {

        List<Role> roles = roleDAO.getAllRoles();
//        Role role = roleDAO.getRoleById((long) 2);
//        List<AccessType> accessTypes = accessTypeDAO.getAllAccessTypes();

//        System.out.println(role.toString());
        for (Role role : roles) {

            System.out.println(role.toString());
        }

        return "success";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/insert/hr")
    public String insertHumanResource() {

        List<HumanResourceType> allHRTypes = humanResourceTypeDAO.getAllHRType();
        List<Role> allRoles = roleDAO.getAllRoles();
        List<AccessType> allAccessTypes = accessTypeDAO.getAllAccessTypes();

        Set<AccessType> roleAccessTypes = new HashSet<>();

        HumanResource hr = new HumanResource();
        Faker faker = new Faker();

        hr.setFirstName(faker.name().firstName());
        hr.setLastName(faker.name().lastName());
        hr.setEmail(faker.internet().emailAddress());
        hr.setPhone(faker.phoneNumber().cellPhone());
        hr.setPassword("1234".getBytes(StandardCharsets.UTF_8));
        hr.setDepartment("CSE");

//        hr.setResourceType(allHRTypes.get(7));
//        hr.setRole(allRoles.get(0));
        for (AccessType at : allRoles.get(0).getAccessTypes()) {

            roleAccessTypes.add(at);
        }

        hr.setAccess(roleAccessTypes);

        int id = humanResourceDAO.addHumanResource(hr);

        if (id != 0) {

            return "success";
        } else {

            return "fail";
        }
    }
}
