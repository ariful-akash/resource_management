package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class AdminComplainRequisitionJson {

    private String type;

    private int solved;

    private int unsolved;

    public AdminComplainRequisitionJson() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public int getUnsolved() {
        return unsolved;
    }

    public void setUnsolved(int unsolved) {
        this.unsolved = unsolved;
    }
}
