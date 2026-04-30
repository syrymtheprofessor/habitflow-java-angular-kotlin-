package com.dailic.main.controller.events;

import com.dailic.main.kafka.producer.UserRegistrationProducer;
import com.dailic.main.kafka.proto.UserRegisteredProto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/public/events")
@RequiredArgsConstructor
public class KeycloakEventController {

    private final UserRegistrationProducer producer;

    @PostMapping("/keycloak")
    public ResponseEntity<Void> handleEvent(@RequestBody Map<String, Object> event) {
        String type = (String) event.get("type");
        if ("REGISTER".equals(type)) {
            Map<String, Object> details = (Map<String, Object>) event.get("details");
            UserRegisteredProto.UserRegistered proto = UserRegisteredProto.UserRegistered
                    .newBuilder()
                    .setKeycloakId((String) event.get("userId"))
                    .setEmail((String) details.get("email"))
                    .setFirstName((String) details.get("firstName"))
                    .setLastName((String) details.get("lastName"))
                    .build(); // TODO: Keycloak Webhook
            producer.publish(proto);
        }
        return ResponseEntity.ok().build();
    }
}