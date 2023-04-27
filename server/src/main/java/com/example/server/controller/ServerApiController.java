package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

//    https://openapi.naver.com/v1/search/local.json
//    ?query=%EB%8C%80%EA%B5%AC
//    &display=10
//    &start=1
//    &sort=random
    String query = "김지원";
    String encode = "%EA%B9%80%EC%A7%80%EC%9B%90";
    //String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));
    @GetMapping("/naver")
    public String  naver(){
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","김지원")
                .queryParam("display", 10)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode()
                .build()
                .toUri();

        log.info("uri:  {}",uri);

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","HcBoKJ6Yo1nuRA5mRABx")
                .header("X-Naver-Client-Secret","BIm8fs_laS")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req,String.class);

        return  result.getBody();
    }
    @GetMapping("")
    public User hello(@RequestParam String name, @RequestParam int age ){
        User user = new User(name,age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
            //HttpEntity<String> entity,
                     @RequestBody Req<User> user ,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String customHeader){
        //log.info("req:{}",entity.getBody());
        log.info("userId: {}, userName:{}",userId,userName);
        log.info("authorization: {}, custom: {}",authorization, customHeader);
        log.info("client req : {}",user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );

        response.setRBody(user.getRBody());

        return response;
    }
}
