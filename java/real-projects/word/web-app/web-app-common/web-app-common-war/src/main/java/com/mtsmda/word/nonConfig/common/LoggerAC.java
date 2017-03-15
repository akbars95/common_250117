package com.mtsmda.word.nonConfig.common;

import org.apache.log4j.Logger;

/**
 * Created by dminzat on 3/14/2017.
 */
public abstract class LoggerAC implements LoggerI {

    protected static Logger LOGGER = null;

    protected LoggerAC() {
        setLogger();
    }

    @Override
    public <T> void setLogger() {
        LOGGER = Logger.getLogger(this.getClass());
    }
}