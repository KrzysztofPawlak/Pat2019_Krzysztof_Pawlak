package com.krzysztof.studio.model;

import lombok.Data;

public @Data class Equipment {

    private String projectorName;
    private boolean phoneAvailable;
    private Phone phone;
}
