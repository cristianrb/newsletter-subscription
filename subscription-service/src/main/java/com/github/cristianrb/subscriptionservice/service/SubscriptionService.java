package com.github.cristianrb.subscriptionservice.service;

import com.github.cristianrb.subscriptionservice.model.SubscriptionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubscriptionService {

    Mono<SubscriptionDto> createSubscription(SubscriptionDto subscription);

    Mono<Void> cancelSubscriptionById(String id);

    Mono<SubscriptionDto> getSubscriptionById(String id);

    Flux<SubscriptionDto> getAll();
}
