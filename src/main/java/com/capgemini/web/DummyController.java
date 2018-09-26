package com.capgemini.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebResult;

@RestController
public class DummyController {

    @RequestMapping("/dummy")
    public String index(){
        return "Hello World (I should be logged in as guest to see this)";
    }
}