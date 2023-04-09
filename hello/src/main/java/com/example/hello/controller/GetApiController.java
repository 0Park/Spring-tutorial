package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {
    @GetMapping(path="/hello")  // http://localhost:8080/api/get/hello
    public String getHello(){
        return "get Hello";
    }
//    옛날에 쓰던 방식
    @RequestMapping(path="/hi", method= RequestMethod.GET)   //    get/ post /  put/  delete 다 사용됨
    public String hi() {
        return  "hi";
    }

//    http://localhost:8080/api/get/path-variable/{name}
//    name이 계속 변화하는데 그 때마다 각 주소를 만들기는 번거롭다.
    @GetMapping("/path-variable/{name}")
//    name이란 속성에 name을 할당해주었다.
    public String pathVariable(@PathVariable(name="name") String pathName){
        System.out.println("PathVariable :"+pathName);
        return pathName;
    }
// queryparameter->search?q=%EB%B0%B0%EC%88%98%EC%A7%80
// &oq=%EB%B0%B0%EC%88%98%EC%A7%80
// &aqs=chrome..69i57j69i60l3.3265j0j4
// &sourceid=chrome&ie=UTF-8

// query parameter가 검색할때 쓰는 매개변수

//    http://localhost:8080/api/get/query-param?user=park&email=park@gmail.com&age=27
@GetMapping(path="query-param")
    public String queryParam(@RequestParam  Map<String, String> queryParm){
       StringBuilder sb = new StringBuilder();

        queryParm.entrySet().forEach(entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+"= "+entry.getValue()+"\n");
        });

        return sb.toString();
    }

// 미리 명시적으로 정해주는 법(두번째)
    @GetMapping("query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return  name +" "+email +" "+age;
    }

//    user=park&email=park@gmail.com&age=27
//    query-param와 객체와 matching을 시킨다.
//    객체를 받아서하는것이 가장 추천하는방법
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest){
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }


}
