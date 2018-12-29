package com.krzysztof.studio.model;

import com.krzysztof.studio.validation.WhiteSpaceCheck;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public @Data class Organization {

    @WhiteSpaceCheck
    @NotNull
    @Size(min = 2, message = "Too few characters. Minimum is 2.")
    @Size(max = 10, message = "Too many characters. Maximum is 20.")
    private String name;

}
