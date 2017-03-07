package com.mtsmda.word.service;

import com.mtsmda.word.common.LoggerI;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dminzat on 2/15/2017.
 */
@Transactional(isolation = Isolation.READ_COMMITTED)
public abstract class ParentService implements LoggerI{

    protected static Logger LOGGER = null;

    public ParentService() {
        setLogger();
    }

}