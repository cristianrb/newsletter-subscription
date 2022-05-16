package com.github.cristianrb.subscriptionservice.repository;

import com.github.cristianrb.subscriptionservice.model.Subscription;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends ReactiveMongoRepository<Subscription, String> {

}
