package com.github.cristianrb.subscriptionservice.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cristianrb.subscriptionservice.model.SubscriptionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SQSProcessor {

    private final AmazonSQS sqs;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${services.sqs.credentials.uri}")
    private String queueUri;

    public SQSProcessor(AmazonSQS sqs) {
        this.sqs = sqs;
    }

    public void sendMessage(SubscriptionDto message) {
        String messageAsText = "";
        try {
            messageAsText = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        SendMessageRequest sendMsgRequest = new SendMessageRequest()
            .withQueueUrl(queueUri)
            .withMessageBody(messageAsText)
            .withDelaySeconds(5);
        sqs.sendMessage(sendMsgRequest);
    }

}
