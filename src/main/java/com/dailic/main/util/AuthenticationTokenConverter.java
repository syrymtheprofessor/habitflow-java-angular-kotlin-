package com.dailic.main.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AuthenticationTokenConverter implements Converter<Jwt, JwtAuthenticationToken> {
    public JwtAuthenticationToken convert(Jwt source) {
        UUID userId = UUID.fromString(source.getClaimAsString("sub"));
        String email = source.getClaimAsString("email");
        Boolean emailVerified = source.getClaimAsBoolean("email_verified");
        String firstName = source.getClaimAsString("given_name");
        String lastName = source.getClaimAsString("family_name");
        Map<String, Object> realmAccessMap = Optional.ofNullable(source.getClaimAsMap("realm_access")).orElse(Collections.emptyMap());
        List<String> roles = (List<String>) realmAccessMap.getOrDefault("roles", List.of());
        List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
        CustomUserPrincipal principal = new CustomUserPrincipal(userId, email, emailVerified, firstName, lastName, roles);
        return new JwtAuthenticationToken(source, authorities) { public Object getPrincipal() { return principal;}};
    }
}