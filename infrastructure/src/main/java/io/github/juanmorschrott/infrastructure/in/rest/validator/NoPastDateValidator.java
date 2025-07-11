package io.github.juanmorschrott.infrastructure.in.rest.validator;

import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

import static java.util.Objects.isNull;

public class NoPastDateValidator implements ConstraintValidator<NoPastDate, SearchRequestDto> {

    @Override
    public boolean isValid(SearchRequestDto dto, ConstraintValidatorContext context) {
        if (isNull(dto) || isNull(dto.getCheckIn()) || isNull(dto.getCheckOut())) {
            return true;
        }

        LocalDate now = LocalDate.now();
        return !dto.getCheckIn().isBefore(now) && !dto.getCheckOut().isBefore(now);
    }
}
