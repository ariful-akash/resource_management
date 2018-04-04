/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.object_resource.Floor;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface FloorDAO {

    public int addFloor(Floor floor);

    public int updateFloor(Floor floor);

    public Floor getFloorById(Long floorId);

    public List<Floor> getAllFloors();
}
