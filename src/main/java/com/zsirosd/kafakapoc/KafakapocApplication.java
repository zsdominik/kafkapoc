package com.zsirosd.kafakapoc;

import com.zsirosd.kafakapoc.consumer.KafkaMessageConsumer;
import com.zsirosd.kafakapoc.producer.KafkaMessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafakapocApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafakapocApplication.class, args);

        KafkaMessageProducer producer = context.getBean(KafkaMessageProducer.class);

        Executors.newSingleThreadExecutor().submit(() -> {
            for (int i = 0; i < 1000; i++) {
                producer.sendMessage("test message (" + i + ") on topic1 " + UUID.randomUUID(), "topic1");
                producer.sendMessage("test message (" + i + ") on topic2 " + UUID.randomUUID(), "topic2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}