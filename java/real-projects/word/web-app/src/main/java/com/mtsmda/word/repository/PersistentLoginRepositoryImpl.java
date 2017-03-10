package com.mtsmda.word.repository;

import com.mtsmda.helper.LocalDateTimeHelper;
import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.real.project.user.model.PersistentLogin;
import com.mtsmda.real.project.user.rowmapper.PersistentLoginRowMapper;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.repository.query.QueryWarehouse.PersistentLoginQuery;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by dminzat on 3/7/2017.
 */
@Repository
public class PersistentLoginRepositoryImpl extends ParentRepository implements PersistentLoginRepository {

    @Override
    public CommonResponse<PersistentLogin> getPersistentLoginBySeries(String series) {
        setQuery(PersistentLoginQuery.QUERY_SELECT_PERSISTENT_LOGIN_BY_SERIES);
        clearParamIfNotEmpty();
        params.put(TableAndFieldsName.PersistentLoginT.T_PERSISTENT_LOGINS_F_SERIES, series);
        try {
            return new CommonResponse<>(getNamedParameterJdbcTemplate().queryForObject(getQuery(), params, new PersistentLoginRowMapper(true)), CommonResponse.SUCCESS);
        } catch (Exception e) {
            return exceptionHandler(CommonResponse.ERROR, e, PersistentLogin.class);
        }
    }

    @Override
    public CommonResponse<Boolean> insertPersistentLogin(PersistentLogin persistentLogin) {
        setQuery(PersistentLoginQuery.QUERY_INSERT_PERSISTENT_LOGIN);
        clearParamIfNotEmpty();
        params.put(TableAndFieldsName.PersistentLoginT.T_PERSISTENT_LOGINS_F_SERIES, persistentLogin.getSeries());
        params.put(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME, persistentLogin.getAccount().getAccountUsername());
        params.put(TableAndFieldsName.PersistentLoginT.T_PERSISTENT_LOGINS_F_TOKEN, persistentLogin.getToken());
        params.put(TableAndFieldsName.PersistentLoginT.T_PERSISTENT_LOGINS_F_LAST_USED, LocalDateTimeHelper.convertLocalDateTimeToStringForOracle(persistentLogin.getLastUsed()));
        try {
            return new CommonResponse<>(isSuccess(getNamedParameterJdbcTemplate().update(getQuery(), params)), CommonResponse.SUCCESS);
        } catch (Exception e) {
            return exceptionHandlerCUD(CommonResponse.ERROR, e);
        }
    }

    @Override
    public CommonResponse<Boolean> updatePersistentLogin(PersistentLogin persistentLogin) {
        setQuery(PersistentLoginQuery.QUERY_UPDATE_PERSISTENT_LOGIN_TOKEN_AND_LAST_USER_BY_SERIES);
        clearParamIfNotEmpty();
        params.put(TableAndFieldsName.PersistentLoginT.T_PERSISTENT_LOGINS_F_SERIES, persistentLogin.getSeries());
        params.put(TableAndFieldsName.PersistentLoginT.T_PERSISTENT_LOGINS_F_TOKEN, persistentLogin.getToken());
        params.put(TableAndFieldsName.PersistentLoginT.T_PERSISTENT_LOGINS_F_LAST_USED, LocalDateTimeHelper.convertLocalDateTimeToStringForOracle(persistentLogin.getLastUsed()));
        try {
            return new CommonResponse<>(isSuccess(getNamedParameterJdbcTemplate().update(getQuery(), params)), CommonResponse.SUCCESS);
        } catch (Exception e) {
            return exceptionHandlerCUD(CommonResponse.ERROR, e);
        }
    }

    @Override
    public CommonResponse<Boolean> deletePersistentLoginByUsername(String username) {
        setQuery(PersistentLoginQuery.QUERY_DELETE_BY_USERNAME);
        clearParamIfNotEmpty();
        params.put(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME, username);
        try {
            return new CommonResponse<>(isSuccess(getNamedParameterJdbcTemplate().update(getQuery(), params)), CommonResponse.SUCCESS);
        } catch (Exception e) {
            return exceptionHandlerCUD(CommonResponse.ERROR, e);
        }
    }

    @Override
    public <T> void setLogger() {
        LOGGER = Logger.getLogger(this.getClass());
    }
}