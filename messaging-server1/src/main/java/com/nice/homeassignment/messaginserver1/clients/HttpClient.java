package com.nice.homeassignment.messaginserver1.clients;

import com.nice.homeassignment.messaginserver1.exceptions.HttpClientStatusException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

public class HttpClient<T> {

    private String baseUrl;
    private final WebClient webClient;

    public HttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono get(String relativeUrl){
        WebClient.ResponseSpec response = webClient
                .method(HttpMethod.GET)
                .uri(URI.create(relativeUrl)).retrieve();

        return handleResponse(response);
    }

    public Mono post(String relativeUrl, Object body, Class clazz){
        WebClient.ResponseSpec response = webClient
                .post().uri(relativeUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(body), clazz).retrieve()
                ;

        return handleResponse(response);
    }

    private Mono handleResponse(WebClient.ResponseSpec response) {
        return response.onStatus(HttpStatus::is4xxClientError, clientResponse ->
                Mono.error(new HttpClientStatusException())
        )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new HttpClientStatusException())
                ).bodyToMono(Object.class);
    }
}
