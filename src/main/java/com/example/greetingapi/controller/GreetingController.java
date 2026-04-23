package com.example.greetingapi.controller;

import com.example.greetingapi.dto.GreetingResponse;
import com.example.greetingapi.service.GreetingService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Validated
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public ResponseEntity<GreetingResponse> greet(
            @RequestParam
            @NotBlank(message = "name must not be blank")
            @Size(min = 1, max = 100, message = "name must be between 1 and 100 characters")
            String name) {

        return ResponseEntity.ok(new GreetingResponse(greetingService.greet(name)));
    }
}
