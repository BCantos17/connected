package com.code.challenge;

import com.code.challenge.controller.ConnectedController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	@Autowired
	private ConnectedController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}