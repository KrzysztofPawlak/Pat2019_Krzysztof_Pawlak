package com.krzysztof.studio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Entity
@Table
@Data
public class Equipment {

    @Id
    private UUID id;
    @JsonInclude(NON_DEFAULT)
    private String projectorName;
    private boolean phoneAvailable;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Phone phone;

    @JsonInclude(NON_NULL)
    public Phone getPhone() {
        return phoneAvailable ? phone : null;
    }
}
