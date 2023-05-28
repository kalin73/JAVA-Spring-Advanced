package com.softuni.pathfinder.validations.userExists;

import java.util.Optional;

import com.softuni.pathfinder.domain.dtos.binding.UserLoginForm;
import com.softuni.pathfinder.domain.entities.UserEntity;
import com.softuni.pathfinder.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserLoginValidator implements ConstraintValidator<ValidateLoginUser, UserLoginForm> {
	private final UserRepository userRepository;

	public UserLoginValidator(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	@Override
	public boolean isValid(UserLoginForm userLoginForm, ConstraintValidatorContext context) {
		Optional<UserEntity> loginCandidate = this.userRepository.findByUsername(userLoginForm.getUsername());

		return loginCandidate.isPresent() && loginCandidate.get().getPassword().equals(userLoginForm.getPassword());

	}

}
