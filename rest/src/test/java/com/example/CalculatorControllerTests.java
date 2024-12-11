package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CalculatorControllerTests {

    private final MockMvc mockMvc;
    @Autowired
    public CalculatorControllerTests(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }

    @Test
    public void sumEndpointTest() throws Exception {
        Float x = 2.0f;
        Float y = 2.0f;

        mockMvc
                .perform(MockMvcRequestBuilders.get("/sum?x=" + x + "&y=" + y))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("4.0"));


    }
    @Test
    public void subtractionEndpointTest() throws Exception {
        Float x = 2.0f;
        Float y = 2.0f;

        mockMvc
                .perform(MockMvcRequestBuilders.get("/subtraction?x=" + x + "&y=" + y))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0.0"));

    }
    @Test
    public void multiplicationEndpointTest() throws Exception {
        Float x = 2.0f;
        Float y = 2.0f;

        mockMvc
                .perform(MockMvcRequestBuilders.get("/multiplication?x=" + x + "&y=" + y))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("4.0"));

    }
    @Test
    public void divisionEndpointTest() throws Exception {
        Float x = 2.0f;
        Float y = 2.0f;

        mockMvc
                .perform(MockMvcRequestBuilders.get("/division?x=" + x + "&y=" + y))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1.0"));

    }

}
