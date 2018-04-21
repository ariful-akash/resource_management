package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.models.user.HumanResource;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class RequisitionJson extends Requisition {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    private HumanResource creator;

    private HumanResource solver;

    public RequisitionJson() {
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
