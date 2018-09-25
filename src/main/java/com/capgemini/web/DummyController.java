package com.capgemini.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @RequestMapping("/dummy")
    public String index(){
        return "Hello World";
    }

    @RequestMapping("/dummy/protected")
    public String helloWorldTest(){
        return "Hello World (I should be logged in to see this)";
    }

}