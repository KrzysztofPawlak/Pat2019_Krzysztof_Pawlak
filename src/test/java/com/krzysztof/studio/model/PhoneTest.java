package com.krzysztof.studio.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static final int VALID_INTERNAL_NUMBER = 99;
    private static final String VALID_EXTERNAL_NUMBER = "+12 123456789";

    @Test
    void should_have_correct_external_number() {
        var phone = createPhoneWithExternalNumber(VALID_EXTERNAL_NUMBER);
        Set<ConstraintViolation<Phone>> violations = validator.validate(phone);
        assertThat(violations.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12 123456789",
            "+12 1234foo6789",
            "+12123456789",
            "+121234567891",
    })
    void should_have_incorrect_external_number(String number) {
        var phone = createPhoneWithExternalNumber(number);
        Set<ConstraintViolation<Phone>> violations = validator.validate(phone);
        assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void should_have_correct_internal_number() {
        var phone = createPhoneWithInternalNumber(VALID_INTERNAL_NUMBER);
        Set<ConstraintViolation<Phone>> violations = validator.validate(phone);
        assertThat(violations.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -2,
            101
    })
    void should_have_incorrect_internal_number(int number) {
        var phone = createPhoneWithInternalNumber(number);
        Set<ConstraintViolation<Phone>> violations = validator.validate(phone);
        assertThat(violations.isEmpty()).isFalse();
    }

    private Phone createPhoneWithInternalNumber(int internalNumber) {
        return createPhone(internalNumber, VALID_EXTERNAL_NUMBER);
    }

    private Phone createPhoneWithExternalNumber(String externalNumber) {
        return createPhone(VALID_INTERNAL_NUMBER, externalNumber);
    }

    private Phone createPhone(int internalNumber, String externalNumber) {
        var phone = new Phone();
        phone.setInternalNumber(internalNumber);
        phone.setExternalNumber(externalNumber);
        return phone;
    }
}
