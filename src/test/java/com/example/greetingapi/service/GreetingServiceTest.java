package com.example.greetingapi.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @Test
    void greet_returnsFormattedMessage() {
        assertThat(service.greet("Alice")).isEqualTo("Hello, Alice!");
    }

    @Test
    void greet_withSingleCharName_returnsFormattedMessage() {
        assertThat(service.greet("A")).isEqualTo("Hello, A!");
    }
}
