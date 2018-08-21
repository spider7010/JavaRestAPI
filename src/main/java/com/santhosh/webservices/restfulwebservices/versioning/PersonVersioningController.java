package com.santhosh.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    //URI versioning
    @GetMapping(value = "/v1/person")
    public PersionV1 getPersionV1() {
        return new PersionV1("Santhosh Surimani");
    }

    @GetMapping(value = "/v2/person")
    public PersionV2 getPersionV2() {
        return new PersionV2(new Name("Santhosh", "Surimani"));
    }

    //param versioning
    @GetMapping(value = "/person/param", params = "version=1")
    public PersionV1 getParamV1() {
        return new PersionV1("Santhosh Surimani");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersionV2 getParamV2() {
        return new PersionV2(new Name("Santhosh", "Surimani"));
    }

    //header versioning
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersionV1 getHeaderV1() {
        return new PersionV1("Santhosh Surimani");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersionV2 getHeaderV2() {
        return new PersionV2(new Name("Santhosh", "Surimani"));
    }

    //mime-type versioning
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersionV1 getProducesV1() {
        return new PersionV1("Santhosh Surimani");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersionV2 getProducesV2() {
        return new PersionV2(new Name("Santhosh", "Surimani"));
    }
}
