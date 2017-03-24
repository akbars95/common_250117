package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.helper.*;
import com.mtsmda.real.project.user.model.User;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName;
import com.mtsmda.real.project.user.rowmapper.UserRowMapper;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.nonConfig.repository.query.QueryWarehouse.SpringSecurityQuery;
import com.mtsmda.word.nonConfig.repository.query.QueryWarehouse.UserQuery;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mtsmda.helper.LocalDateTimeHelper.NORMAL_DATE_FORMAT;
import static com.mtsmda.helper.QueryCreatorHelper.TO_DATE_ONLY_DATE_ORACLE_PATTERN;
import static com.mtsmda.helper.QueryCreatorHelper.getToDateOracleAsParam;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.GroupUsersT.T_GROUP_USERS;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.GroupUsersT.T_GROUP_USERS_F_GROUP_ID;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.UserAttemptT.T_USER_ATTEMPTS_F_ACCOUNT_USER_ID;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.UserT.*;
import static com.mtsmda.spring.helper.response.CommonResponse.ERROR;
import static com.mtsmda.spring.helper.response.CommonResponse.SUCCESS;

/**
 * Created by dminzat on 3/6/2017.
 */
@Repository
public class UserRepositoryImpl extends ParentRepository implements UserRepository {

    @Override
    public CommonResponse<Boolean> insertUser(User user) {
        List<String> listWithParams = ListHelper.getListWithData(T_USERS_F_USER_FIRSTNAME, T_USERS_F_USER_LASTNAME,
                T_USERS_F_USER_MIDDLENAME, T_USERS_F_USER_EMAIL, T_USERS_F_USER_PHONE, T_USERS_F_USER_GENDER,
                T_USERS_F_USER_DATE_OF_BIRTH, T_USERS_F_USER_ACTIVE);
        try {
            setQuery(QueryCreatorHelper.insertGenerateWithDate(T_USERS, listWithParams,
                    ListHelper.getListWithData(getToDateOracleAsParam(T_USERS_F_USER_DATE_OF_BIRTH, TO_DATE_ONLY_DATE_ORACLE_PATTERN))));
            params = MapHelper.getMap(listWithParams, ListHelper.getListWithData(user.getUserFirstName(), user.getUserLastName(), user.getUserMiddleName(),
                    user.getUserEmail(), user.getUserPhone(), user.getUserGender().name(),
                    LocalDateTimeHelper.convertLocalDateToString(user.getUserDateOfBirth(), NORMAL_DATE_FORMAT),
                    user.getUserIsActive() ? "T" : "F"));
            setSuccess(isSuccess(getNamedParameterJdbcTemplate().update(getQuery(), params)));
            return resultSuccessCUD(SUCCESS);
        } catch (Exception e) {
            return exceptionHandlerCUD(ERROR, e);
        }
    }

    @Override
    public CommonResponse<Boolean> insertUserGroup(User user) {
        List<String> listWithParams = ListHelper.getListWithData(T_GROUP_USERS_F_GROUP_ID, T_USERS_F_USER_ID);
        try {
            setQuery(QueryCreatorHelper.insertGenerate(T_GROUP_USERS, listWithParams));
            params = MapHelper.getMap(listWithParams, ListHelper.getListWithData(user.getGroup().getGroupId(),
                    user.getUserId()));
            setSuccess(isSuccess(getNamedParameterJdbcTemplate().update(getQuery(), params)));
            return resultSuccessCUD(SUCCESS);
        } catch (Exception e) {
            return exceptionHandlerCUD(ERROR, e);
        }
    }

    @Override
    public CommonResponse<User> getUserByUsername(String username) {
        setQuery(UserQuery.QUERY_GET_USER_BY_USERNAME);
        clearParamIfNotEmpty();
        params.put(T_USER_ATTEMPTS_F_ACCOUNT_USER_ID, username);
        try {
            return new CommonResponse<>(getNamedParameterJdbcTemplate().queryForObject(getQuery(), params, new UserRowMapper()), SUCCESS);
        } catch (Exception e) {
            return exceptionHandler(CommonResponse.ERROR, e, User.class);
        }
    }

    @Override
    public CommonResponse<User> getLastAddUserBy() {
        setQuery(UserQuery.QUERY_GET_LAST_ADD_USER);
        clearParamIfNotEmpty();
        try {
            return new CommonResponse<>(getNamedParameterJdbcTemplate().queryForObject(getQuery(), params, new UserRowMapper()), SUCCESS);
        } catch (Exception e) {
            return exceptionHandler(CommonResponse.ERROR, e, User.class);
        }
    }

    @Override
    public CommonResponse<User> getUserAndAccountByUsername(String username) {
        setQuery(SpringSecurityQuery.QUERY_USER_BY_USERNAME);
        clearParamIfNotEmpty();
        params.put(T_USER_ATTEMPTS_F_ACCOUNT_USER_ID, username);
        try {
            return new CommonResponse<>(getNamedParameterJdbcTemplate().queryForObject(getQuery(), params, new UserRowMapper()), SUCCESS);
        } catch (Exception e) {
            return exceptionHandler(CommonResponse.ERROR, e, User.class);
        }
    }

}