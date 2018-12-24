package com.krzysztof.studio.model;

import lombok.Data;

public @Data class Phone {

    private int internalNumber;
    private String externalNumber;
    private PhoneInterface phoneInterface;
}
