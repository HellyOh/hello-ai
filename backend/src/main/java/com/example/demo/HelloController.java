package com.example.demo;

import java.net.http.HttpClient;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api")
public class HelloController {

    private final RestClient aiClient;

    // ai.base-url 설정값을 받아서, AI 서버를 호출할 클라이언트를 만든다
    public HelloController(@Value("${ai.base-url}") String aiBaseUrl) {
        // 자바 HttpClient는 기본이 HTTP/2(h2c 업그레이드)라서 uvicorn이 요청을 해석하지 못한다.
        // HTTP/1.1로 고정해야 AI 서버가 본문을 정상적으로 받는다.
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        this.aiClient = RestClient.builder()
                .baseUrl(aiBaseUrl)
                .requestFactory(new JdkClientHttpRequestFactory(httpClient))
                .build();
    }

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        // AI 서버의 /summarize 를 호출
        Map<?, ?> aiResult = aiClient.post()
                .uri("/summarize")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("text", "도커로 만든 첫 번째 AI 서비스입니다"))
                .retrieve()
                .body(Map.class);

        return Map.of("from", "spring-boot", "ai", aiResult);
    }
}