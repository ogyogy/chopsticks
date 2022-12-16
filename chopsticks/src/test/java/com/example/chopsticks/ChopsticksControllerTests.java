package com.example.chopsticks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ChopsticksControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        // indexページを確認
        this.mockMvc.perform(get("/"))
                // HTTPステータスコードが200 OKであることを確認
                .andExpect(status().isOk())
                // 正しいViewが返却されているか確認
                .andExpect(view().name("index"))
                // Viewに正しいデフォルトパラメータが設定されているか確認
                .andExpect(model().attribute("player1Name", "Player 1"))
                .andExpect(model().attribute("player2Name", "Player 2"));
    }
}
