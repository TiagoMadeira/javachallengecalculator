package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {

    public KafkaConfig() {

    }

    @Bean
    public NewTopic calculatorRequestTopic(){
        return TopicBuilder.name("calculator.requests")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public ReplyingKafkaTemplate<String,Object, Object> replyingKafkaTemplate(
            ProducerFactory<String,Object> pf,
            ConcurrentMessageListenerContainer<String, Object> repliesContainer) {
        return new ReplyingKafkaTemplate<>(pf,repliesContainer);
    }

    @Bean
    KafkaTemplate<String, Object> template(ProducerFactory<String, Object> pf) {
        return new KafkaTemplate<>(pf);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, Object> repliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory,
            KafkaTemplate<String, Object> template) {
        containerFactory.setReplyTemplate(template);
        ConcurrentMessageListenerContainer<String, Object> repliesContainer = containerFactory.createContainer("calculator.requests");
        repliesContainer.getContainerProperties().setGroupId("replies");
        return repliesContainer;
    }


}
