package com.kickoff.core.config.security.config;

import com.google.gson.Gson;
import com.kickoff.core.config.security.CustomUserDetailsService;
import com.kickoff.core.config.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
    {
        String [] excludePath = {
                "/api/login",
                "/api/signup",
                "/h2-console",
                "/swagger-ui",
                "/v3",
                "/api/static",
                "/api/image/.*",
                "/api/images/",
                "/players",
                "/api/api-docs",
                "/admin/**",
                "/api/member/join",
                "/api/auth/authentication",
                "/api/post/search/",
                "/api/player/search",
                "/api/comment/search",
                "/api/league-game",
                "/api/post/search",
                "/api/league/",
                "/**",
                "/oauth2/authorization/google",
                "/api/oauth2/authorization/google",
                "/login/oauth2/code/google",
                "/api/auth/social",
                "/login",
                "/",
                "/post/test"

        };
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeaderStr = request.getHeader("Authorization");
        try {
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = tokenProvider.validateToken(accessToken);
            String email = (String) claims.get("email");
            String password = (String) claims.get("password");

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            if (userDetails != null)
            {
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        password,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            filterChain.doFilter(request, response);
        }
        catch (Exception e)
        {
            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
            response.setContentType("application/json");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }
    }
}
