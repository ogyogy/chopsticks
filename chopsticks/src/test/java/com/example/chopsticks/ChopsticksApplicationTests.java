package com.example.chopsticks;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChopsticksApplicationTests {

	@Autowired
	private ChopsticksController controller;

	@Test
	void contextLoads() {
		// コンテキストがコントローラーを作成していることを確認
		assertThat(controller).isNotNull();
	}

}
