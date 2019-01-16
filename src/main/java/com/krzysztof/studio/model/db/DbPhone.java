package com.krzysztof.studio.model.db;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Data
public class DbPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer internalNumber;
    private String externalNumber;
    private String phoneInterface;
}
