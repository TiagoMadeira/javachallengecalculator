package com.example.integration;

import com.example.RestApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=RestApplication.class)
public class CalculatorControllerTests {

    private final MockMvc mockMvc;

    @Autowired
    public CalculatorControllerTests(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }

    @Test
    public void sumEndpointTest() throws Exception {
        BigDecimal two = new BigDecimal(2);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/sum?x=" + two + "&y=" + two))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{result:\"4\"}"));


    }
    @Test
    public void subtractionEndpointTest() throws Exception {
        BigDecimal two = new BigDecimal(2);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/subtraction?x=" + two + "&y=" + two))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{result:\"0\"}"));

    }
    @Test
    public void multiplicationEndpointTest() throws Exception {
        BigDecimal two = new BigDecimal(2);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/multiplication?x=" + two + "&y=" + two))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{result:\"4\"}"));

    }
    @Test
    public void divisionEndpointTest() throws Exception {
        BigDecimal two = new BigDecimal(2);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/division?x=" + two + "&y=" + two))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{result:\"1\"}"));

    }

}
