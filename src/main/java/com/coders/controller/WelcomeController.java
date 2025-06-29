package com.coders.controller;


import com.coders.avro.User;
import com.coders.dto.Customer;
import com.coders.service.KafkaMessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class WelcomeController {

    @Autowired
    private KafkaMessagePublisher publisher;
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            for (int i = 0; i < 10; i++) {
                publisher.sendMessageToTopic(message);

            }

            return ResponseEntity.ok("message published successfully..");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }

    @PostMapping("/publish")
    public void sendEvents(Customer customer) {

        try {
            publisher.sendEventsToTopic(customer);
        } catch (Exception e) {
            logger.info("Error occured: " + e.getMessage());


        }
    }

    @PostMapping("/user")
    public String postUser(@RequestBody UserRequest request) {
        User user = User.newBuilder()
                .setAge(request.age)
                .setName(request.name())
                .setEmail(request.email())
                .build();
        publisher.sendUser(user);
        return "User sent to Kafka topic!";
    }

    public record UserRequest(int age, String name, String email) {}
}
