package com.avoris.infrastructure.in.rest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NoPastDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoPastDate {

    String message() default "Date must be present or future, not past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
