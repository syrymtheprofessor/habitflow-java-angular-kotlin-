package com.dailic.main.kafka.consumer;

import com.dailic.main.kafka.proto.UserRegisteredProto;
import com.dailic.main.model.User;
import com.dailic.main.repository.UserRepository;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRegistrationConsumer {

    private final UserRepository userRepository;

    // TOLEARN #6 Мониторинг Listener если шина переполнится
    @KafkaListener(topics = "user-registered", groupId = "habitflow-group")
    public void consume(byte[] message) throws InvalidProtocolBufferException {
        UserRegisteredProto.UserRegistered event =
                UserRegisteredProto.UserRegistered.parseFrom(message);

        UUID keycloakId = UUID.fromString(event.getKeycloakId());
        userRepository.findById(keycloakId).orElseGet(() -> {
            User user = new User();
            user.setId(keycloakId);
            user.setEmail(event.getEmail());
            user.setName(event.getFirstName() + " " + event.getLastName());
            return userRepository.save(user);
        });
    }
}