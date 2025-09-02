package com.deliverytech.delivery.validation.Interface;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.deliverytech.delivery.validation.cepValidator;

import jakarta.validation.Constraint;

@Documented

@Constraint(validatedBy = cepValidator.class)

@Target({ ElementType.FIELD })

@Retention(RetentionPolicy.RUNTIME)

public @interface validCep {
    String message() default "CEP inválido";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
