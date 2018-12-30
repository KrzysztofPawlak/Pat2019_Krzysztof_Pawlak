package com.krzysztof.studio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public @Data class Equipment {

    @JsonInclude(NON_DEFAULT)
    private String projectorName;
    private boolean phoneAvailable;
    @Valid
    private Phone phone;

    @JsonInclude(NON_NULL)
    public Phone getPhone() {
        return phoneAvailable ? phone : null;
    }
}
