package com.example.greetingapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void greeting_withValidName_returns200() throws Exception {
        mockMvc.perform(get("/api/v1/greeting").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Alice!"));
    }

    @Test
    void greeting_withBlankName_returns400() throws Exception {
        mockMvc.perform(get("/api/v1/greeting").param("name", "   "))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.messages[0]").value("name must not be blank"));
    }

    @Test
    void greeting_withNameExceeding100Chars_returns400() throws Exception {
        String longName = "A".repeat(101);
        mockMvc.perform(get("/api/v1/greeting").param("name", longName))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.messages[0]").value("name must be between 1 and 100 characters"));
    }

    @Test
    void greeting_withMissingName_returns400() throws Exception {
        mockMvc.perform(get("/api/v1/greeting"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.messages[0]").value("Required parameter 'name' is missing"));
    }

    @Test
    void greeting_withMaxLengthName_returns200() throws Exception {
        String maxName = "B".repeat(100);
        mockMvc.perform(get("/api/v1/greeting").param("name", maxName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, " + maxName + "!"));
    }
}
