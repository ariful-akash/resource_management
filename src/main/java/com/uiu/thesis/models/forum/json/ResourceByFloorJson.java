package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uiu.thesis.models.object_resource.Floor;
import java.util.List;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class ResourceByFloorJson extends Floor {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    private List<ResourceByRoomJson> rooms;

    public ResourceByFloorJson() {
    }

    public List<ResourceByRoomJson> getRooms() {
        return rooms;
    }

    public void setRooms(List<ResourceByRoomJson> rooms) {
        this.rooms = rooms;
    }
}
