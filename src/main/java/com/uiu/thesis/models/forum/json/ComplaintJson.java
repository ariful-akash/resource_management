package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.models.user.HumanResource;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class ComplaintJson extends Complaint {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    private HumanResource creator;

    private HumanResource solver;

    public ComplaintJson() {
    }

    public HumanResource getCreator() {
        return creator;
    }

    public void setCreator(HumanResource creator) {
        this.creator = creator;
    }

    public HumanResource getSolver() {
        return solver;
    }

    public void setSolver(HumanResource solver) {
        this.solver = solver;
    }

}
