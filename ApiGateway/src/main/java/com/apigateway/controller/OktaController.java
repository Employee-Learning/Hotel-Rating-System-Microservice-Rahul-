package com.apigateway.controller;

import com.apigateway.models.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class OktaController {

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user
    ) {

        AuthResponse authResponse = new AuthResponse();

        authResponse.setUserId(user.getSubject());

        if (client != null && client.getAccessToken() != null) {
            authResponse.setAccessToken(client.getAccessToken().getTokenValue());
            authResponse.setExpiresAt(
                    client.getAccessToken().getExpiresAt().getEpochSecond());
        }

        if (client != null && client.getRefreshToken() != null) {
            authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
        }
        Collection<String> authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        authResponse.setAuthorities(authorities);
        return ResponseEntity.ok(authResponse);
    }
}
