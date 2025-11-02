package com.loseminho.global.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 루트 경로 컨트롤러
 */
@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, String> root() {
        Map<String, String> info = new HashMap<>();
        info.put("service", "Loseminho Backend API");
        info.put("version", "1.0.0");
        info.put("status", "running");
        info.put("docs", "/swagger-ui.html");
        info.put("health", "/api/v1/health");
        return info;
    }
}
