package com.code.challenge.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes =  ConnectedService.class )
@TestPropertySource(value = "classpath:application.yml", properties = "cities.file=classpath:assets/cities.text")
public class ConnectedServiceTest {

    @Autowired
    private ConnectedService service;

    @Test
    public void initTest(){
        service.init();
    }

    @Test
    public void isConnectedTestTrue(){
        boolean firstResult = service.isConnected("New York", "Newark");
        boolean secondResult = service.isConnected("New York", "Albany");

        assertThat(service).isInstanceOf(ConnectedService.class);
        assertThat(firstResult).isTrue();
        assertThat(secondResult).isFalse();
    }
}
