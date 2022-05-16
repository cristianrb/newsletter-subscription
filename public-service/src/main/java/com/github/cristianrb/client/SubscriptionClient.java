package com.github.cristianrb.client;

import com.github.cristianrb.model.SubscriptionDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface SubscriptionClient {

    String SUBSCRIPTION_PATH = "/subscription";

    @POST(SUBSCRIPTION_PATH)
    Call<SubscriptionDto> createSubscription(@Body SubscriptionDto subscriptionDto);

    @DELETE(SUBSCRIPTION_PATH + "/{id}")
    Call<Void> cancelSubscription(@Path(value = "id") String id);

    @GET(SUBSCRIPTION_PATH + "/{id}")
    Call<SubscriptionDto> getSubscriptionById(@Path(value = "id") String id);

    @GET(SUBSCRIPTION_PATH)
    Call<List<SubscriptionDto>> getAllSubscriptions();
}
