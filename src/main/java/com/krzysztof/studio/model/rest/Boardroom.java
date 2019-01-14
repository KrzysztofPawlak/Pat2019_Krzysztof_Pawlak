package com.krzysztof.studio.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.krzysztof.studio.model.Equipment;
import com.krzysztof.studio.validation.WhiteSpaceCheck;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.krzysztof.studio.config.ApiConfig.*;
import static com.krzysztof.studio.config.ApiConfig.BOARDROOM_MAX_LEVEL;

@Data
public class Boardroom {

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
    @Min(value = BOARDROOM_MIN_LEVEL, message = "Incorrect. Minimum levels is " + BOARDROOM_MIN_LEVEL + ".")
    @Max(value = BOARDROOM_MAX_LEVEL, message = "Incorrect. Maximum levels is " + BOARDROOM_MAX_LEVEL + ".")
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
    private Equipment equipment;
}
