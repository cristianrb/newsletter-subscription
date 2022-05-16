package com.github.cristianrb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cristianrb.client.SubscriptionClient;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class ApplicationConfiguration {

    @Value("${subscription-service-url}")
    public String subscriptionServiceUrl;

    @Bean
    public SubscriptionClient subscriptionClient() {
        return new Retrofit.Builder()
                .baseUrl(subscriptionServiceUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(SubscriptionClient.class);
    }
}
