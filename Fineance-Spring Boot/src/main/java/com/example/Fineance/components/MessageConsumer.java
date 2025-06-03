package com.example.Fineance.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = "task_queue")
    public void receiveMessage(String message) {
        System.out.println("Odebrano wiadomość: " + message);
        notifyAdmin(message);
        try {
            Thread.sleep(message.chars().filter(c -> c == '.').count() * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Przetworzono wiadomość: " + message);
    }

    private void notifyAdmin(String message) {
        System.out.println("[ADMIN ALERT] Nowa rejestracja: " + message);
    }
}
