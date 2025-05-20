package com.example.Fineance.controllers;

import com.example.Fineance.services.MessageSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageSender messageSender;

    public MessageController(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String msg) {
        messageSender.sendMessage(msg);
        return ResponseEntity.ok("Wiadomość wysłana");
    }
}
