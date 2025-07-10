package com.company.assignment.common.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class AssignmentManiaWebClient {

    private final WebClient webClient;

    public AssignmentManiaWebClient(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("https://").build();
    }

    public Mono<?> call() {
        return webClient.post()
                .uri("/")
                .header("Content-Type", "application/json")
                .bodyValue("")
                .retrieve()
                .bodyToMono(Object.class)
                .timeout(Duration.ofSeconds(30))
                .doOnSuccess(response -> System.out.println("api Response: " + response))
                .doOnError(error -> System.out.println("api 호출 실패: " + error.getMessage()));
    }
}
