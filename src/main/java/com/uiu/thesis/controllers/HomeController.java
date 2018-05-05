package com.uiu.thesis.controllers;

import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceTypeDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.user.HumanResourceType;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ishara Dikkumbura
 */
@Controller
public class HomeController {

    @Autowired
    private HumanResourceTypeDAO hrTypeDAO;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    @RequestMapping(value = "/index")
    public String showIndex(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            return "forum";
        }

        return "index";
    }

    @RequestMapping(value = "/complaints")
    public String showMyComplaintes(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            return "complaints";
        }

        return "index";
    }

    @RequestMapping(value = "/statistics")
    public String showStatistics(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token == null) {

            return "index";
        } else if (tokenDAO.isTokenExist(token)
                && humanResourceDAO.hasAccess(tokenDAO.getUserId(token), (long) 19)) {

            return "statistics";
        }

        return "forum";
    }

    @RequestMapping(value = "/hr")
    public String showHr(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token == null) {

            return "index";
        } else if (humanResourceDAO.hasAccess(tokenDAO.getUserId(token), (long) 19)) {

            return "ManageHumanResources";
        }

        return "forum";
    }

    @RequestMapping(value = "/office")
    public String showOr(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token == null) {

            return "index";
        } else if (humanResourceDAO.hasAccess(tokenDAO.getUserId(token), (long) 1)
                || humanResourceDAO.hasAccess(tokenDAO.getUserId(token), (long) 2)
                || humanResourceDAO.hasAccess(tokenDAO.getUserId(token), (long) 3)) {

            return "ManageOfficeResources";
        }

        return "forum";
    }

    @RequestMapping(value = "/requisitions")
    public String showRequisitions(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            return "requisitions";
        }

        return "index";
    }

    @RequestMapping(value = "/profile")
    public String showProfile(HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            return "profile";
        }

        return "index";
    }

    /**
     * insert data into human_resource_type table
     *
     * @return
     */
    @RequestMapping(value = "/insert/hrtype")
    public String insertHRType() {

        int id = 0;
        String[] hrTypes = {
            "Vice Chancelor",
            "Pro Vice Chancelor",
            "Office Admin",
            "Faculty",
            "Department Head",
            "School Dean",
            "Registrar",
            "Asst. Registrar"
        };

        HumanResourceType hrType = new HumanResourceType();

        for (String hrt : hrTypes) {

            hrType.setResourceName(hrt);
            id = hrTypeDAO.addHRType(hrType);

            if (id == 0) {

                return "fail";
            }
        }

        return "success";

    }

}
