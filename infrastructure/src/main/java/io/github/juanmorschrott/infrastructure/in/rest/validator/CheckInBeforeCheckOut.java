package io.github.juanmorschrott.infrastructure.in.rest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckInBeforeCheckOutValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckInBeforeCheckOut {

    String message() default "CheckIn date must be before checkOut date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
