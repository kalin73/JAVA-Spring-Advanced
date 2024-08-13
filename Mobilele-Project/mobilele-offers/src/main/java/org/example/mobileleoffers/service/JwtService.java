package org.example.mobileleoffers.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.mobileleoffers.model.entity.UserEntity;
import org.example.mobileleoffers.model.user.MobileleUserDetails;
import org.example.mobileleoffers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Optional;
import java.util.UUID;

@Service
public class JwtService {
    private final String secret;

    private final UserRepository userRepository;

    public JwtService(@Value("${jwt.secret}") String secret, UserRepository userRepository) {
        this.secret = secret;
        this.userRepository = userRepository;
    }

    public UserDetails extractUserDetails(String jwtToken) {
        Claims claims = extractClaims(jwtToken);
        final String userId = claims.getSubject();

        Optional<UserEntity> user = userRepository.findByUserId(UUID.fromString(userId));

        return user.map(MobileleUserDetails::mapToMobileleUserDetails).orElseThrow();
    }

    private Claims extractClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes();

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
