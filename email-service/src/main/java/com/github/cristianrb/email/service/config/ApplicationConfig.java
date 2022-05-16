package com.github.cristianrb.email.service.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.github.cristianrb.email.service.service.EmailService;
import com.github.cristianrb.email.service.service.SqsConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.MessageListener;

@Configuration
public class ApplicationConfig {

    private MessageListener sqsConsumer;

    @Bean
    public SqsCredentialsProvider sqsCredentialsProvider(SqsConfiguration sqsConfiguration) {
        String queueName = sqsConfiguration.getUri().substring(sqsConfiguration.getUri().lastIndexOf("/")+1);
        return new SqsCredentialsProvider(
                sqsConfiguration.getAccess_key(),
                sqsConfiguration.getSecret_access_key(),
                sqsConfiguration.getRegion(),
                sqsConfiguration.getEndpoint(),
                sqsConfiguration.getUri(),
                queueName
        );
    }

    @Bean
    public AmazonSQS amazonSQS(SqsCredentialsProvider sqsCredentialsProvider) {
        return AmazonSQSClientBuilder.standard()
                .withCredentials(sqsCredentialsProvider)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsCredentialsProvider.getEndpoint(), sqsCredentialsProvider.getRegion()))
                .build();
    }

    @Bean
    public SQSConnectionFactory sqsConnectionFactory(AmazonSQS amazonSQS) {
        return new SQSConnectionFactory(
                new ProviderConfiguration(),
                amazonSQS
        );
    }

    @Bean
    public MessageListener messageListener(EmailService emailService) {
        this.sqsConsumer = new SqsConsumer(emailService);
        return this.sqsConsumer;
    }

    @Bean
    public DefaultMessageListenerContainer jmsListener(SqsCredentialsProvider sqsCredentialsProvider, SQSConnectionFactory sqsConnectionFactory) {
        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setConnectionFactory(sqsConnectionFactory);
        dmlc.setDestinationName(sqsCredentialsProvider.getQueueName());
        dmlc.setMessageListener(sqsConsumer);
        return dmlc;
    }


}
