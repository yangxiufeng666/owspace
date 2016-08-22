package com.github.baby.owspace.model.entity;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/22
 * owspace
 */
public class Event {
    long id;
    String name;

    public Event(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
