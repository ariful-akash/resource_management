/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.controllers.ashifDBop;

import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.RoleDAO;
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

    /**
     * This method insert super admin data like ProVC, VC, Dean, Dept. Head
     *
     * @return
     */
    @RequestMapping(value = "/insert/superadmin")
    public String insertSuperAdmin() {

        return "";
    }

    /**
     * This method insert regular users
     *
     * @return
     */
    @RequestMapping(value = "/insert/users")
    public String insertOtherUsers() {

        return "";
    }
}
