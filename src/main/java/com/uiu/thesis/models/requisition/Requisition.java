package com.uiu.thesis.models.requisition;

import com.uiu.thesis.models.user.HumanResource;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "requisitions")
public class Requisition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "requisition_need_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date requisitionNeedDate;

    @Column(name = "requisition_placing_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date requisitionPlacingDate;

    @Column(name = "purpose", length = 1000)
    private String purpose;

    @Column(name = "remarks", length = 1000)
    private String remarks;

    @Column(name = "solved")
    private boolean isSolved;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HumanResource solver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisition_creator_id")
    private HumanResource requisitionCreator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisition_type_id")
    private RequisitionType requisitionType;

    /**
     * Constructor
     */
    public Requisition() {
    }

}
