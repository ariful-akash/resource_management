package com.uiu.thesis.controllers.akashDBop;

import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.dao.interfaces.ComplaintTypeDAO;
import com.uiu.thesis.dao.interfaces.OfficeResourceDAO;
import com.uiu.thesis.dao.interfaces.OfficeResourceTypeDAO;
import com.uiu.thesis.models.complaint.ComplaintType;
import com.uiu.thesis.models.object_resource.OfficeResource;
import com.uiu.thesis.models.object_resource.OfficeResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ariful
 */
@Controller

public class AkashDBOperationController {

    @Autowired
    private OfficeResourceTypeDAO orTypeDAO;

    /**
     * insert OfficeResourceType table
     *
     * @return
     */
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

    /**
     * insert office_resource table with foreign key(office_resource_type table)
     *
     * @return
     */
    @Autowired
    private OfficeResourceDAO orDAO;

    @RequestMapping(value = "/insert/or")
    public String addOfficeResource() {

        int id = 0;
        OfficeResource or = new OfficeResource();

        or.setFloor("2nd");
        or.setQuantity(1);
        or.setRoom("405");
        or.setResourceType(orTypeDAO.getOfficeResourceType((long) 2));
        id = orDAO.addOfficeResource(or);

        if (id != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @Autowired
    private AccessTypeDAO accTypeDAO;

    /**
     * insert complaint table with foreign key(access Type table)
     *
     * @return
     */
    @Autowired
    private ComplaintTypeDAO comTypeDAO;

    @RequestMapping(value = "/insert/comtype")
    private String comTypeDAO() {

        String comTypes[] = {
            "Stationery",
            "Computer",
            "Computer Accessories",
            "Office Resource"
        };

        int id = 0;
        ComplaintType comType = new ComplaintType();
        for (String type : comTypes) {

            comType.setType(type);
            comType.setAccessType(accTypeDAO.getAccessType((long) 2));
            id = comTypeDAO.addComplaintType(comType);

        }

        if (id != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

}
