package com.code.challenge.controller;

import com.code.challenge.service.ConnectedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConnectedController.class)
public class ConnectedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ConnectedService service;

    @Test
    public void isConnectedTest() throws Exception {
        String firstCity = "New York";
        String secondCity = "Boston";
        when(service.isConnected(firstCity, secondCity)).thenReturn(true);
        this.mockMvc.perform(get("/connected?origin=" + firstCity + "&destination=" + secondCity))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("true")));
    }
}
