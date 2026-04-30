package com.dailic.main.kafka.producer;

import com.dailic.main.kafka.proto.UserRegisteredProto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void publish(UserRegisteredProto.UserRegistered event) {
        kafkaTemplate.send("user-registered", event.toByteArray());
    }
}