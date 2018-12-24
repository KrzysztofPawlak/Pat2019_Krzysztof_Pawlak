package com.krzysztof.studio.model;

import lombok.Data;

public @Data class Boardroom {

    private String name;
    private String id;
    private int level;
    private boolean available;
    private int seats;
    private int sunbeds;
    private int hammocks;
    private Equipment equipment;
}
