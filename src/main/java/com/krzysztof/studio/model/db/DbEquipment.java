package com.krzysztof.studio.model.db;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Data
public class DbEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String projectorName;
    private boolean phoneAvailable;
    @OneToOne(cascade = CascadeType.ALL)
    private DbPhone phone;

    public DbPhone getPhone() {
        return phoneAvailable ? phone : null;
    }
}
