package com.github.cristianrb.subscriptionservice.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.github.cristianrb.subscriptionservice.model.SubscriptionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class SQSProcessorTests {

    @Mock
    private AmazonSQS sqs;

    private SQSProcessor subject;

    @BeforeEach
    public void setup() {
        subject = new SQSProcessor(sqs);
    }

    @Test
    public void Given_SendingAMessage_When_CallingSendMessage_Then_ShouldSendTheMessage() {
        SubscriptionDto subscriptionDto = new SubscriptionDto("id", "test@test.com", "test", "gender", "2006-01-02", true, "1");
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(null)
                .withMessageBody("{\"id\":\"id\",\"email\":\"test@test.com\",\"firstName\":\"test\",\"gender\":\"gender\",\"dateOfBirth\":\"2006-01-02\",\"consented\":true,\"newsletterId\":\"1\"}")
                .withDelaySeconds(5);
        given(sqs.sendMessage(sendMessageRequest)).willReturn(new SendMessageResult());

        subject.sendMessage(subscriptionDto);

        verify(sqs).sendMessage(sendMessageRequest);
    }
}
