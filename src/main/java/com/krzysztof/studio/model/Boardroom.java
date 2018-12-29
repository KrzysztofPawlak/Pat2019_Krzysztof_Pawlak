package com.krzysztof.studio.model;

import com.krzysztof.studio.validation.InRange;
import lombok.Data;

public @Data class Boardroom {

    private String name;
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
