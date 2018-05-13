package com.uiu.thesis.controllers;

import com.uiu.thesis.dao.interfaces.TokenDAO;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ashif
 */
@Controller
public class ForumController {

    @Autowired
    private TokenDAO tokenDAO;

    @RequestMapping(value = "/forum")
    public String getForum(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (tokenDAO.isTokenExist(token)) {

            return "forum";
        }

        return "index";
    }
}
