package cz.tmsoft.import_csv.validator;

import cz.tmsoft.import_csv.validator.validator.CustomTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CustomTimeValidator.class)
@Documented
public @interface CustomTimeValid {
    String message() default "{different between start time and end time must max 30 days}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
