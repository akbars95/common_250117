package com.mtsmda.real.project.user.annotation;

import java.lang.annotation.*;

/**
 * Created by dminzat on 3/2/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Inherited
public @interface Column {

    public String name() default "";

}