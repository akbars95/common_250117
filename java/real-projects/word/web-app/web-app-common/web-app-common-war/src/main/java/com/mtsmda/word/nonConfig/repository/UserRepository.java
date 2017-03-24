package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.user.model.User;
import com.mtsmda.spring.helper.response.CommonResponse;

/**
 * Created by dminzat on 3/6/2017.
 */
public interface UserRepository {

    CommonResponse<Boolean> insertUser(User user);
    CommonResponse<Boolean> insertUserGroup(User user);
    CommonResponse<User> getUserByUsername(String username);
    CommonResponse<User> getLastAddUserBy();
    CommonResponse<User> getUserAndAccountByUsername(String username);

}