package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

//    ResponseEntity
    @ResponseBody
    @GetMapping("/user")
    public User user(){
//        타입 추론
        var user = new User();
        user.setName("park");
        user.setAddress("fast campus");

        return user;
    }
}
