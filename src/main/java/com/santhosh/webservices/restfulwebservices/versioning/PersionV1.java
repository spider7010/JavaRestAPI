package com.santhosh.webservices.restfulwebservices.versioning;

public class PersionV1 {

    private String name;

    public PersionV1(String name) {
        this.name = name;
    }

    public PersionV1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
