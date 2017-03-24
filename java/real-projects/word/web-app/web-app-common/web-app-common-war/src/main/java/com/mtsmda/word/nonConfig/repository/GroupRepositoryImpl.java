package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.user.model.Group;
import com.mtsmda.real.project.user.rowmapper.GroupRowMapper;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName;
import com.mtsmda.real.project.user.rowmapper.UserRowMapper;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.nonConfig.repository.query.QueryWarehouse;
import org.springframework.stereotype.Repository;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.GroupsT.T_GROUPS;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.GroupsT.T_GROUPS_F_GROUP_NAME;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.UserAttemptT.T_USER_ATTEMPTS_F_ACCOUNT_USER_ID;

/**
 * Created by dminzat on 3/23/2017.
 */
@Repository
public class GroupRepositoryImpl extends ParentRepository implements GroupRepository {

    @Override
    public CommonResponse<Group> getGroupByName(String groupName) {
        setQuery(QueryWarehouse.GroupQuery.QUERY_GET_GROUP_BY_NAME);
        clearParamIfNotEmpty();
        params.put(T_GROUPS_F_GROUP_NAME, groupName);
        try{
            return new CommonResponse<>(getNamedParameterJdbcTemplate().queryForObject(getQuery(), params, new GroupRowMapper()), CommonResponse.SUCCESS);
        }
        catch (Exception e){
            return exceptionHandler(CommonResponse.ERROR, e, Group.class);
        }
    }

}