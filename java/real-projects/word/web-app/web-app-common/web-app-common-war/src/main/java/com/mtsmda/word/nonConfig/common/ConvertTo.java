package com.mtsmda.word.nonConfig.common;

/**
 * Created by dminzat on 3/9/2017.
 */
@FunctionalInterface
public interface ConvertTo<TO> {
    TO convert();
}