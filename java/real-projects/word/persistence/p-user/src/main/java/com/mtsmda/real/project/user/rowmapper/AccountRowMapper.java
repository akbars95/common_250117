package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.real.project.user.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.AccountT.*;

/**
 * Created by dminzat on 3/2/2017.
 */
public class AccountRowMapper implements RowMapper<Account> {

    private boolean rowMapUser = false;
    private boolean rowMapUserAttempt = false;

    public AccountRowMapper() {

    }

    public AccountRowMapper(boolean rowMapUser) {
        this.rowMapUser = rowMapUser;
    }

    public AccountRowMapper(boolean rowMapUser, boolean rowMapUserAttempt) {
        this.rowMapUser = rowMapUser;
        this.rowMapUserAttempt = rowMapUserAttempt;
    }

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();

        if (this.rowMapUser) {
            try {
                account.setUser(new UserRowMapper().mapRow(rs, rowNum));
            } catch (Exception e) {
                account.setUser(null);
            }
        } else {
            try {
                account.setUser(new User(rs.getInt(T_ACCOUNTS_F_ACCOUNT_USER_ID)));
            } catch (Exception e) {
                account.setUser(null);
            }
        }

        try {
            account.setAccountUsername(rs.getString(T_ACCOUNTS_F_ACCOUNT_USERNAME));
        } catch (Exception e) {
            account.setAccountUsername(null);
        }

        try {
            account.setAccountPassword(rs.getString(T_ACCOUNTS_F_ACCOUNT_PASSWORD));
        } catch (Exception e) {
            account.setAccountPassword(null);
        }

        try {
            account.setAccountNonExpired(rs.getBoolean(T_ACCOUNTS_F_ACCOUNT_NON_EXPIRED));
        } catch (Exception e) {
            account.setAccountNonExpired(null);
        }

        try {
            account.setAccountNonLocked(rs.getBoolean(T_ACCOUNTS_F_ACCOUNT_NON_LOCKED));
        } catch (Exception e) {
            account.setAccountNonLocked(null);
        }

        try {
            account.setCredentialsNonExpired(rs.getBoolean(T_ACCOUNTS_F_CREDENTIALS_NON_EXPIRED));
        } catch (Exception e) {
            account.setCredentialsNonExpired(null);
        }

        try {
            account.setUserMaxAttempts(rs.getInt(T_ACCOUNTS_F_USER_MAX_ATTEMPTS));
        } catch (Exception e) {
            account.setUserMaxAttempts(null);
        }

        try {
            account.setUserCountDaysNeedChangePass(rs.getInt(T_ACCOUNTS_F_COUNT_DAY_NEED_CHANGE_PASS));
        } catch (Exception e) {
            account.setUserCountDaysNeedChangePass(null);
        }

        if (this.rowMapUserAttempt) {
            try {
                account.setUserAttempt(new UserAttemptRowMapper().mapRow(rs, rowNum));
            } catch (Exception e) {
                account.setUserAttempt(null);
            }
        }

        return account;
    }
}