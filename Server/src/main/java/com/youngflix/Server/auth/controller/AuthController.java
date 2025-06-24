package com.youngflix.Server.auth.controller;


import com.youngflix.Server.auth.dto.LoginRequest;
import com.youngflix.Server.auth.dto.TokenResponse;
import com.youngflix.Server.auth.service.AuthService;
import com.youngflix.Server.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authService.login(loginRequest);
        return ResponseEntity.ok(new ApiResponse<>(200, "로그인 성공", tokenResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenResponse>> refresh(@RequestBody String refreshToken) {
        TokenResponse tokenResponse = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(new ApiResponse<>(200, "리프래시 토큰 발급", tokenResponse));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody String email) {
        authService.logout(email);
        return ResponseEntity.noContent().build();
    }
}
