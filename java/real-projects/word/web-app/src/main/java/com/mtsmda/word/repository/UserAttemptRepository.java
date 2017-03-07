package com.mtsmda.word.repository;

import com.mtsmda.real.project.user.model.UserAttempt;
import com.mtsmda.spring.helper.response.CommonResponse;

/**
 * Created by dminzat on 3/6/2017.
 */
public interface UserAttemptRepository {

    CommonResponse<UserAttempt> getUserAttemptByUsername(String username);

}