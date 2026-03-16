package com.sakshi.inventory_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public DefaultErrorHandler errorHandler() {

        // retry 3 times with 1 second delay
        FixedBackOff fixedBackOff = new FixedBackOff(1000L, 3);

        return new DefaultErrorHandler((record, exception) -> {
            System.out.println("Kafka message failed after retries: " + record.value());
        }, fixedBackOff);
    }
}