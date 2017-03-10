package com.mtsmda.word.common;

/**
 * Created by dminzat on 3/9/2017.
 */
@FunctionalInterface
public interface Converter<FROM, TO> {
    TO convert(FROM from);
}