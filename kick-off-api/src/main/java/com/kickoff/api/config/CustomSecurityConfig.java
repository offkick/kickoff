package com.kickoff.api.config;

import com.kickoff.core.config.security.CustomUserDetailsService;
import com.kickoff.core.config.security.handler.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableMethodSecurity
public class CustomSecurityConfig {
    private final JWTCheckFilter jwtCheckFilter;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/static/**"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/login"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/signup"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-resources/**"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**"))
                .requestMatchers(AntPathRequestMatcher.antMatcher( "/api-docs"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/webjars/**"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/**"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/admin"))

                ;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        log.info("---- security config -----");
        httpSecurity.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });
        httpSecurity.csrf(s-> s.disable());
        httpSecurity.userDetailsService(customUserDetailsService);
        httpSecurity.addFilterBefore(jwtCheckFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling(configure -> configure.accessDeniedHandler(new CustomAccessDeniedHandler()));
        httpSecurity.sessionManagement(configurer ->configurer.sessionCreationPolicy(SessionCreationPolicy.NEVER));
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setExposedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
