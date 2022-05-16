package com.github.cristianrb.rest;

import com.github.cristianrb.client.SubscriptionClient;
import com.github.cristianrb.model.SubscriptionDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final SubscriptionClient subscriptionClient;

    public SubscriptionController(SubscriptionClient subscriptionClient) {
        this.subscriptionClient = subscriptionClient;
    }

    @PostMapping
    public SubscriptionDto createSubscription(@Valid @RequestBody SubscriptionDto subscription) throws IOException {
        return subscriptionClient.createSubscription(subscription).execute().body();
    }

    @DeleteMapping("/{id}")
    public void cancelSubscription(@PathVariable String id) throws IOException {
        subscriptionClient.cancelSubscription(id).execute().body();
    }

    @GetMapping("/{id}")
    public SubscriptionDto getSubscriptionById(@PathVariable String id) throws IOException {
        return subscriptionClient.getSubscriptionById(id).execute().body();
    }

    @GetMapping
    public List<SubscriptionDto> getAllSubscriptions() throws IOException {
        return subscriptionClient.getAllSubscriptions().execute().body();
    }

}
