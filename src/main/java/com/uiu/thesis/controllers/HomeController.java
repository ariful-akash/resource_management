package com.uiu.thesis.controllers;

import com.uiu.thesis.dao.interfaces.OfficeResourceTypeDAO;
import com.uiu.thesis.models.object_resource.OfficeResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ishara Dikkumbura
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/index")
    public String showIndex() {
        return "index";
    }

    @Autowired
    private OfficeResourceTypeDAO orTypeDAO;

    @RequestMapping(value = "/insert/ortype")
    public String addORType() {

        String orTypes[] = {
            "class room chair",
            "canteen chair",
            "faculty chair",
            "study chair (study room, library)",
            "special chair",
            "waiting chair",
            "guest chair",
            "class room table",
            "canteen table",
            "faculty table",
            "study table",
            "special table",
            "presentation table",
            "projector table",
            "computer lab table",
            "cisco lab table",
            "digital lab table",
            "projector",
            "monitor",
            "CPU",
            "faculty shelf",
            "almirah",
            "server",
            "multiplug",
            "book shelf",
            "mouse",
            "keyboard",
            "printer",
            "photocopier",
            "scaner"
        };
        int id = 0;
        OfficeResourceType orType = new OfficeResourceType();
        for (String type : orTypes) {

            orType.setResourceType(type);
            id = orTypeDAO.addOfficeResourceType(orType);
        }

        if (id != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

}
