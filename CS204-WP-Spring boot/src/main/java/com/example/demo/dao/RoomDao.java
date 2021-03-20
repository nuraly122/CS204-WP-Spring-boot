package com.example.demo.dao;

import com.example.demo.model.Room;

import java.util.List;

public interface RoomDao {
    Room getRoomById(int ID);

    List<Room> getAll();
}
