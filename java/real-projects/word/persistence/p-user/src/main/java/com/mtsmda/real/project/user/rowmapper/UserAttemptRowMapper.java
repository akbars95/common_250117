package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.real.project.user.model.User;
import com.mtsmda.real.project.user.model.UserAttempt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.UserAttemptT.*;

/**
 * Created by dminzat on 3/2/2017.
 */
public class UserAttemptRowMapper implements RowMapper<UserAttempt> {

    @Override
    public UserAttempt mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAttempt userAttempt = new UserAttempt();

        /*Field[] declaredFields = userAttempt.getClass().getDeclaredFields();

        long count = Arrays.stream(declaredFields).filter(field -> {
            Column annotationColumn = field.getAnnotation(Column.class);

            return ObjectHelper.objectIsNotNull(annotationColumn);
        }).count();
        System.out.println(count);*/

        try {
            userAttempt.setUserAttemptId(rs.getInt(T_USER_ATTEMPTS_F_USER_ATTEMPT_ID));
        } catch (Exception e) {
            userAttempt.setUserAttemptId(null);
        }

        try {
            userAttempt.setAccount(new Account(new User(rs.getInt(T_USER_ATTEMPTS_F_ACCOUNT_USER_ID))));
        } catch (Exception e) {
            userAttempt.setAccount(null);
        }

        try {
            userAttempt.setAttempts(rs.getInt(T_USER_ATTEMPTS_F_ATTEMPTS));
        } catch (Exception e) {
            userAttempt.setAttempts(null);
        }

        try {
            userAttempt.setLastModified(rs.getTimestamp(T_USER_ATTEMPTS_F_LAST_MODIFIED).toLocalDateTime());
        } catch (Exception e) {
            userAttempt.setLastModified(null);
        }

        return userAttempt;
    }

    public static void main(String[] args) {
        try {
            new UserAttemptRowMapper().mapRow(null, 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}