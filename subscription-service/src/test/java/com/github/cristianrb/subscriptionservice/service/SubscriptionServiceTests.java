package com.github.cristianrb.subscriptionservice.service;

import com.github.cristianrb.subscriptionservice.exception.SubscriptionNotFoundException;
import com.github.cristianrb.subscriptionservice.model.SubscriptionDto;
import com.github.cristianrb.subscriptionservice.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class SubscriptionServiceTests {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private SQSProcessor sqsProcessor;

    private SubscriptionService subject;

    @BeforeEach
    public void setup() {
        this.subject = new SubscriptionServiceImpl(subscriptionRepository, sqsProcessor);
    }

    @Test
    public void Given_ASubscriptionDto_When_CreatingSubscription_Then_ShouldCreateTheSubscription() {
        SubscriptionDto subscriptionDto = new SubscriptionDto(null, "test@test.com", "test", "gender", "2006-01-02", true, "1");
        SubscriptionDto expectedSubscriptionDto = new SubscriptionDto("id", "test@test.com", "test", "gender", "2006-01-02", true, "1");
        given(subscriptionRepository.save(subscriptionDto.toSubscription())).willReturn(Mono.just(expectedSubscriptionDto.toSubscription()));
        doNothing().when(sqsProcessor).sendMessage(expectedSubscriptionDto);

        StepVerifier
                .create(subject.createSubscription(subscriptionDto))
                .expectNext(expectedSubscriptionDto)
                .verifyComplete();

        verify(subscriptionRepository).save(subscriptionDto.toSubscription());
        verify(sqsProcessor).sendMessage(expectedSubscriptionDto);
    }

    @Test
    public void Given_AnId_When_CancellingSubscription_Then_ShouldDeleteIt() {
        SubscriptionDto expectedSubscriptionDto = new SubscriptionDto("id", "test@test.com", "test", "gender", "2006-01-02", true, "1");
        given(subscriptionRepository.findById(expectedSubscriptionDto.getId())).willReturn(Mono.just(expectedSubscriptionDto.toSubscription()));
        given(subscriptionRepository.deleteById(expectedSubscriptionDto.getId())).willReturn(Mono.empty());

        StepVerifier
                .create(subject.cancelSubscriptionById(expectedSubscriptionDto.getId()))
                .verifyComplete();

        verify(subscriptionRepository).findById(expectedSubscriptionDto.getId());
        verify(subscriptionRepository).deleteById(expectedSubscriptionDto.getId());
    }

    @Test
    public void Given_ANotExistingIdToCancelSubscription_When_CancellingSubscription_Then_ShouldThrowAnError() {
        given(subscriptionRepository.findById("notValid")).willReturn(Mono.empty());

        StepVerifier
                .create(subject.cancelSubscriptionById("notValid"))
                .expectError(SubscriptionNotFoundException.class)
                .verify();

        verify(subscriptionRepository).findById("notValid");
    }

    @Test
    public void Given_AnId_WhenGettingDetailsOfSubscription_Then_ShouldReturnTheSubscription() {
        SubscriptionDto expectedSubscriptionDto = new SubscriptionDto("id", "test@test.com", "test", "gender", "2006-01-02", true, "1");
        given(subscriptionRepository.findById(expectedSubscriptionDto.getId())).willReturn(Mono.just(expectedSubscriptionDto.toSubscription()));

        StepVerifier
                .create(subject.getSubscriptionById(expectedSubscriptionDto.getId()))
                .expectNext(expectedSubscriptionDto)
                .verifyComplete();

        verify(subscriptionRepository).findById(expectedSubscriptionDto.getId());
    }

    @Test
    public void Given_ANotExistingId_WhenGettingDetailsOfSubscription_Then_ShouldThrowAnError() {
        given(subscriptionRepository.findById("notValid")).willReturn(Mono.empty());

        StepVerifier
                .create(subject.getSubscriptionById("notValid"))
                .expectError(SubscriptionNotFoundException.class)
                .verify();

        verify(subscriptionRepository).findById("notValid");
    }

    @Test
    public void Given_TryingToGetAllSubscriptions_WhenCallingGetAll_Then_ShouldReturnAllSubscriptions() {
        SubscriptionDto expectedSubscriptionDto1 = new SubscriptionDto("id", "test@test.com", "test", "gender", "2006-01-02", true, "1");
        SubscriptionDto expectedSubscriptionDto2 = new SubscriptionDto("id2", "test@test.com", "test", "gender", "2006-01-02", true, "1");
        given(subscriptionRepository.findAll()).willReturn(Flux.just(expectedSubscriptionDto1.toSubscription(), expectedSubscriptionDto2.toSubscription()));

        StepVerifier
                .create(subject.getAll())
                .expectNext(expectedSubscriptionDto1)
                .expectNext(expectedSubscriptionDto2)
                .verifyComplete();

        verify(subscriptionRepository).findAll();
    }

}
