package com.gabriel.pive.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Comma-separated list of allowed origins for CORS. Default points at the
     * Vite dev server so a fresh clone "just works". In any other environment,
     * set the env var {@code BOVINA_CORS_ORIGINS} (or system property
     * {@code bovina.cors.allowed-origins}) to the public frontend URL(s):
     *
     * <pre>BOVINA_CORS_ORIGINS=https://app.bovina.example,https://staging.bovina.example</pre>
     */
    @Value("${bovina.cors.allowed-origins:http://localhost:5173}")
    private String[] allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
