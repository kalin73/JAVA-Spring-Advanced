package com.softuni.mobilelesec.services;

import com.softuni.mobilelesec.domain.dtos.binding.UserRegisterFormDto;
import com.softuni.mobilelesec.domain.entities.UserEntity;
import com.softuni.mobilelesec.domain.user.MobileleUserDetails;
import com.softuni.mobilelesec.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public boolean isDbInit() {
        return this.userRepository.count() > 0;
    }

    public Optional<MobileleUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof MobileleUserDetails mobileleUserDetails) {
            return Optional.of(mobileleUserDetails);
        }

        return Optional.empty();

    }

    public void registerUser(UserRegisterFormDto userRegister) {
        UserEntity userToSave = new UserEntity().setFirstName(userRegister.getFirstName())
                .setLastName(userRegister.getLastName()).setEmail(userRegister.getEmail())
                .setPassword(passwordEncoder.encode(userRegister.getPassword()));

        this.userRepository.save(userToSave);

        this.emailService.sendRegistrationEmail(userToSave.getEmail(),
                userToSave.getFirstName() + " " + userToSave.getLastName());
    }

}
