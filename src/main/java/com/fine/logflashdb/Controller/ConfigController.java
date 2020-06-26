package com.fine.logflashdb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/Config")
public class ConfigController {

    private final RestTemplate restTemplate;

    @Value("${logflash.url}")
    private String url;

    @Autowired
    public ConfigController(RestTemplateBuilder restTemplateBuilder) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate = restTemplateBuilder.build();
        restTemplate.setRequestFactory(requestFactory);
    }

    @GetMapping("/")
    public String getConfig() {
        return this.restTemplate.getForObject(this.url + "/Config", String.class);
    }

    @PatchMapping("/")
    public String patchConfig(@RequestBody String body) {
        return this.restTemplate.patchForObject(this.url + "/Config", body, String.class);
    }

}
