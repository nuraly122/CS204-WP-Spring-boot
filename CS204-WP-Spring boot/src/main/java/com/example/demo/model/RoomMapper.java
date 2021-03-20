package com.example.demo.model;

import com.example.demo.model.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();
        room.setID(resultSet.getInt(1));
        room.setName(resultSet.getString(2));
        room.setOwner(resultSet.getString(3));
        return room;
    }
}
