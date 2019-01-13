package com.krzysztof.studio.config.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private String description;
}
