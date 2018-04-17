package com.uiu.thesis.controllers;

import com.uiu.thesis.dao.interfaces.TokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ashif
 */
@Controller
public class LoginLogoutController {

    @Autowired
    private TokenDAO tokenDAO;

    @RequestMapping(
            value = "/login",
            params = {"email", "password"},
            method = RequestMethod.POST)
    public String doLogin(Model model,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        if (email != null && !email.isEmpty()
                && password != null && !password.isEmpty()) {

            String token = tokenDAO.getToken(email, password);
            model.addAttribute("token", token);
        }

        return "index";
    }
}
