package ru.yandex.practicum.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class InTwoHoursValidator implements ConstraintValidator<InTwoHours, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        if (localDateTime == null) {
            return true;
        }
        LocalDateTime inTwoHoursTime = LocalDateTime.now().plusHours(2);

        return localDateTime.equals(inTwoHoursTime) || localDateTime.isAfter(inTwoHoursTime);
    }
}
