package com.example.greetingapi.dto;

import java.util.List;

public record ErrorResponse(int status, String error, List<String> messages) {}
