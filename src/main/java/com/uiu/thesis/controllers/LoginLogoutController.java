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

    /**
     *
     * @param model
     * @param email
     * @param password
     * @return
     */
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

        return "/index";
    }

    /**
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET, params = {"token"})
    public String doLogout(@RequestParam("token") String token) {

        if (token != null && !token.isEmpty()) {

            int value = tokenDAO.removeToken(token);

            if (value != 0) {

                return "/index";
            }
        }

        return "/home";
    }
}
