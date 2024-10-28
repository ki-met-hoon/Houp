package com.example.houp.support.util;

import com.example.houp.advice.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RequestValidator<T> {

    private final Validator validator;

    public void validate(T body) {
        Errors errors = new BeanPropertyBindingResult(body, body.getClass().getName());

        this.validator.validate(body, errors);

        if (!errors.getAllErrors().isEmpty()) {
            onValidationErrors(errors);
        }
    }

    private void onValidationErrors(Errors errors) {
        throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
    }
}
