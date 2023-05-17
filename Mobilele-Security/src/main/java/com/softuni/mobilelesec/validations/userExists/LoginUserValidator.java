package com.softuni.mobilelesec.validations.userExists;

import java.util.Optional;

import com.softuni.mobilelesec.domain.dtos.binding.UserLoginFormDto;
import com.softuni.mobilelesec.domain.entities.UserEntity;
import com.softuni.mobilelesec.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LoginUserValidator implements ConstraintValidator<ValidateLoginUser, UserLoginFormDto> {
	private final UserRepository userRepository;

	public LoginUserValidator(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	@Override
	public boolean isValid(UserLoginFormDto userLoginForm, ConstraintValidatorContext context) {
		Optional<UserEntity> loginCandidate = this.userRepository.findByEmail(userLoginForm.getUsername());

		return loginCandidate.isPresent() && loginCandidate.get().getPassword().equals(userLoginForm.getPassword());

	}

}
