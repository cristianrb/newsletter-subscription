package com.github.cristianrb.subscriptionservice.service;

import com.github.cristianrb.subscriptionservice.exception.SubscriptionNotFoundException;
import com.github.cristianrb.subscriptionservice.model.Subscription;
import com.github.cristianrb.subscriptionservice.model.SubscriptionDto;
import com.github.cristianrb.subscriptionservice.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SQSProcessor sqsProcessor;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SQSProcessor sqsProcessor) {
        this.subscriptionRepository = subscriptionRepository;
        this.sqsProcessor = sqsProcessor;
    }

    @Override
    public Mono<SubscriptionDto> createSubscription(SubscriptionDto subscription) {
        return subscriptionRepository
                .save(subscription.toSubscription())
                .map(Subscription::toSubscriptionDto)
                .map(s -> {
                    sqsProcessor.sendMessage(s);
                    return s;
                });
    }

    @Override
    public Mono<Void> cancelSubscriptionById(String id) {
        return getSubscriptionById(id)
                .switchIfEmpty(Mono.error(new SubscriptionNotFoundException("Subscription with id: " + id + " was not found")))
                .flatMap(s -> subscriptionRepository.deleteById(s.getId()));
    }

    @Override
    public Mono<SubscriptionDto> getSubscriptionById(String id) {
        return subscriptionRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new SubscriptionNotFoundException("Subscription with id: " + id + " was not found")))
                .map(Subscription::toSubscriptionDto);
    }

    @Override
    public Flux<SubscriptionDto> getAll() {
        return subscriptionRepository.findAll()
                .map(Subscription::toSubscriptionDto);
    }

}
