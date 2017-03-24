package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.spring.helper.response.CommonResponse;

/**
 * Created by dminzat on 3/22/2017.
 */
public interface AccountRepository {

    CommonResponse<Boolean> insertAccount(Account account);

}