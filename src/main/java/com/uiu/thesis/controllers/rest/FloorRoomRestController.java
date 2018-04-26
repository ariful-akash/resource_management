package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.FloorDAO;
import com.uiu.thesis.dao.interfaces.RoomDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.forum.json.FloorRoomJson;
import com.uiu.thesis.models.object_resource.Floor;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController(value = "/api/service/office/floor")
public class FloorRoomRestController {

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private FloorDAO floorDAO;

    @Autowired
    private RoomDAO roomDAO;

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/office/floor/room/all",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getFloorsWithRoom(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            ObjectMapper objectMapper = new ObjectMapper();

            List<Floor> justFloors = floorDAO.getAllFloors();
            List<FloorRoomJson> floorRoomJsons = new ArrayList<>();

            if (justFloors != null) {

                for (Floor floor : justFloors) {

                    FloorRoomJson floorRoomJson = getFloorRoomJson(floor);

                    floorRoomJsons.add(floorRoomJson);
                }

                try {

                    return objectMapper.writeValueAsString(floorRoomJsons);
                } catch (JsonProcessingException e) {

                    System.err.println(e.toString());
                }
            }
        }

        return "[]";
    }

    private FloorRoomJson getFloorRoomJson(Floor floor) {

        if (floor == null) {

            return null;
        }

        FloorRoomJson floorRoomJson = new FloorRoomJson();

        floorRoomJson.setId(floor.getId());
        floorRoomJson.setFloor(floor.getFloor());
        floorRoomJson.setRooms(roomDAO.getRoomsByFloor(floor.getId()));

        return floorRoomJson;
    }
}
