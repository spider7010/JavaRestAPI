package com.santhosh.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/hello-world")
    public String helloWorld(){
        return "Hello Santhosh..!! Welcome to Spring Rest";
    }


    @GetMapping(value = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Welcome to Spring Rest");
    }

    @GetMapping(value = "/hello-world/path-variable/{name}")
    public  HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(value = "/hello-world-internationalized-default")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("good.morning.message",null,locale);
    }

    @GetMapping(value = "/hello-world-internationalized")
    public String helloWorldInternationalized(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
