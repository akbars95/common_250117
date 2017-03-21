package com.mtsmda.word.nonConfig.service;

import com.mtsmda.word.nonConfig.common.LoggerAC;
import com.mtsmda.word.nonConfig.common.LoggerI;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dminzat on 2/15/2017.
 * <ol>
 * <li>1 - validation</li>
 * <li>2 - conversion</li>
 * <li>3 - business logic</li>
 * </ol>
 */
@Transactional(isolation = Isolation.READ_COMMITTED)
public abstract class ParentService extends LoggerAC{

}