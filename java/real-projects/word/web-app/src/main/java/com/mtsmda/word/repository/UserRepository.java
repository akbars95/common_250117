package com.mtsmda.word.repository;

import com.mtsmda.real.project.user.model.User;
import com.mtsmda.spring.helper.response.CommonResponse;

/**
 * Created by dminzat on 3/6/2017.
 */
public interface UserRepository {

    CommonResponse<User> getUserByUsername(String username);

}