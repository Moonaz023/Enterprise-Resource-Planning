package com.erp.service;



import com.erp.dto.RequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

@Service
public class ApiService {

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateContent(String prompt) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create the request body
        RequestDTO.Part part = new RequestDTO.Part();
        part.setText(prompt);

        RequestDTO.Content content = new RequestDTO.Content();
        content.setParts(List.of(part));

        RequestDTO request = new RequestDTO();
        request.setContents(List.of(content));

        // Send the request
        HttpEntity<RequestDTO> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Parse the response JSON to extract only the "text"
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            String extractedText = root.path("candidates").get(0)
                                       .path("content").path("parts").get(0)
                                       .path("text").asText();

            // Convert Markdown to HTML using Flexmark
            Parser parser = Parser.builder().build();
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            return renderer.render(parser.parse(extractedText));  // Convert to HTML
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing response";
        }
    }
}