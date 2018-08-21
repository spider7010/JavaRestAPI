package com.santhosh.webservices.restfulwebservices.versioning;

public class PersionV2 {

    private Name name;

    public PersionV2() {
    }

    public PersionV2(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
