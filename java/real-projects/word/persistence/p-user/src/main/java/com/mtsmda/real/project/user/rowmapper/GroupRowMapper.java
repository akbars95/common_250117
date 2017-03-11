package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dminzat on 3/11/2017.
 */
public class GroupRowMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();

        try {
            group.setGroupId(rs.getInt(TableAndFieldsName.GroupsT.T_GROUPS_F_GROUP_ID));
        } catch (Exception e) {
            group.setGroupId(null);
        }

        try {
            group.setGroupName(rs.getString(TableAndFieldsName.GroupsT.T_GROUPS_F_GROUP_NAME));
        } catch (Exception e) {
            group.setGroupName(null);
        }

        return group;
    }
}