package com.step.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static ApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(DemoApplication.class, args);
        System.err.println("context："+context!=null ? context.toString() : "sorry");
    }
}
