/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @RequestMapping(value = "/insert/role")
    public String insertRole() {

        Role role = new Role();
        role.setRole("data entry operator");

        int id = roleDAO.addRole(role);

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
