package com.example.config;

import com.example.CalculatorMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

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
            ConcurrentKafkaListenerContainerFactory<String, CalculatorMessage> containerFactory,
            KafkaTemplate<String, CalculatorMessage> template,
            final KafkaConfigProps kafkaConfigProps
    ) {

        containerFactory.setReplyTemplate(template);
        ConcurrentMessageListenerContainer<String, CalculatorMessage> container = containerFactory.createContainer(kafkaConfigProps.getReplyTopic());
        ReplyingKafkaTemplate<String, CalculatorMessage, CalculatorMessage> replier = new ReplyingKafkaTemplate<>(pf, container);
        replier.setDefaultTopic(kafkaConfigProps.getTopic());

        return replier;
    }

    @Bean
    KafkaTemplate<String, CalculatorMessage> template(ProducerFactory<String, CalculatorMessage> pf) {
        return new KafkaTemplate<>(pf);
    }
}
