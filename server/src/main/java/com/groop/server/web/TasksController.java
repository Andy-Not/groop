package com.groop.server.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joandy alejo garcia
 */
@RestController
@RequestMapping("/api/auth")
public class TasksController {
@GetMapping("hello")
    public String hello() {
    return "hello wrld";
}
}
