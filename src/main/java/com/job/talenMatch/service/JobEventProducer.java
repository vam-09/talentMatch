package com.job.talenMatch.service;

import com.job.talenMatch.dto.JobEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobEventProducer {

    private static final String TOPIC = "job-notification";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public JobEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void publishJobCreatedEvent(JobEvent jobEvent) {
        log.info("Publishing Kafka message to topic '{}' for Job ID: {}", TOPIC, jobEvent.getJobId());

        kafkaTemplate.send(TOPIC, jobEvent).whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Successfully sent message for Job ID: {} [Offset: {}]",
                    jobEvent.getJobId(), result.getRecordMetadata().offset());
            } else {
                log.error("Failed to send Kafka message for Job ID: {}. Error: {}",
                    jobEvent.getJobId(), ex.getMessage());
            }
        });
    }
}
