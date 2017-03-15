package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.user.model.UserAttempt;

/**
 * Created by dminzat on 3/1/2017.
 */
public interface UserDetailRepository {

    void updateFailAttempts(String username);
    void resetFailAttempts(String username);
    UserAttempt getUserAttempts(String username);

}