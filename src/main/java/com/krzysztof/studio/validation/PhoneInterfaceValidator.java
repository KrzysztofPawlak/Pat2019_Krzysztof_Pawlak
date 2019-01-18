package com.krzysztof.studio.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PhoneInterfaceValidator implements ConstraintValidator<PhoneInterfaceEnum, String> {

    private PhoneInterfaceEnum annotation;

    @Override
    public void initialize(PhoneInterfaceEnum annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueToValid, ConstraintValidatorContext constraintValidatorContext) {
        if (valueToValid == null) return true;
        var enumValues = this.annotation.enumClass().getEnumConstants();
        return Arrays.stream(enumValues).anyMatch(enumValue -> valueToValid.equalsIgnoreCase(enumValue.toString()));
    }
}
