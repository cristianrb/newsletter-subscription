package com.github.cristianrb.email.service.service;

import com.amazon.sqs.javamessaging.message.SQSTextMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cristianrb.email.service.model.SubscriptionDto;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class SqsConsumer implements MessageListener {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmailService emailService;

    public SqsConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onMessage(Message message) {
        SQSTextMessage sqsMessage = (SQSTextMessage) message;
        try {
            SubscriptionDto subscriptionMessage = objectMapper.readValue(sqsMessage.getText(), SubscriptionDto.class);
            emailService.sendEmail(subscriptionMessage.getEmail(), subscriptionMessage.getNewsletterId());
            System.out.println("Sending email to subscription: " + subscriptionMessage);
        } catch (JMSException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
