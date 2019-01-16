package com.krzysztof.studio.model.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class DbBoardroom {

    @Id
    private String name;
    private String id;
    private String organizationName;
    private int level;
    private boolean available;
    private Integer seats;
    private Integer standingPlaces;
    private Integer sunbeds;
    private Integer hammocks;
    @OneToOne(cascade = CascadeType.ALL)
    private DbEquipment equipment;

}
