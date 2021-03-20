package com.example.demo.dao;

import com.example.demo.event.RoomCreateEvent;
import com.example.demo.model.Room;
import com.example.demo.model.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class RoomDaoImpl implements RoomDao, ApplicationEventPublisherAware {
    JdbcTemplate jdbcTemplate;
    private ApplicationEventPublisher eventPublisher;

    private final String SQL_FIND_ROOM = "select * from hotel where ID = ?";
    private final String SQL_GET_ALL = "select * from hotel";

    @Autowired
    public RoomDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Room room) {
        System.out.println("RoomDaoImpl.create");
        System.out.println("room = " + room);

        //TODO create user in db

        this.eventPublisher.publishEvent(new RoomCreateEvent(this, room));
    }

    public Room getRoomById(int ID) {
        return jdbcTemplate.queryForObject(SQL_FIND_ROOM, new Object[]{ID}, new RoomMapper());
    }

    public List<Room> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new RoomMapper());
    }

    public void update(int ID, Room room) {
        System.out.println("UserDaoImpl.update");
    }

    public void delete(int ID) {
        System.out.println("RoomDaoImpl.delete id: " + ID);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
