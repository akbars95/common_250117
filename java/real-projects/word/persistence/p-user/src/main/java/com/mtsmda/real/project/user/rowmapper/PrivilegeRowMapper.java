package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.Privilege;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.PrivilegeT.T_PRIVILEGES_F_PRIVILEGE_ID;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.PrivilegeT.T_PRIVILEGES_F_PRIVILEGE_NAME;

/**
 * Created by dminzat on 3/11/2017.
 */
public class PrivilegeRowMapper implements RowMapper<Privilege>{

    @Override
    public Privilege mapRow(ResultSet rs, int rowNum) throws SQLException {
        Privilege privilege = new Privilege();

        try {
            privilege.setPrivilegeId(rs.getInt(T_PRIVILEGES_F_PRIVILEGE_ID));
        } catch (Exception e) {
            privilege.setPrivilegeId(null);
        }

        try {
            privilege.setPrivilegeName(rs.getString(T_PRIVILEGES_F_PRIVILEGE_NAME));
        } catch (Exception e) {
            privilege.setPrivilegeName(null);
        }

        return privilege;
    }
}