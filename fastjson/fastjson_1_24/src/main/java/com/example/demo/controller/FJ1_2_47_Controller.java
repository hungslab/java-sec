package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class FJ1_2_47_Controller {
    @RequestMapping("/1.2.24")
    public void test(@RequestBody String data) {
        Object object;
        System.out.println("hello");
    }
}
