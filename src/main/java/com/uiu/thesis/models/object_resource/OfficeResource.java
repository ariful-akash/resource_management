/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.models.object_resource;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "office_resources")
public class OfficeResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "floor", nullable = false)
    private String floor;

    @Column(name = "quantity")
    private int quantity;

    /**
     * Constructor
     */
    public OfficeResource() {
    }

    /**
     * Getter and setter
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public OfficeResourceType getResourceType() {
//        return resourceType;
//    }
//
//    public void setResourceType(OfficeResourceType resourceType) {
//        this.resourceType = resourceType;
//    }
    /**
     * Hash Method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.room);
        hash = 37 * hash + Objects.hashCode(this.floor);
        hash = 37 * hash + this.quantity;
//        hash = 37 * hash + Objects.hashCode(this.resourceType);
        return hash;
    }

    /**
     * Equals method
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OfficeResource other = (OfficeResource) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.room, other.room)) {
            return false;
        }
        if (!Objects.equals(this.floor, other.floor)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
//        if (!Objects.equals(this.resourceType, other.resourceType)) {
//            return false;
//        }
        return true;
    }

    /**
     * To string method
     *
     * @return
     */
//    @Override
//    public String toString() {
//        return "OfficeResource{" + "id=" + id + ", room=" + room + ", floor=" + floor + ", quantity=" + quantity + ", resourceType=" + resourceType + '}';
//    }
    @Override
    public String toString() {
        return "OfficeResource{" + "id=" + id + ", room=" + room + ", floor=" + floor + ", quantity=" + quantity + '}';
    }
}
