package com.softuni.mobilelesec.validations.matchingPasswords;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, Object> {
	private String password;

	private String confirmPassword;

	private String message;

	@Override
	public void initialize(PasswordMatch constraintAnnotation) {
		this.password = constraintAnnotation.password();
		this.confirmPassword = constraintAnnotation.confirmPassword();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

		Object passwordValue = beanWrapper.getPropertyValue(this.password);
		Object confirmPasswordValue = beanWrapper.getPropertyValue(this.confirmPassword);

		if (passwordValue != null && passwordValue.equals(confirmPasswordValue)) {
			return true;
		}

		context.buildConstraintViolationWithTemplate(message).addPropertyNode(confirmPassword).addConstraintViolation()
				.disableDefaultConstraintViolation();

		return false;
	}

}
