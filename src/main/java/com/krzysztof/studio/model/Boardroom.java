package com.krzysztof.studio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.krzysztof.studio.validation.InRange;
import com.krzysztof.studio.validation.WhiteSpaceCheck;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.krzysztof.studio.config.ApiConfig.BOARDROOM_ID_MAX_LENGTH;
import static com.krzysztof.studio.config.ApiConfig.BOARDROOM_ID_MIN_LENGTH;
import static com.krzysztof.studio.config.ApiConfig.BOARDROOM_MAX_LEVEL;
import static com.krzysztof.studio.config.ApiConfig.BOARDROOM_MIN_LEVEL;
import static com.krzysztof.studio.config.ApiConfig.BOARDROOM_NAME_MAX_LENGTH;
import static com.krzysztof.studio.config.ApiConfig.BOARDROOM_NAME_MIN_LENGTH;

@Entity
@Table
@Data
public class Boardroom {

    @Id
    @NotNull
    @WhiteSpaceCheck
    @Size(min = BOARDROOM_NAME_MIN_LENGTH, message = "Not enough characters. Minimum is " + BOARDROOM_NAME_MIN_LENGTH + ".")
    @Size(max = BOARDROOM_NAME_MAX_LENGTH, message = "Too many characters. Maximum is " + BOARDROOM_NAME_MAX_LENGTH + ".")
    private String name;
    @JsonInclude(NON_NULL)
    @WhiteSpaceCheck
    @Size(min = BOARDROOM_ID_MIN_LENGTH, message = "Not enough characters. Minimum is " + BOARDROOM_ID_MIN_LENGTH + ".")
    @Size(max = BOARDROOM_ID_MAX_LENGTH, message = "Too many characters. Maximum is " + BOARDROOM_ID_MAX_LENGTH + ".")
    private String id;
    private String organizationName;
    @InRange(
            min = BOARDROOM_MIN_LEVEL,
            max = BOARDROOM_MAX_LEVEL,
            message = "Possibly levels range is between " + BOARDROOM_MIN_LEVEL + " to " + BOARDROOM_MAX_LEVEL + "."
    )
    private int level;
    private boolean available;
    @NotNull
    @PositiveOrZero
    private Integer seats;
    @NotNull
    @PositiveOrZero
    private Integer standingPlaces;
    @JsonInclude(NON_DEFAULT)
    @PositiveOrZero
    private Integer sunbeds;
    @JsonInclude(NON_DEFAULT)
    @PositiveOrZero
    private Integer hammocks;
    @JsonInclude(NON_DEFAULT)
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Equipment equipment;
}
