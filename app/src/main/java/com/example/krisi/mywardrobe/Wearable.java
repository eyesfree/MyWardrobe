package com.example.krisi.mywardrobe;

public class Wearable {
    public String name;
    public String type;
    public String state;

    public Wearable() {
    }

    public Wearable(String name, String type, String state) {
        this.name = name;
        this.type = type;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
