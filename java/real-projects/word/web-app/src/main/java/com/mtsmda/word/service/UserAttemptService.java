package com.mtsmda.word.service;

import com.mtsmda.real.project.user.model.UserAttempt;

/**
 * Created by dminzat on 3/6/2017.
 */
public interface UserAttemptService {

    UserAttempt getUserAttemptByUsername(String username);

}