package com.zero.springphotoz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringPhotozApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPhotozApplication.class, args);
    }

}
