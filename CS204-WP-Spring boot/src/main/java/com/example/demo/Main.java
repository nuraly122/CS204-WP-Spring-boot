package com.example.demo;

import com.example.demo.config.SpringConfiguration;
import com.example.demo.dao.RoomDaoImpl;
import com.example.demo.model.Room;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        Menu start = new Menu();
        start.initialize();
        start.menu();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        RoomDaoImpl roomDaoImpl = context.getBean("roomDao", RoomDaoImpl.class);

        Room room = new Room();
        room.setID(1);
        room.setName("Room");
        room.setOwner("Name");
        roomDaoImpl.create(room);
    }
}