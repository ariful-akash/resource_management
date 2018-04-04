/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ashif
 */
@Controller
public class AshifDBOperationHRController {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private AccessTypeDAO accessTypeDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    @Autowired
    private HumanResourceTypeDAO humanResourceTypeDAO;

    /**
     * This methods reruns a HumanResource object set with fake data
     *
     * @return
     */
    private HumanResource getFakeHumanResource(Role role) {

        HumanResource hr = new HumanResource();
        Faker faker = new Faker();
        Set<AccessType> roleAccess = new HashSet<>();

        hr.setFirstName(faker.name().firstName());
        hr.setLastName(faker.name().lastName());
        hr.setEmail(faker.internet().emailAddress());
        hr.setPhone(faker.phoneNumber().cellPhone());
        hr.setPassword("1234".getBytes(StandardCharsets.UTF_8));
//        hr.setRole(role);

        for (AccessType at : role.getAccessTypes()) {

            roleAccess.add(at);
        }

        hr.setAccess(roleAccess);

        return hr;
    }

    /**
     *
     * @param department
     * @return
     */
    private HumanResource getFakeHumanResource(String department) {

        HumanResource hr = new HumanResource();
        Faker faker = new Faker();

        hr.setFirstName(faker.name().firstName());
        hr.setLastName(faker.name().lastName());
        hr.setEmail(faker.internet().emailAddress());
        hr.setPhone(faker.phoneNumber().cellPhone());
        hr.setDepartment(department);
        hr.setPassword("1234".getBytes(StandardCharsets.UTF_8));

        return hr;
    }

    /**
     *
     * @param roleList
     * @return
     */
    private Map<Long, Role> getRoleMap(List<Role> roleList) {

        if (!roleList.isEmpty()) {

            Map<Long, Role> roleMap = new HashMap<Long, Role>();

            for (Role role : roleList) {

                roleMap.put(role.getId(), role);
            }

            return roleMap;
        }

        return null;
    }

    /**
     *
     * @param roleList
     * @return
     */
    private Map<Long, Role> getRoleMap(Set<Role> roleList) {

        if (!roleList.isEmpty()) {

            Map<Long, Role> roleMap = new HashMap<Long, Role>();

            for (Role role : roleList) {

                roleMap.put(role.getId(), role);
            }

            return roleMap;
        }

        return null;
    }

    /**
     *
     * @param hrTypeList
     * @return
     */
    private Map<Long, HumanResourceType> getHRTypeMap(Set<HumanResourceType> hrTypeSet) {

        if (!hrTypeSet.isEmpty()) {

            Map<Long, HumanResourceType> hrTypeMap = new HashMap<Long, HumanResourceType>();

            for (HumanResourceType hrType : hrTypeSet) {

                hrTypeMap.put(hrType.getId(), hrType);
            }

            return hrTypeMap;
        }

        return null;
    }

    /**
     *
     * @param hrTypeList
     * @return
     */
    private Map<Long, HumanResourceType> getHRTypeMap(List<HumanResourceType> hrTypeList) {

        if (!hrTypeList.isEmpty()) {

            Map<Long, HumanResourceType> hrTypeMap = new HashMap<Long, HumanResourceType>();

            for (HumanResourceType hrType : hrTypeList) {

                hrTypeMap.put(hrType.getId(), hrType);
            }

            return hrTypeMap;
        }

        return null;
    }

    /**
     * This method insert super admin data like ProVC, VC, Dean, Dept. Head
     *
     * @return
     */
    @RequestMapping(value = "/insert/superadmin")
    public String insertSuperAdmin() {

        String[] department = {"CSE", "BBA", "EEE", "ECONOMICS"};
        Set<HumanResource> humanResourcesList = new HashSet<>();
        Random random = new Random();
        int randNum;

        Map<Long, Role> roleMap = getRoleMap(roleDAO.getAllRoles());
        List<HumanResourceType> hrTypes = humanResourceTypeDAO.getAllHRType();

        for (HumanResourceType hrt : hrTypes) {

            randNum = random.nextInt(4);
            switch (hrt.getId().intValue()) {

                case 1:

                    HumanResource hr = getFakeHumanResource(department[randNum]);

                    Role role = roleMap.get((long) 1);
                    hr.setAccess(role.getAccessTypes());

                    humanResourcesList.add(hr);
                    hrt.setHumanResources(humanResourcesList);

                    humanResourceTypeDAO.updateHRType(hrt);
                    break;
            }
        }

        return "success";
    }

    @RequestMapping(value = "/insert/users")
    public String insertUser() {

        String[] department = {"CSE", "BBA", "EEE", "ECONOMICS"};
        Random random = new Random();
        int randomNumber;

        for (int i = 1; i <= 10; i++) {

            randomNumber = random.nextInt(4);
            HumanResource hr = getFakeHumanResource(department[randomNumber]);

            humanResourceDAO.addHumanResource(hr);
        }

        return "success";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/map/user/role/access")
    public String mapUserRoleAccess() {

        HumanResource hr;

        /**
         * Map two users with the role "super user-rw" and default access for that role
         */
        Role role = roleDAO.getRoleById((long) 1);

        hr = humanResourceDAO.getHumanResource((long) 102);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        hr = humanResourceDAO.getHumanResource((long) 103);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        roleDAO.updateRole(role);

        /**
         * Map two users with the role "super user-r" and default access for that role
         */
        role = roleDAO.getRoleById((long) 2);

        hr = humanResourceDAO.getHumanResource((long) 104);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        hr = humanResourceDAO.getHumanResource((long) 105);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        roleDAO.updateRole(role);

        /**
         * Map one user with the role "database admin" and default access for that role
         */
        role = roleDAO.getRoleById((long) 3);

        hr = humanResourceDAO.getHumanResource((long) 106);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        roleDAO.updateRole(role);

        /**
         * Map three users with the role "it admin" and default access for that role
         */
        role = roleDAO.getRoleById((long) 4);

        hr = humanResourceDAO.getHumanResource((long) 107);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        hr = humanResourceDAO.getHumanResource((long) 108);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        hr = humanResourceDAO.getHumanResource((long) 109);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        roleDAO.updateRole(role);

        /**
         * Map four users with the role "department admin" and default access for that role
         */
        role = roleDAO.getRoleById((long) 5);

        hr = humanResourceDAO.getHumanResource((long) 110);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        hr = humanResourceDAO.getHumanResource((long) 111);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        hr = humanResourceDAO.getHumanResource((long) 112);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        hr = humanResourceDAO.getHumanResource((long) 113);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        roleDAO.updateRole(role);

        /**
         * Map two users with the role "school admin" and default access for that role
         */
        role = roleDAO.getRoleById((long) 6);

        hr = humanResourceDAO.getHumanResource((long) 114);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        hr = humanResourceDAO.getHumanResource((long) 115);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        roleDAO.updateRole(role);

        /**
         * Map 6 users with the role "service admin" and default access for that role
         */
        role = roleDAO.getRoleById((long) 7);

        for (int i = 116; i <= 121; i++) {

            hr = humanResourceDAO.getHumanResource((long) i);
            hr.setAccess(role.getAccessTypes());

            role.getHumanResources().add(hr);
        }

        roleDAO.updateRole(role);

        /**
         * Map 2 users with the role "data operator" and default access for that role
         */
        role = roleDAO.getRoleById((long) 8);

        hr = humanResourceDAO.getHumanResource((long) 122);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        hr = humanResourceDAO.getHumanResource((long) 123);
        hr.setAccess(role.getAccessTypes());

        role.getHumanResources().add(hr);

        roleDAO.updateRole(role);

        /**
         * Map rest users with the role "user" and default access for that role
         */
        role = roleDAO.getRoleById((long) 9);

        for (int i = 124; i <= 141; i++) {

            hr = humanResourceDAO.getHumanResource((long) i);
            hr.setAccess(role.getAccessTypes());

            role.getHumanResources().add(hr);
        }

        roleDAO.updateRole(role);

        return "success";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/map/user/hrtype")
    public String mapUserHRType() {

        HumanResourceType hrType;
        HumanResource hr;

        /**
         * Map "Vice Chancelor" with a human resource
         */
        hrType = humanResourceTypeDAO.getHumanResourceType((long) 1);

        hr = humanResourceDAO.getHumanResource((long) 102);
        hrType.getHumanResources().add(hr);

        humanResourceTypeDAO.updateHRType(hrType);

        /**
         * Map "Pro Vice Chancelor" with a human resource
         */
        hrType = humanResourceTypeDAO.getHumanResourceType((long) 2);

        hr = humanResourceDAO.getHumanResource((long) 103);
        hrType.getHumanResources().add(hr);

        humanResourceTypeDAO.updateHRType(hrType);

        /**
         * Map rest HR types with rest Human Resources
         */
        int k = 104;
        for (int i = 3; i <= 8; i++) {

            hrType = humanResourceTypeDAO.getHumanResourceType((long) i);

            for (int j = 1; j <= 6; j++) {

                hr = humanResourceDAO.getHumanResource((long) k);
                k++;
                hrType.getHumanResources().add(hr);
            }
            humanResourceTypeDAO.updateHRType(hrType);
        }

        /**
         * Map "Faculty" with 2 human resources
         */
        hrType = humanResourceTypeDAO.getHumanResourceType((long) 4);

        hr = humanResourceDAO.getHumanResource((long) k);
        hrType.getHumanResources().add(hr);

        k++;

        hr = humanResourceDAO.getHumanResource((long) k);
        hrType.getHumanResources().add(hr);

        humanResourceTypeDAO.updateHRType(hrType);

        return "success";
    }

    @RequestMapping(value = "/read/hrtype")
    public String readHRType() {

        List<HumanResourceType> allHRTypes = humanResourceTypeDAO.getAllHRType();

        for (HumanResourceType hrType : allHRTypes) {

            System.out.println(hrType);
            System.out.println(hrType.getHumanResources().size() + "\n\n");

            for (HumanResource hr : hrType.getHumanResources()) {

                System.out.println(hr);
                System.out.println(hr.getAccess() + "\n\n");
            }

            System.out.println("\n\n\n");
        }

        return "success";
    }

    @RequestMapping(value = "/read/user/accessid")
    public String getHumanResourcesByAccess() {

        List<HumanResource> hrList = humanResourceDAO.getHumanResourcesByAccessType((long) 10);

        for (HumanResource humanResource : hrList) {

            System.out.println(humanResource);
        }

        return "success";
    }
}
