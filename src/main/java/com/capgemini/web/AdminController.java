package com.capgemini.web;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebResult;

@RestController
public class AdminController {

    @Secured("ROLE_ADMIN")
    @RequestMapping("/admin")
    public String index(){
        return "Admin page (I should be logged in as admin to see this)";
    }
}