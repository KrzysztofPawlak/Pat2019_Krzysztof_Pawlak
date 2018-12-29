package com.krzysztof.studio.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public @Data class Phone {

    @Positive
    @Max(value = 100, message = "The value must be less then 100")
    private int internalNumber;
    @Pattern(regexp="^\\+[0-9]{2}\\s[0-9]{9}$", message = "incorrect external number")
    private String externalNumber;
    private PhoneInterface phoneInterface;
}
