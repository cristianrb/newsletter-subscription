package com.github.cristianrb.subscriptionservice.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public SqsCredentialsProvider sqsCredentialsProvider(SqsConfiguration sqsConfiguration) {
        return new SqsCredentialsProvider(
                sqsConfiguration.getAccess_key(),
                sqsConfiguration.getSecret_access_key(),
                sqsConfiguration.getRegion(),
                sqsConfiguration.getEndpoint()
        );
    }

    @Bean
    public AmazonSQS amazonSQS(SqsCredentialsProvider sqsCredentialsProvider) {
        return AmazonSQSClientBuilder.standard()
                .withCredentials(sqsCredentialsProvider)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsCredentialsProvider.getEndpoint(), sqsCredentialsProvider.getRegion()))
                .build();
    }

}
