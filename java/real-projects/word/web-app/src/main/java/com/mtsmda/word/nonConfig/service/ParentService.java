package com.mtsmda.word.nonConfig.service;

import com.mtsmda.word.nonConfig.common.LoggerI;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Isolation;
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