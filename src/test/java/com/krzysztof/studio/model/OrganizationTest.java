package com.krzysztof.studio.model;

import com.krzysztof.studio.model.rest.Organization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OrganizationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void should_have_whitespace_validation_error() {
        var name = "   ";
        var organization = createOrganizationWithName(name);
        Set<ConstraintViolation<Organization>> violations = validator.validate(organization);
        assertThat(violations.isEmpty()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            " foo",
            "foo bar",
            "foo ",
    })
    void should_not_have_whitespace_validation_error(String name) {
        var organization = createOrganizationWithName(name);
        Set<ConstraintViolation<Organization>> violations = validator.validate(organization);
        assertThat(violations.isEmpty()).isTrue();
    }

    private Organization createOrganizationWithName(String organizationName) {
        var organization = new Organization();
        organization.setName(organizationName);
        return organization;
    }
}
