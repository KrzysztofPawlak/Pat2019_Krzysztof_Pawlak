package com.krzysztof.studio.model;

import com.krzysztof.studio.validation.WhiteSpaceCheck;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.krzysztof.studio.config.ApiConfig.ORGANIZATION_NAME_MAX_LENGTH;
import static com.krzysztof.studio.config.ApiConfig.ORGANIZATION_NAME_MIN_LENGTH;

public @Data class Organization {

    @WhiteSpaceCheck
    @NotNull
    @Size(min = ORGANIZATION_NAME_MIN_LENGTH, message = "Too few characters. Minimum is " + ORGANIZATION_NAME_MIN_LENGTH + ".")
    @Size(max = ORGANIZATION_NAME_MAX_LENGTH, message = "Not enough characters. Maximum is " + ORGANIZATION_NAME_MAX_LENGTH + ".")
    private String name;

}
