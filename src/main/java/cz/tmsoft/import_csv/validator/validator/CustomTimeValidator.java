package cz.tmsoft.import_csv.validator.validator;

import cz.tmsoft.import_csv.api.v1.CallFilterRest;
import cz.tmsoft.import_csv.validator.CustomTimeValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomTimeValidator implements ConstraintValidator<CustomTimeValid, CallFilterRest> {

    @Override
    public void initialize(CustomTimeValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CallFilterRest value, ConstraintValidatorContext context) {
        if (value == null || (value.getEndTime() == null || value.getStartTime() == null)) {
            return true;
        }

        LocalDateTime starTime = LocalDateTime.parse( value.getStartTime(), DateTimeFormatter.ofPattern( "dd.MM.yyyy HH:mm:ss" ) );
        LocalDateTime endTime = LocalDateTime.parse( value.getEndTime(), DateTimeFormatter.ofPattern( "dd.MM.yyyy HH:mm:ss" ) );

        Duration duration = Duration.between(starTime, endTime);

        return duration.toDays() >= 30 ? false : true;
    }
}
