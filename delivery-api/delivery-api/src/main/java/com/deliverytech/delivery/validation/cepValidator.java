package com.deliverytech.delivery.validation;

import com.deliverytech.delivery.validation.Interface.validCep;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class cepValidator implements ConstraintValidator<validCep, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return value.matches("\\d{5}-\\d{3}") || value.matches("\\d{8}");
    }
}
