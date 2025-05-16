package com.example.demo.service;

import com.example.demo.model.QuestionAnswer;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CohereService {

    private static final String COHERE_API_URL = "https://api.cohere.ai/v1/chat";
    private static final String API_KEY = "Bearer 8Ry1FFcD5EgFlvQjAddfDq38vdXhF1ymTmKMZFId"; // <-- THAY bằng key thật

    public String analyzeWithCohere(List<QuestionAnswer> responses) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // Ghép câu hỏi thành đoạn hội thoại
            StringBuilder promptBuilder = new StringBuilder();
            promptBuilder.append("Analyze this ADHD quiz and estimate the likelihood:\n");
            for (QuestionAnswer r : responses) {
                promptBuilder.append(r.getQuestion()).append(" → ").append(r.getAnswer()).append("\n");
            }

            // Payload cho Cohere
            Map<String, Object> payload = new HashMap<>();
            payload.put("message", promptBuilder.toString());
            payload.put("model", "command-r"); // hoặc command-r nếu chưa được mở access
            payload.put("temperature", 0.3);
            payload.put("chat_history", new ArrayList<>()); // Optional

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", API_KEY);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    COHERE_API_URL, HttpMethod.POST, request, String.class
            );

            JSONObject json = new JSONObject(response.getBody());
            return json.getString("text"); // hoặc tùy theo format Cohere trả

        } catch (Exception e) {
            return "Error calling Cohere: " + e.getMessage();
        }
    }
}
