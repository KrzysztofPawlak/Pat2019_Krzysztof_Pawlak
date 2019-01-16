package com.krzysztof.studio.model.db;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class DbOrganization {

    @Id
    private String name;

}
