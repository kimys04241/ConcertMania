package com.company.assignment.common.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic", groupId = "group")
    public void listen(String userId) {

    }
}
