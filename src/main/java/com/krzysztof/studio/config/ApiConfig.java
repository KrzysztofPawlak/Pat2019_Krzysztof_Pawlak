package com.krzysztof.studio.config;

public final class ApiConfig {

    public static final int ORGANIZATION_NAME_MIN_LENGTH = 2;
    public static final int ORGANIZATION_NAME_MAX_LENGTH = 20;

    public static final int BOARDROOM_NAME_MIN_LENGTH = 2;
    public static final int BOARDROOM_NAME_MAX_LENGTH = 20;

    public static final int BOARDROOM_ID_MIN_LENGTH = 2;
    public static final int BOARDROOM_ID_MAX_LENGTH = 20;

    public static final int BOARDROOM_MIN_LEVEL = 0;
    public static final int BOARDROOM_MAX_LEVEL = 10;

    public static final int PHONE_MAX_INTERNAL_NUMBER = 100;

    public static final int RESERVATION_MIN_LENGTH = 2;
    public static final int RESERVATION_MAX_LENGTH = 20;

    public static final String ACCEPTABLE_EXTERNAL_NUMBER_FORMAT = "^\\+[0-9]{2}\\s[0-9]{9}$";
}
