package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uiu.thesis.models.object_resource.Floor;
import com.uiu.thesis.models.object_resource.Room;
import java.util.List;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class FloorRoomJson extends Floor {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    private List<Room> rooms;

    public FloorRoomJson() {
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
