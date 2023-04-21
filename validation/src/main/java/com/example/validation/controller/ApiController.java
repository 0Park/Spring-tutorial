package com.example.validation.controller;

import com.example.validation.dto.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field: "+field.getField());
                System.out.println(message);

                sb.append("field:  "+ field.getField()+" / ");
                sb.append(" message: "+ message+"\n");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        System.out.println(user);
//    기존에 if 를 사용해서 오류를 처리하는 방식
      /*  Pattern p = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");
        Matcher m = p.matcher(user.getPhoneNumber());
//       if를 사용해서 데이터 형식이 잘못 됬을 때 오류를 내는 법
        if(m.find() == false){
//            System.out.println(m.find());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
      */
        return ResponseEntity.ok(user);
    }
}
