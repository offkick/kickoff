package com.kickoff.api.controller.auth;

import com.kickoff.api.controller.auth.dto.AuthenticationApiRequest;
import com.kickoff.api.controller.auth.dto.AuthenticationApiResponse;
import com.kickoff.api.service.auth.AuthService;
import com.kickoff.api.service.auth.AuthenticationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Auth")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authentication")
    public AuthenticationApiResponse login(@RequestBody AuthenticationApiRequest request)
    {
        return AuthenticationApiResponse.of(
                authService.authentication(AuthenticationRequest.of(request.email(), request.password()))
        );
    }

    @PostMapping("/validate")
    public void validateJwtToken()
    {
        log.info("check validate");
    }
}
