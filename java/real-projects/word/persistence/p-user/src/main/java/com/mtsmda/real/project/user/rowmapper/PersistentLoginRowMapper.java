package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.real.project.user.model.PersistentLogin;
import com.mtsmda.real.project.user.model.User;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.PersistentLoginT;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dminzat on 3/7/2017.
 */
@Deprecated
public class PersistentLoginRowMapper implements RowMapper<PersistentLogin> {

    private boolean rowMapAccount = false;

    public PersistentLoginRowMapper() {

    }

    public PersistentLoginRowMapper(boolean rowMapAccount) {
        this();
        this.rowMapAccount = rowMapAccount;
    }

    @Override
    public PersistentLogin mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersistentLogin persistentLogin = new PersistentLogin();

        if (rowMapAccount) {
            persistentLogin.setAccount(new AccountRowMapper().mapRow(rs, rowNum));
        } else {
            try {
                persistentLogin.setAccount(new Account(new User(rs.getInt(PersistentLoginT.T_PERSISTENT_LOGINS_F_ACCOUNT_USER_ID))));
            } catch (Exception e) {
                persistentLogin.setAccount(null);
            }
        }

        try {
            persistentLogin.setSeries(rs.getString(PersistentLoginT.T_PERSISTENT_LOGINS_F_SERIES));
        } catch (Exception e) {
            persistentLogin.setSeries(null);
        }

        try {
            persistentLogin.setToken(rs.getString(PersistentLoginT.T_PERSISTENT_LOGINS_F_TOKEN));
        } catch (Exception e) {
            persistentLogin.setToken(null);
        }

        try {
            persistentLogin.setLastUsed(rs.getTimestamp(PersistentLoginT.T_PERSISTENT_LOGINS_F_LAST_USED).toLocalDateTime());
        } catch (Exception e) {
            persistentLogin.setLastUsed(null);
        }

        return persistentLogin;
    }
}