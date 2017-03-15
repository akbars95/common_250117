package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.user.model.UserAttempt;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName;
import com.mtsmda.real.project.user.rowmapper.UserAttemptRowMapper;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.nonConfig.repository.query.QueryWarehouse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by dminzat on 3/6/2017.
 */
@Repository
public class UserAttemptRepositoryImpl extends ParentRepository implements UserAttemptRepository{

    @Override
    public CommonResponse<UserAttempt> getUserAttemptByUsername(String username) {
        setQuery(QueryWarehouse.UserAttemptQuery.QUERY_GET_USER_ATTEMPT_BY_USERNAME);
        clearParamIfNotEmpty();
        params.put(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME, username);
        try{
            return new CommonResponse<>(getNamedParameterJdbcTemplate().queryForObject(getQuery(), params, new UserAttemptRowMapper()), CommonResponse.SUCCESS);
        }
        catch (Exception e){
            return exceptionHandler(CommonResponse.ERROR, e, UserAttempt.class);
        }
    }

    @Override
    public <T> void setLogger() {
        LOGGER = Logger.getLogger(UserAttemptRepositoryImpl.class);
    }
}