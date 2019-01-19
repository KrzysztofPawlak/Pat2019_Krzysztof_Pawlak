package com.krzysztof.studio.model.db;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@NoArgsConstructor
public class DbBoardroom {

    @Id
    private String name;
    private String id;
    @ManyToOne
    private DbOrganization organization;
    private int level;
    private boolean available;
    private Integer seats;
    private Integer standingPlaces;
    private Integer sunbeds;
    private Integer hammocks;
    @OneToOne(cascade = CascadeType.ALL)
    private DbEquipment equipment;

    public DbBoardroom(String name) {
        this.name = name;
    }
}
