package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.Gender;
import com.mtsmda.real.project.user.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.UserT.*;

/**
 * Created by dminzat on 3/6/2017.
 */
public class UserRowMapper implements RowMapper<User> {

    private boolean rowMapperAccount = false;
    private boolean rowMapperGroup = false;

    public UserRowMapper() {

    }

    public UserRowMapper(boolean rowMapperAccount) {
        this.rowMapperAccount = rowMapperAccount;
    }

    public UserRowMapper(boolean rowMapperAccount, boolean rowMapperGroup) {
        this.rowMapperAccount = rowMapperAccount;
        this.rowMapperGroup = rowMapperGroup;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        try {
            user.setUserId(rs.getInt(T_USERS_F_USER_ID));
        } catch (Exception e) {
            user.setUserId(null);
        }

        try {
            user.setUserFirstName(rs.getString(T_USERS_F_USER_FIRSTNAME));
        } catch (Exception e) {
            user.setUserFirstName(null);
        }

        try {
            user.setUserLastName(rs.getString(T_USERS_F_USER_LASTNAME));
        } catch (Exception e) {
            user.setUserLastName(null);
        }

        try {
            user.setUserMiddleName(rs.getString(T_USERS_F_USER_MIDDLENAME));
        } catch (Exception e) {
            user.setUserMiddleName(null);
        }

        try {
            user.setUserEmail(rs.getString(T_USERS_F_USER_EMAIL));
        } catch (Exception e) {
            user.setUserEmail(null);
        }

        try {
            user.setUserPhone(rs.getString(T_USERS_F_USER_PHONE));
        } catch (Exception e) {
            user.setUserPhone(null);
        }

        try {
            user.setUserGender(Gender.valueOf(rs.getString(T_USERS_F_USER_GENDER)));
        } catch (Exception e) {
            user.setUserGender(null);
        }

        try {
            user.setUserDateOfBirth(rs.getTimestamp(T_USERS_F_USER_DATE_OF_BIRTH).toLocalDateTime().toLocalDate());
        } catch (Exception e) {
            user.setUserDateOfBirth(null);
        }


        try {
            user.setUserIsActive(rs.getBoolean(T_USERS_F_USER_ACTIVE));
        } catch (Exception e) {
            user.setUserIsActive(null);
        }

        try {
            user.setUserSiteURL(rs.getString(T_USERS_F_USER_SITE));
        } catch (Exception e) {
            user.setUserSiteURL(null);
        }

        try {
            user.setAddUserLocalDateTime(rs.getTimestamp(T_USERS_F_ADD_USER_DATE_TIME).toLocalDateTime());
        } catch (Exception e) {
            user.setAddUserLocalDateTime(null);
        }

        if (this.rowMapperAccount) {
            try {
                user.setAccount(new AccountRowMapper().mapRow(rs, rowNum));
            } catch (Exception e) {
                user.setAccount(null);
            }
        }

        if (this.rowMapperGroup) {
            try {
                user.setGroup(new GroupRowMapper().mapRow(rs, rowNum));
            } catch (Exception e) {
                user.setGroup(null);
            }
        }

        return user;
    }

}