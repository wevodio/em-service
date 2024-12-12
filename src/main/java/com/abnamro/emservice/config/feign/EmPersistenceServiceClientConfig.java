package com.abnamro.emservice.config.feign;

import com.abnamro.emservice.usecase.FeignEmPersistenceClient;
import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class EmPersistenceServiceClientConfig {

    @Bean
    FeignEmPersistenceClient feignEmPersistenceClient(
            @Value("${em.persistence.service.apiKey}") String apiKey,
            @Value("${em.persistence.service.url}") String url) {

        return Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .logger(new Slf4jLogger())
                .retryer(new Retryer.Default(5000L, TimeUnit.SECONDS.toMillis(3L), 3))
                .errorDecoder(new OriginalMessageErrorDecoder())
                .logLevel(Logger.Level.HEADERS)
                .requestInterceptor(requestTemplate -> requestTemplate.header("x-api-key", apiKey))
                .target(FeignEmPersistenceClient.class, url);
    }

//    @Bean
//    public Retryer retryer() {
//        return new Retryer.Default(200, SECONDS.toMillis(2), 3);
//    }
}