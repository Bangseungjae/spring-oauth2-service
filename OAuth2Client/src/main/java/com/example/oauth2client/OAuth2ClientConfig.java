package com.example.oauth2client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OAuth2ClientConfig {

    @Bean
    public SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request.antMatchers("/", "/photos").permitAll()
                .anyRequest().authenticated());
        http.oauth2Login(authLogin -> authLogin.defaultSuccessUrl("/"));
        http.oauth2Client();
        return http.build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 토큰이 만료되면 refresh Token으로 인가받기 위해서 설정을 해야한다.
    @Bean
    public DefaultOAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository,
                                                                        OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
        OAuth2AuthorizedClientProvider auth2AuthorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                .authorizationCode()
                .clientCredentials()
                .password()
                .refreshToken()
                .build();

        DefaultOAuth2AuthorizedClientManager oAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, oAuth2AuthorizedClientRepository);
        oAuth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
        return oAuth2AuthorizedClientManager;
    }
}
