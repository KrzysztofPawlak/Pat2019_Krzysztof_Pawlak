package com.krzysztof.studio.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { PhoneInterfaceValidator.class })
public @interface PhoneInterfaceEnum {
    String message() default "Invalid phone interface value.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?extends java.lang.Enum<?>> enumClass();
    boolean ignoreCase() default false;
}
