package com.mtsmda.word.repository;

import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.real.project.user.model.UserAttempt;
import com.mtsmda.real.project.user.rowmapper.UserAttemptRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.time.LocalDateTime;

import static com.mtsmda.helper.LocalDateTimeHelper.NORMAL_DATE_TIME_FORMAT_VICE_VERSA;
import static com.mtsmda.helper.LocalDateTimeHelper.convertLocalDateTimeToString;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.UserAttemptT.*;

/**
 * Created by dminzat on 3/1/2017.
 */
@Repository
public class UserDetailRepositoryImpl extends JdbcDaoSupport implements UserDetailRepository {

    public static final String QUERY_GET_BY_USERNAME_USER_ID = "select u.USER_ID " +
            "from T_USERS u inner join T_ACCOUNTS a on u.USER_ID = a.ACCOUNT_USER_ID " +
            "where a.ACCOUNT_USERNAME = ?";
    private static final String QUERY_RESET_FAIL_ATTEMPTS = "UPDATE " + T_USER_ATTEMPTS + " SET ATTEMPTS = 0, LAST_MODIFIED = NULL " +
            "WHERE ACCOUNT_USER_ID = (" + QUERY_GET_BY_USERNAME_USER_ID + ")";
    private static final String QUERY_GET_USER_ATTEMPTS = "select * " +
            "from " + T_USER_ATTEMPTS +
            " WHERE ACCOUNT_USER_ID = (" + QUERY_GET_BY_USERNAME_USER_ID + ")";
    private static final String QUERY_INSERT_USER_TO_USER_ATTEMPTS = "INSERT INTO " + T_USER_ATTEMPTS +
            "(" + T_USER_ATTEMPTS_F_ACCOUNT_USER_ID + ", " + T_USER_ATTEMPTS_F_ATTEMPTS + ", " + T_USER_ATTEMPTS_F_LAST_MODIFIED
            + ") values (?,?,TO_DATE(?, 'hh24:mi:ss dd.mm.yyyy'))";
    private static final String QUERY_UPDATE_USER_ATTEMPT = "UPDATE " + T_USER_ATTEMPTS
            + " SET " + T_USER_ATTEMPTS_F_ATTEMPTS + "=" + T_USER_ATTEMPTS_F_ATTEMPTS + "+1"
            + ", " + T_USER_ATTEMPTS_F_LAST_MODIFIED + "=TO_DATE(?, 'hh24:mi:ss dd.mm.yyyy')"
            + " WHERE " + T_USER_ATTEMPTS_F_ACCOUNT_USER_ID + "= (" + QUERY_GET_BY_USERNAME_USER_ID + ")";
    private static final String QUERY_LOCKED_USER = "UPDATE T_ACCOUNTS SET ACCOUNT_NON_LOCKED = ? "
            + "where ACCOUNT_USERNAME = ?";

    @Autowired
    @Qualifier("driverManagerDataSource")
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void updateFailAttempts(String username) {
        UserAttempt userAttempt = getUserAttempts(username);
        if (ObjectHelper.objectIsNull(userAttempt)) {
            if (isExistUser(username)) {
                getJdbcTemplate().update(QUERY_INSERT_USER_TO_USER_ATTEMPTS, new Object[]{
                        getUserByUsername(username), 1, convertLocalDateTimeToString(LocalDateTime.now(), NORMAL_DATE_TIME_FORMAT_VICE_VERSA)
                });
            }
        } else {
            if (isExistUser(username)) {
                getJdbcTemplate().update(QUERY_UPDATE_USER_ATTEMPT, new Object[]{
                        convertLocalDateTimeToString(LocalDateTime.now(), NORMAL_DATE_TIME_FORMAT_VICE_VERSA), username
                });
            }

            if (userAttempt.getAttempts() + 1 >= 3) {
                getJdbcTemplate().update(QUERY_LOCKED_USER, 0, username);
                throw new LockedException("User account is locked!");
            }
        }
    }

    @Override
    public void resetFailAttempts(String username) {
        getJdbcTemplate().update(QUERY_RESET_FAIL_ATTEMPTS, new Object[]{username});
    }

    @Override
    public UserAttempt getUserAttempts(String username) {
        try {
            return getJdbcTemplate().queryForObject(QUERY_GET_USER_ATTEMPTS, new Object[]{username}, new UserAttemptRowMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    private Integer getUserByUsername(String username) {
        return getJdbcTemplate().queryForObject(QUERY_GET_BY_USERNAME_USER_ID, new Object[]{username}, Integer.class);
    }

    private boolean isExistUser(String username) {
        try {
            getUserByUsername(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}