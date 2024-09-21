package com.kickoff.api.controller.auth;

import com.kickoff.api.controller.auth.dto.AuthenticationApiRequest;
import com.kickoff.api.controller.auth.dto.AuthenticationApiResponse;
import com.kickoff.api.service.auth.AuthService;
import com.kickoff.api.service.auth.AuthenticationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Auth")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authentication")
    public AuthenticationApiResponse login(@Valid @RequestBody AuthenticationApiRequest request)
    {
        return AuthenticationApiResponse.of(
                authService.authentication(AuthenticationRequest.of(request.email(), request.password()))
        );
    }

    @GetMapping("/social")
    public String token(@RequestParam(value = "accessToken") String accessToken)
    {
        return accessToken;
    }

    @PostMapping("/validate")
    public void validateJwtToken()
    {
        log.info("check validate");
    }
}
