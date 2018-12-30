package com.krzysztof.studio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.krzysztof.studio.validation.PhoneInterfaceEnum;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.krzysztof.studio.config.ApiConfig.ACCEPTABLE_EXTERNAL_NUMBER_FORMAT;
import static com.krzysztof.studio.config.ApiConfig.PHONE_MAX_INTERNAL_NUMBER;

public @Data
class Phone {

    @JsonInclude(NON_DEFAULT)
    @Positive
    @Max(value = PHONE_MAX_INTERNAL_NUMBER, message = "The max internal number must be less then " + PHONE_MAX_INTERNAL_NUMBER + ".")
    private Integer internalNumber;
    @JsonInclude(NON_DEFAULT)
    @Pattern(regexp = ACCEPTABLE_EXTERNAL_NUMBER_FORMAT, message = "Incorrect external number.")
    private String externalNumber;
    @JsonInclude(NON_DEFAULT)
    @PhoneInterfaceEnum(enumClass = PhoneInterface.class)
    private String phoneInterface;
}
