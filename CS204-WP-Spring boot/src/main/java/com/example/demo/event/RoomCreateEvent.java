package com.example.demo.event;

import com.example.demo.model.Room;

public class RoomCreateEvent {
    private Room room;

    public RoomCreateEvent(Object source, Room room) {
        //super(source);
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}
