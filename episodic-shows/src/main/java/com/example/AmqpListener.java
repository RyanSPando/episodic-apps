package com.example;

@Configuration
public class AmqpListener {

    @RabbitListener(queues = "episodic-shows-queue")
    public void receiveMessage(final Message message) {

    }

}