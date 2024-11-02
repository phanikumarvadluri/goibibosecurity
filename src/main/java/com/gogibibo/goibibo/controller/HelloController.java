package com.gogibibo.goibibo.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "this is security demo"+request.getSession().getId();
    }

    @GetMapping("/hi")
    public String hi() {
        return "this is security demo uuuuuuuuuuuu";
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrf(HttpServletRequest httpServletRequest ) {
        CsrfToken csrf = (CsrfToken)httpServletRequest.getAttribute("_csrf");
        return csrf;
    }

    @GetMapping("/add/{a}/{b}")
    public int add(@PathVariable  int a, @PathVariable  int b)
    {
        return a+b;
    }


    @GetMapping("/add")
    public int addthree(@RequestParam int a, @RequestParam  int b)
    {
        return a+b;
    }
}
