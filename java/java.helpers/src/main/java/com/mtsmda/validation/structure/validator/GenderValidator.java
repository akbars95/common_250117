package com.mtsmda.validation.structure.validator;

import com.mtsmda.validation.structure.constraint.CheckLocalDateTime;
import com.mtsmda.validation.structure.constraint.DateEnum;
import com.mtsmda.validation.structure.constraint.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by dminzat on 9/1/2016.
 */
public class GenderValidator implements ConstraintValidator<Gender, String> {

    @Override
    public void initialize(Gender gender) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            com.mtsmda.pattern.Gender.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}