package com.krzysztof.studio.model.db;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
public class DbOrganization {

    @Id
    private String name;

    public DbOrganization(String name) {
        this.name = name;
    }
}
