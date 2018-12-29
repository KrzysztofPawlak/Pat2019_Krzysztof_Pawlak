package com.krzysztof.studio.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { RangeValidator.class })
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface InRange {
    String message() default "value is out of range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}
