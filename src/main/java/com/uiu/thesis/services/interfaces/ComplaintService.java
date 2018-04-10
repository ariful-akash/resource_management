/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.services.interfaces;

import com.uiu.thesis.models.complaint.Complaint;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface ComplaintService {

    public int addNewComplaint(Complaint complaint);

    public List<Complaint> getComplaintsByType(Long id);

    public List<Complaint> getComplaintsByCreator(Long id);
}
