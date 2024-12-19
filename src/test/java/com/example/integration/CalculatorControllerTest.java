package com.example.integration;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yaml")
@EmbeddedKafka
public class CalculatorControllerTest {
    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    private final MockMvc mockMvc;

    @Autowired
    public CalculatorControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void sumEndpointTest() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/sum?x=" + BigDecimal.TWO + "&y=" + BigDecimal.TWO))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{result:\"4\"}"));
    }
    @Test
    public void subtractionEndpointTest() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/subtraction?x=" + BigDecimal.TWO + "&y=" + BigDecimal.TWO))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{result:\"0\"}"));

    }
    @Test
    public void multiplicationEndpointTest() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/multiplication?x=" + BigDecimal.TWO + "&y=" + BigDecimal.TWO))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{result:\"4\"}"));

    }
    @Test
    public void divisionEndpointTest() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/division?x=" + BigDecimal.TWO + "&y=" + BigDecimal.TWO))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{result:\"1\"}"));

    }
    @Test
    public void divisionByZeroTest() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/division?x=" + BigDecimal.TWO + "&y=" + BigDecimal.ZERO))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
