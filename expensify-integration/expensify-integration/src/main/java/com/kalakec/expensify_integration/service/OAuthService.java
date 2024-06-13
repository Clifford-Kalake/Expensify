package com.kalakec.expensify_integration.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OAuthService {

    @Value("${expensify.partnerUserID}")
    private String partnerUserID;

    @Value("${expensify.partnerUserSecret}")
    private String partnerUserSecret;

    @Value("${expensify.tokenUrl}")
    private String tokenUrl;

    private String accessToken;

    public String getAccessToken() {
        if (accessToken == null) {
            authenticate();
        }
        return accessToken;
    }

    private void authenticate() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Map<String, String> params = new HashMap<>();
        params.put("partnerUserID", partnerUserID);
        params.put("partnerUserSecret", partnerUserSecret);
        params.put("grant_type", "partner");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, String> responseBody = (Map<String, String>) response.getBody();
                if (responseBody != null) {
                    accessToken = responseBody.get("access_token");
                } else {
                    throw new RuntimeException("No access token in response");
                }
            } else {
                throw new RuntimeException("Failed to authenticate with Expensify: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Invalid credentials or request: " + e.getMessage());
        } catch (ResourceAccessException e) {
            throw new RuntimeException("Network error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }
}
