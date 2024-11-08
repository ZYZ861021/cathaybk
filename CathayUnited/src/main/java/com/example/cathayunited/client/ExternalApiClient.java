package com.example.cathayunited.client;

import com.example.cathayunited.dto.request.ExternalRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalApiClient {

    private final RestTemplate restTemplate;

    public ExternalApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callExternalApi(String url, ExternalRequestDto externalRequestDto) {
        return restTemplate.postForObject(url, externalRequestDto, String.class);
    }
}
