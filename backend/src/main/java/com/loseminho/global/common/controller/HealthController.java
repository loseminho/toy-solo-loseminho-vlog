package com.loseminho.global.common.controller;

import com.loseminho.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 헬스체크 컨트롤러
 */
@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<Map<String, Object>> health() {
        Map<String, Object> healthInfo = new HashMap<>();
        healthInfo.put("status", "UP");
        healthInfo.put("timestamp", LocalDateTime.now());
        healthInfo.put("service", "loseminho-backend");
        
        return ApiResponse.success(healthInfo, "서비스가 정상적으로 실행 중입니다.");
    }

    @GetMapping("/ping")
    public ApiResponse<String> ping() {
        return ApiResponse.success("pong");
    }
}
