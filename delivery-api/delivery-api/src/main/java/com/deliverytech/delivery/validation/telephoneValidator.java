package com.deliverytech.delivery.validation;

import com.deliverytech.delivery.validation.Interface.validTelephone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class telephoneValidator implements ConstraintValidator<validTelephone, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        return value.matches("\\(\\d{2}\\)\\s\\d{5}-\\d{4}") || value.matches("\\d{11}");
    }
}
