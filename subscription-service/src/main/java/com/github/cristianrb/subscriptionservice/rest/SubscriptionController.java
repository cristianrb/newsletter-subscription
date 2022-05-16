package com.github.cristianrb.subscriptionservice.rest;

import com.github.cristianrb.subscriptionservice.model.SubscriptionDto;
import com.github.cristianrb.subscriptionservice.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public Mono<SubscriptionDto> createSubscription(@Valid @RequestBody SubscriptionDto subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> cancelSubscription(@PathVariable String id) {
        return subscriptionService.cancelSubscriptionById(id);
    }

    @GetMapping("/{id}")
    public Mono<SubscriptionDto> getSubscriptionById(@PathVariable String id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @GetMapping
    public Flux<SubscriptionDto> getAllSubscriptions() {
        return subscriptionService.getAll();
    }

}
