package com.dauphine.bloggerboxbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Hello World", description = "Hello World endpoints")
public class HelloWorldController {

    @GetMapping("/hello-world")
    @Operation(summary = "Returns Hello World")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/hello")
    @Operation(summary = "Returns a greeting using a query parameter")
    public String helloWithParam(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/hello/{name}")
    @Operation(summary = "Returns a greeting using a path variable")
    public String helloWithPath(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}
