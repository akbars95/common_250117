package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.real.project.user.model.PasswordHistory;
import com.mtsmda.real.project.user.model.User;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.PasswordHistoryT;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.PasswordHistoryT.T_PASSWORD_HISTORY_F_ACCOUNT_USER_ID;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.PasswordHistoryT.T_PASSWORD_HISTORY_F_AC_PAS_CHANGE_DATE_TIME;

/**
 * Created by dminzat on 3/11/2017.
 */
public class PasswordHistoryRowMapper implements RowMapper<PasswordHistory> {

    private boolean rowMapPasswordHistory = false;

    public PasswordHistoryRowMapper() {

    }

    public PasswordHistoryRowMapper(boolean rowMapPasswordHistory) {
        this.rowMapPasswordHistory = rowMapPasswordHistory;
    }

    @Override
    public PasswordHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        PasswordHistory passwordHistory = new PasswordHistory();

        if (this.rowMapPasswordHistory) {
            try {
                passwordHistory.setAccount(new AccountRowMapper().mapRow(rs, rowNum));
            } catch (Exception e) {
                passwordHistory.setAccount(null);
            }
        } else {
            try {
                passwordHistory.setAccount(new Account(new User(rs.getInt(T_PASSWORD_HISTORY_F_ACCOUNT_USER_ID))));
            } catch (Exception e) {
                passwordHistory.setAccount(null);
            }
        }

        try {
            passwordHistory.setAccountPassword(rs.getString(T_PASSWORD_HISTORY_F_ACCOUNT_USER_ID));
        } catch (Exception e) {
            passwordHistory.setAccountPassword(null);
        }

        try {
            passwordHistory.setAccountPasswordChangeLocalDateTime(rs.getTimestamp(T_PASSWORD_HISTORY_F_AC_PAS_CHANGE_DATE_TIME).toLocalDateTime());
        } catch (Exception e) {
            passwordHistory.setAccountPasswordChangeLocalDateTime(null);
        }

        return passwordHistory;
    }
}