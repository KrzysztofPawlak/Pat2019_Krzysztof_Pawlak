package com.krzysztof.studio.model;

import com.krzysztof.studio.validation.InRange;
import lombok.Data;

import javax.validation.constraints.Size;

public @Data class Boardroom {

    @Size(min = 2, message = "Too few characters. Minimum is 2.")
    @Size(min = 10, message = "Too many characters. Maximum is 20.")
    private String name;
    @Size(min = 2, message = "Too few characters. Minimum is 2.")
    @Size(min = 10, message = "Too many characters. Maximum is 20.")
    private String id;
    @InRange(
            min = 0,
            max = 10,
            message = "Possibly level range is between 0-10."
    )
    private int level;
    private boolean available;
    private int seats;
    private int sunbeds;
    private int hammocks;
    private Equipment equipment;
}
