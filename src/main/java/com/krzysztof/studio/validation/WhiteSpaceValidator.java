package com.krzysztof.studio.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WhiteSpaceValidator implements ConstraintValidator<WhiteSpaceCheck, String> {

    static final String MESSAGE = "Field cannot contain only whitespace.";

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        return !isWhitespace(text);
    }

    private boolean isWhitespace(String text) {
        return text.replaceAll("\\s", "").isEmpty();
    }
}
