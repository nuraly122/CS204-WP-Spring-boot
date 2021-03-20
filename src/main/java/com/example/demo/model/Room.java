package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Room {
    private int ID;
    private String owner;
    private String name;

    public Room() {
        name = "e";
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}