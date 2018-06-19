package com.code.challenge.controller;

import com.code.challenge.exception.RestResponseEntityExceptionHandler;
import com.code.challenge.service.ConnectedService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConnectedController.class)
public class ConnectedControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ConnectedController controller;

    @MockBean
    ConnectedService service;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void isConnectedTrueTest() throws Exception {
        String firstCity = "New York";
        String secondCity = "Boston";
        when(service.isConnected(firstCity, secondCity)).thenReturn(true);
        this.mockMvc.perform(get("/connected?origin=" + firstCity + "&destination=" + secondCity))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("true")));
    }

    @Test
    public void isConnectedFalseTest() throws Exception {
        String firstCity = "New York";
        String secondCity = "Albany";
        when(service.isConnected(firstCity, secondCity)).thenReturn(false);
        this.mockMvc.perform(get("/connected?origin=" + firstCity + "&destination=" + secondCity))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("false")));
    }

    @Test
    public void isConnectedNullValues() throws Exception {
        String firstCity = null;
        String secondCity = null;
        when(service.isConnected(firstCity, secondCity)).thenReturn(false);
        this.mockMvc.perform(get("/connected?origin=" + firstCity))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void isConnectedException() throws Exception {
        String firstCity = "New York";
        String secondCity = "Boston";
        when(service.isConnected(firstCity, secondCity)).thenThrow(new RuntimeException());
        this.mockMvc.perform(get("/connected?origin=" + firstCity + "&destination=" + secondCity))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(is("Server Error")));
    }
}
