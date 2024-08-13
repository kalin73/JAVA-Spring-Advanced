package org.example.mobileleoffers.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.mobileleoffers.model.entity.UserEntity;
import org.example.mobileleoffers.model.user.MobileleUserDetails;
import org.example.mobileleoffers.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Optional;

@Service
public class JwtService {
    private final String secret;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public JwtService(@Value("${jwt.secret}") String secret, UserRepository userRepository, ModelMapper modelMapper) {
        this.secret = secret;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDetails extractUserDetails(String jwtToken) {
        Claims claims = extractClaims(jwtToken);
        final String userId = claims.getSubject();

        Optional<UserEntity> user = userRepository.findByUserId(userId);

        return user.map(u -> modelMapper.map(u, MobileleUserDetails.class)).orElseThrow();
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
