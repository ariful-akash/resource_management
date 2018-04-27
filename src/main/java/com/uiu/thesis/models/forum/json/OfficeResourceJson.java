package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uiu.thesis.models.object_resource.OfficeResource;
import com.uiu.thesis.models.object_resource.OfficeResourceType;
import java.util.List;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class OfficeResourceJson extends OfficeResourceType {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    List<OfficeResource> resources;

    public OfficeResourceJson() {
    }

    public List<OfficeResource> getResources() {
        return resources;
    }

    public void setResources(List<OfficeResource> resources) {
        this.resources = resources;
    }

}
