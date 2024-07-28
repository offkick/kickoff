package com.kickoff.api.controller.auth;

import com.kickoff.api.controller.auth.dto.AuthenticationApiRequest;
import com.kickoff.api.controller.auth.dto.AuthenticationApiResponse;
import com.kickoff.api.service.auth.AuthService;
import com.kickoff.api.service.auth.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authentication")
    public AuthenticationApiResponse login(AuthenticationApiRequest request)
    {
        return AuthenticationApiResponse.of(
                authService.authentication(AuthenticationRequest.of(request.email(), request.password()))
        );
    }
}
