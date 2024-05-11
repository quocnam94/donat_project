package com.luv2code.springdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumber, String>{
	
//	Validator for the phone number
	private String phoneNumberPrefix;
	
	@Override
	public void initialize(PhoneNumber thePhoneNumber) {
		phoneNumberPrefix = thePhoneNumber.value();
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext theConstraintValidatorContext) {
		boolean result;
		if (phoneNumber != null) {
			result = phoneNumber.startsWith(phoneNumberPrefix);
		}
		else {
			return true;
		}
		return result;
	}
	
}
