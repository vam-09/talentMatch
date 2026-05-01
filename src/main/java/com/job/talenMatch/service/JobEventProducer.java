package com.job.talenMatch.service;

import com.job.talenMatch.dto.JobEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobEventProducer {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publishJobCreatedEvent(JobEvent job) {
        // Logic to wrap the job in an event and publish it
        log.info("Producing JobCreatedEvent for Job ID: {}", job.getJobId());
        eventPublisher.publishEvent(job);
    }

}
