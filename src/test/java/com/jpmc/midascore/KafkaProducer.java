package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final String topic;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public KafkaProducer(@Value("${general.kafka-topic}") String topic, KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String transactionLine) {
        String[] transactionData = transactionLine.split(", ");
        Transaction tx = new Transaction(Long.parseLong(transactionData[0]), Long.parseLong(transactionData[1]), Float.parseFloat(transactionData[2]));

        // Print/log the transaction so you can see the toString() output in test logs
        logger.info("Producing transaction -> {}", tx);

        // Optional: pause to allow attaching a debugger. Set environment variable PAUSE_ON_TX=true to enable.
        try {
            String pause = System.getenv("PAUSE_ON_TX");
            if (pause != null && pause.equalsIgnoreCase("true")) {
                logger.info("PAUSE_ON_TX is true — sleeping for 10 minutes to allow debugger attach (you can kill the test when done)");
                Thread.sleep(10 * 60 * 1000L);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        kafkaTemplate.send(topic, tx);
    }
}