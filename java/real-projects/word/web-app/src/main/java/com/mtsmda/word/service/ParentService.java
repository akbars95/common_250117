package com.mtsmda.word.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dminzat on 2/15/2017.
 */
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ParentService {

}