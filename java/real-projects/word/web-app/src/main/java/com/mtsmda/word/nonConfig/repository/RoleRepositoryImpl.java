package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.user.model.Role;
import com.mtsmda.real.project.user.rowmapper.RoleRowMapper;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.AccountT;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.nonConfig.repository.query.QueryWarehouse;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dminzat on 3/13/2017.
 */
@Repository("roleRepositoryImpl")
public class RoleRepositoryImpl extends ParentRepository implements RoleRepository {

    @Override
    public CommonResponse<List<Role>> getUserRolesByUsername(String username) {
        setQuery(QueryWarehouse.SpringSecurityQuery.QUERY_GET_USER_ROLES_BY_USERNAME_GROUP_AUTHORITY);
        clearParamIfNotEmpty();
        params.put(AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME, username);
        try {
            return new CommonResponse<>(getNamedParameterJdbcTemplate().query(getQuery(), params, new RoleRowMapper()), CommonResponse.SUCCESS);
        } catch (Exception e) {
            return exceptionHandlerReturnList(CommonResponse.ERROR, e, Role.class);
        }
    }

    @Override
    public <T> void setLogger() {
        LOGGER = Logger.getLogger(this.getClass());
    }
}