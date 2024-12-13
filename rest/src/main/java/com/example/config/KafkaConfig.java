package com.example.config;

import com.example.domain.CalculatorRequest;
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
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public ReplyingKafkaTemplate<String, CalculatorRequest, CalculatorRequest> replyingKafkaTemplate(
            ProducerFactory<String,CalculatorRequest> pf,
            ConcurrentMessageListenerContainer<String, CalculatorRequest> repliesContainer) {
        return new ReplyingKafkaTemplate<>(pf,repliesContainer);
    }

    @Bean
    KafkaTemplate<String, CalculatorRequest> template(ProducerFactory<String, CalculatorRequest> pf) {
        return new KafkaTemplate<>(pf);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, CalculatorRequest> repliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, CalculatorRequest> containerFactory,
            KafkaTemplate<String, CalculatorRequest> template) {
        containerFactory.setReplyTemplate(template);
        ConcurrentMessageListenerContainer<String, CalculatorRequest> repliesContainer = containerFactory.createContainer("calculator.replies");
        repliesContainer.getContainerProperties().setGroupId("replies");
        return repliesContainer;
    }


}
