/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RoomDAO;
import com.uiu.thesis.models.object_resource.Floor;
import com.uiu.thesis.models.object_resource.Room;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ashif
 */
public class RoomDAOImpl implements RoomDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param room
     * @return
     */
    @Override
    public int addRoom(Room room) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(room);

        return Integer.valueOf(id.toString());
    }

    /**
     *
     * @param room
     * @return
     */
    @Override
    public int updateRoom(Room room) {

        if (room != null && room.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();

            try {

                session.update(room);
                return 1;
            } catch (HibernateException e) {

                return 0;
            }
        }

        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Room> getAllRooms() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Room";

        @SuppressWarnings("unchecked")
        List<Room> rooms = session.createQuery(hql).list();

        return rooms;
    }

    @Override
    public List<Room> getRoomsByFloor(Floor floor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param floorId
     * @return
     */
    @Override
    public List<Room> getRoomsByFloor(Long floorId) {

        if (floorId != null && floorId > 0) {

            String sql = "SELECT * FROM room"
                    + " WHERE floor_id = " + floorId;

            return getRoomsBySql(sql);
        }

        return null;
    }

    /**
     *
     * @param roomId
     * @return
     */
    @Override
    public Room getRoomsById(Long roomId) {

        if (roomId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (Room) session.get(Room.class, roomId);
        }

        return null;
    }

    /**
     *
     * @param sql
     * @return
     */
    private List<Room> getRoomsBySql(String sql) {

        if (sql != null && !sql.isEmpty()) {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List results = query.list();

            if (results != null && results.size() > 0) {

                List<Room> rooms = new ArrayList<>();

                for (Object result : results) {

                    Room room = new Room();
                    Map row = (Map) result;

                    BigInteger idInt = (BigInteger) row.get("id");

                    room.setId(idInt.longValue());
                    room.setRoom((String) row.get("room"));

                    rooms.add(room);
                }
            }
        }

        return null;
    }
}
