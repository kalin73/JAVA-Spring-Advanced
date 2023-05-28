package com.softuni.pathfinder.validations.userExists;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UserLoginValidator.class)
public @interface ValidateLoginUser {
	String message() default "Username or password doesn't match.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
