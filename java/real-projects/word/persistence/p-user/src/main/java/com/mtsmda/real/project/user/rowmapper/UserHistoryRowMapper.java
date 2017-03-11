package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.Gender;
import com.mtsmda.real.project.user.model.UserHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.UserHistoryT.*;

/**
 * Created by dminzat on 3/11/2017.
 */
public class UserHistoryRowMapper implements RowMapper<UserHistory> {

    @Override
    public UserHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserHistory userHistory = new UserHistory();

        try {
            userHistory.setUserId(rs.getInt(T_USERS_HISTORY_F_USER_ID));
        } catch (Exception e) {
            userHistory.setUserId(null);
        }

        try {
            userHistory.setUserFirstName(rs.getString(T_USERS_HISTORY_F_USER_FIRSTNAME));
        } catch (Exception e) {
            userHistory.setUserFirstName(null);
        }

        try {
            userHistory.setUserLastName(rs.getString(T_USERS_HISTORY_F_USER_LASTNAME));
        } catch (Exception e) {
            userHistory.setUserLastName(null);
        }

        try {
            userHistory.setUserMiddleName(rs.getString(T_USERS_HISTORY_F_USER_MIDDLENAME));
        } catch (Exception e) {
            userHistory.setUserMiddleName(null);
        }

        try {
            userHistory.setUserEmail(rs.getString(T_USERS_HISTORY_F_USER_EMAIL));
        } catch (Exception e) {
            userHistory.setUserEmail(null);
        }

        try {
            userHistory.setUserPhone(rs.getString(T_USERS_HISTORY_F_USER_PHONE));
        } catch (Exception e) {
            userHistory.setUserPhone(null);
        }

        try {
            userHistory.setUserGender(Gender.valueOf(rs.getString(T_USERS_HISTORY_F_USER_GENDER)));
        } catch (Exception e) {
            userHistory.setUserGender(null);
        }

        try {
            userHistory.setUserDateOfBirth(rs.getTimestamp(T_USERS_HISTORY_F_USER_DATE_OF_BIRTH).toLocalDateTime().toLocalDate());
        } catch (Exception e) {
            userHistory.setUserDateOfBirth(null);
        }

        try {
            userHistory.setUserIsActive(rs.getBoolean(T_USERS_HISTORY_F_USER_ACTIVE));
        } catch (Exception e) {
            userHistory.setUserIsActive(null);
        }

        try {
            userHistory.setUserSiteURL(rs.getString(T_USERS_HISTORY_F_USER_SITE));
        } catch (Exception e) {
            userHistory.setUserSiteURL(null);
        }

        try {
            userHistory.setAddUserLocalDateTime(rs.getTimestamp(T_USERS_HISTORY_F_ADD_USER_DATE_TIME).toLocalDateTime());
        } catch (Exception e) {
            userHistory.setAddUserLocalDateTime(null);
        }

        return userHistory;
    }
}