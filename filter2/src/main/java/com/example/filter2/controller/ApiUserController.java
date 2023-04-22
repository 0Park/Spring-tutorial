package com.example.filter2.controller;

import com.example.filter2.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// log를 사용할 수 있게 해주는 annotation
@Slf4j
@RestController
@RequestMapping("/api/temp")
public class ApiUserController {
    @PostMapping("")
    public User user(@RequestBody User user){
        log.info("temp:  { }",user);
        return user;
    }
}
