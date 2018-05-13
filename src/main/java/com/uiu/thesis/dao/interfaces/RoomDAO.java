/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.object_resource.Floor;
import com.uiu.thesis.models.object_resource.Room;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface RoomDAO {

    public int addRoom(Room room);

    public int updateRoom(Room room);

    public List<Room> getAllRooms();

    public List<Room> getRoomsByFloor(Floor floor);

    public List<Room> getRoomsByFloor(Long floorId);

    public Room getRoomsById(Long roomId);
}
