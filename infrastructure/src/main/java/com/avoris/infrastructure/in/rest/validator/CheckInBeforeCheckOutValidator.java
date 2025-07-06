package com.avoris.infrastructure.in.rest.validator;

import com.avoris.infrastructure.in.rest.dto.SearchRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class CheckInBeforeCheckOutValidator implements ConstraintValidator<CheckInBeforeCheckOut, SearchRequestDto> {

    @Override
    public boolean isValid(SearchRequestDto dto, ConstraintValidatorContext context) {
        if (isNull(dto.getCheckIn()) || isNull(dto.getCheckOut())) {
            return true;
        }

        return dto.getCheckIn().isBefore(dto.getCheckOut());
    }
}
