package com.uiu.thesis.models.requisition;

import com.uiu.thesis.models.user.AccessType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "requisition_types")
public class RequisitionType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "access_type_id")
    private AccessType accessType;

    @OneToMany(mappedBy = "requisitionType", cascade = CascadeType.ALL)
    private List<Requisition> requisitions;

    /**
     * Constructor
     */
    public RequisitionType() {
    }
}
