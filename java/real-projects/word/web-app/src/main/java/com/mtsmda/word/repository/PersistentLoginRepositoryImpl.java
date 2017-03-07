package com.mtsmda.word.repository;

import com.mtsmda.real.project.user.model.PersistentLogin;
import com.mtsmda.spring.helper.response.CommonResponse;
import org.apache.log4j.Logger;

/**
 * Created by dminzat on 3/7/2017.
 */
public class PersistentLoginRepositoryImpl extends ParentRepository implements PersistentLoginRepository {

    @Override
    public CommonResponse<PersistentLogin> getPersistentLoginBySeries(String series) {
        return null;
    }

    @Override
    public CommonResponse<Boolean> insertPersistentLogin(PersistentLogin persistentLogin) {
        return null;
    }

    @Override
    public CommonResponse<Boolean> updatePersistentLogin(PersistentLogin persistentLogin) {
        return null;
    }

    @Override
    public CommonResponse<Boolean> deletePersistentLoginByUsername(String username) {
        return null;
    }

    @Override
    public <T> void setLogger() {
        LOGGER = Logger.getLogger(this.getClass());
    }
}