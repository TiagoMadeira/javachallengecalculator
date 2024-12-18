package com.example.config;

import com.example.CalculatorMessage;
import com.example.domain.CalculatorRequest;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {


    public KafkaConfig() {
    }



    @Bean
    public NewTopic calculatorRequestTopic(final KafkaConfigProps kafkaConfigProps){
        return TopicBuilder.name(kafkaConfigProps.getTopic())
                .partitions(5)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic calculatorReplyTopic(final KafkaConfigProps kafkaConfigProps){
        return TopicBuilder.name(kafkaConfigProps.getReplyTopic())
                .partitions(5)
                .replicas(1)
                .build();
    }


    @Bean
    public ReplyingKafkaTemplate<String, CalculatorMessage, CalculatorMessage> replyingKafkaTemplate(
            ProducerFactory<String,CalculatorMessage> pf,
            ConcurrentMessageListenerContainer<String, CalculatorMessage> repliesContainer) {
        return new ReplyingKafkaTemplate<>(pf,repliesContainer);
    }


    @Bean
    KafkaTemplate<String, CalculatorMessage> template(ProducerFactory<String, CalculatorMessage> pf) {
        return new KafkaTemplate<>(pf);
    }



    @Bean
    public ConcurrentMessageListenerContainer<String, CalculatorMessage> repliesContainer(
            final KafkaConfigProps kafkaConfigProps,
            ConcurrentKafkaListenerContainerFactory<String, CalculatorMessage> containerFactory,
            KafkaTemplate<String, CalculatorMessage> template) {
        containerFactory.setReplyTemplate(template);
        ConcurrentMessageListenerContainer<String, CalculatorMessage> repliesContainer = containerFactory.createContainer(kafkaConfigProps.getReplyTopic());
        repliesContainer.getContainerProperties().setGroupId("replies");
        return repliesContainer;
    }


}
