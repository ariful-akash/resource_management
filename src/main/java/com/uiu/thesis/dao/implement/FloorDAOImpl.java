/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.FloorDAO;
import com.uiu.thesis.models.object_resource.Floor;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ashif
 */
public class FloorDAOImpl implements FloorDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param floor
     * @return
     */
    @Override
    public int addFloor(Floor floor) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(floor);

        return Integer.valueOf(id.toString());
    }

    /**
     *
     * @param floor
     * @return
     */
    @Override
    public int updateFloor(Floor floor) {

        if (floor != null && floor.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();
            try {

                session.update(floor);
                return 1;
            } catch (HibernateException e) {

                return 0;
            }
        }

        return 0;
    }

    /**
     *
     * @param floorId
     * @return
     */
    @Override
    public Floor getFloorById(Long floorId) {

        if (floorId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (Floor) session.get(Floor.class, floorId);
        }

        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Floor> getAllFloor() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Floor";

        @SuppressWarnings("unchecked")
        List<Floor> floors = session.createQuery(hql).list();

        return floors;
    }

}
