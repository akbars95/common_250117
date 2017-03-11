package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.RoleT.T_ROLES_F_ROLE_ID;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.RoleT.T_ROLES_F_ROLE_NAME;

/**
 * Created by dminzat on 3/11/2017.
 */
public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();

        try {
            role.setRoleId(rs.getInt(T_ROLES_F_ROLE_ID));
        } catch (Exception e) {
            role.setRoleId(null);
        }

        try {
            role.setRoleName(rs.getString(T_ROLES_F_ROLE_NAME));
        } catch (Exception e) {
            role.setRoleName(null);
        }

        return role;
    }
}