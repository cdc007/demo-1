package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(NumberController.class)
public class NumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetNextNumber() throws Exception {
        for (int i = 0; i <= 200; i++) {
            mockMvc.perform(get("/nextNumber"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(String.valueOf(i)));
        }

        // After 200, it should return null or some indication that all numbers are sent
        mockMvc.perform(get("/nextNumber"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}