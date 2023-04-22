package com.example.filter2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Filter2Application {

    public static void main(String[] args) {
        SpringApplication.run(Filter2Application.class, args);
    }

}
