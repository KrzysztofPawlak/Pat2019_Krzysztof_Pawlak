package com.krzysztof.studio.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WhiteSpaceValidator implements ConstraintValidator<WhiteSpaceCheck, String> {

    static final String MESSAGE = "Field cannot contain only whitespace.";

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        return !StringUtils.isWhitespace(text);
    }
}
