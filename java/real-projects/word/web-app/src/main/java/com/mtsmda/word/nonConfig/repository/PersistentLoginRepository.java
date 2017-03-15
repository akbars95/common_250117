package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.user.model.PersistentLogin;
import com.mtsmda.spring.helper.response.CommonResponse;

/**
 * Created by dminzat on 3/7/2017.
 */
public interface PersistentLoginRepository {

    CommonResponse<PersistentLogin> getPersistentLoginBySeries(String series);
    CommonResponse<Boolean> insertPersistentLogin(PersistentLogin persistentLogin);
    CommonResponse<Boolean> updatePersistentLogin(PersistentLogin persistentLogin);
    CommonResponse<Boolean> deletePersistentLoginByUsername(String username);

}