package com.mtsmda.word.repository;

import com.mtsmda.real.project.user.model.User;
import com.mtsmda.real.project.user.rowmapper.UserRowMapper;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.repository.query.QueryWarehouse.UserQuery;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.UserAttemptT.T_USER_ATTEMPTS_F_ACCOUNT_USER_ID;

/**
 * Created by dminzat on 3/6/2017.
 */
@Repository
public class UserRepositoryImpl extends ParentRepository implements UserRepository {

    @Override
    public CommonResponse<User> getUserByUsername(String username) {
        setQuery(UserQuery.QUERY_GET_USER_BY_USERNAME);
        clearParamIfNotEmpty();
        params.put(T_USER_ATTEMPTS_F_ACCOUNT_USER_ID, username);
        try {
            return new CommonResponse<>(getNamedParameterJdbcTemplate().queryForObject(getQuery(), params, new UserRowMapper()), CommonResponse.SUCCESS);
        } catch (Exception e) {
            return exceptionHandler(CommonResponse.ERROR, e, User.class);
        }
    }

    @Override
    public <T> void setLogger() {
        LOGGER = Logger.getLogger(this.getClass());
    }
}