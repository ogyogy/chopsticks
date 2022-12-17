package com.example.chopsticks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class ChopsticksControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerRepository repository;

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

    @Test
    public void shouldReturnQueryMessage() throws Exception {
        // resultページを確認
        // GETパラメータを定義
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // NGパターン
        // GETパラメータを渡していない場合400 Bad Requestが帰ること確認
        this.mockMvc.perform(get("/result"))
                .andExpect(status().isBadRequest());
        // OKパターン
        // GETパラメータを渡して200 OKが変えることを確認
        params.add("player1Name", "foo");
        params.add("player2Name", "bar");
        this.mockMvc.perform(get("/result").params(
                params))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnPlayerName() throws Exception {
        // プレイヤー検索のテスト
        // テスト対象のプレイヤーを定義
        String playerName = "Mock";
        Player player = new Player(playerName);
        // プレイヤー検索のスタブを作成
        when(repository.findById(1)).thenReturn(Optional.of(player));
        // プレイヤー検索のスタブを呼び出して設定したプレイヤーの名前が返ることを確認
        assertThat(repository.findById(1).get().getName()).isEqualTo(playerName);
    }
}
