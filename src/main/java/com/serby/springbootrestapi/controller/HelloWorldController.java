package com.serby.springbootrestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller   // to make it a mvc controller
//@ResponseBody // to convert output in htt response json
@RestController //face p toate
public class HelloWorldController {

    //http://localhost:8080/hello-world
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "hello-world";
    }
}
