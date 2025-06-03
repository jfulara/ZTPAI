package com.example.Fineance.controllers;

import com.example.Fineance.services.MessageSender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc(addFilters = false)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageSender messageSender;

    @MockBean
    private com.example.Fineance.services.JwtService jwtService;

    @MockBean
    private com.example.Fineance.security.JwtFilter jwtFilter;

    @Test
    void sendMessage_shouldReturnOk() throws Exception {
        String testMsg = "Test message";
        mockMvc.perform(post("/api/messages")
                .contentType(MediaType.TEXT_PLAIN)
                .content(testMsg))
                .andExpect(status().isOk())
                .andExpect(content().string("Wiadomość wysłana"));
        Mockito.verify(messageSender).sendMessage(testMsg);
    }
}

