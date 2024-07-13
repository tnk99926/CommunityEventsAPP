package com.codestep.portfolio1app.Exceptions;

import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException {

    private final BindingResult result;

    public ValidationException(BindingResult result) {
        this.result = result;
    }

    public BindingResult getBindingResult() {
        return result;
    }
}