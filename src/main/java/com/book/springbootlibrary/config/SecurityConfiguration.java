package com.book.springbootlibrary.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //disable csrf
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(configurer ->
                        configurer
                            .requestMatchers("/api/books/secure/**",
                                    "/api/payment/secure/**",
                                    "/api/reviews/secure/**",
                                    "/api/messages/secure/**",
                                    "api/admin/secure/**").authenticated()
                            .requestMatchers("/api/books/**",
                                    "/api/reviews/**",
                                    "/api/histories/**","api/payments/**").permitAll()) //remove api/messages when going to production
                .oauth2ResourceServer((oauth2) -> oauth2
                .jwt(Customizer.withDefaults())
                );

        http.cors(Customizer.withDefaults());
        //content negotiation
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // force a non-empty response for 401
        Okta.configureResourceServer401ResponseBody(http);
        return http.build();
    }
}
