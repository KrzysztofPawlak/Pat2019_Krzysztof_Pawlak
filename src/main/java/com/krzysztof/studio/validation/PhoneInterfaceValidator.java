package com.krzysztof.studio.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneInterfaceValidator implements ConstraintValidator<PhoneInterfaceEnum, String> {

    private PhoneInterfaceEnum annotation;

    @Override
    public void initialize(PhoneInterfaceEnum annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueToValid, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = false;

        Object[] enumValues = this.annotation.enumClass().getEnumConstants();

        if(enumValues != null) {
            for(Object en : enumValues) {
                if(valueToValid.equals(en.toString()) || (this.annotation.ignoreCase() && valueToValid.equalsIgnoreCase(en.toString()))) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
