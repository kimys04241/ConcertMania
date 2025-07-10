package com.company.assignment.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(logRequest())
                .filter(logResponse());
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("요청 URL: " + clientRequest.url());
            System.out.println("요청 메서드: " + clientRequest.method());
            clientRequest.headers().forEach((name, values) ->
                    values.forEach(value -> System.out.println("Header: " + name + " = " + value))
            );
            return Mono.just(clientRequest);
        });
    }

    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            System.out.println("응답 상태 코드: " + clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }
}
