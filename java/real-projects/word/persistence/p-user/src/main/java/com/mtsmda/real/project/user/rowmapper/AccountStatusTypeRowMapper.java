package com.mtsmda.real.project.user.rowmapper;

import com.mtsmda.real.project.user.model.AccountStatusType;
import com.mtsmda.real.project.user.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.AccountStatusTypeT.T_ACCOUNT_STATUS_TYPES_F_ACTION_TIMESTAMP;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.AccountStatusTypeT.T_ACCOUNT_STATUS_TYPES_F_STATUS_TYPE_ID;
import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.AccountStatusTypeT.T_ACCOUNT_STATUS_TYPES_F_USER_ID;

/**
 * Created by dminzat on 3/27/2017.
 */
public class AccountStatusTypeRowMapper implements RowMapper<AccountStatusType> {

    private boolean rowMapUser = false;

    @Override
    public AccountStatusType mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountStatusType accountStatusType = new AccountStatusType();

        try {
            if (rowMapUser) {
                accountStatusType.setUser(new UserRowMapper().mapRow(rs, rowNum));
            } else {
                accountStatusType.setUser(new User(rs.getInt(T_ACCOUNT_STATUS_TYPES_F_USER_ID)));
            }
        } catch (Exception e) {
            accountStatusType.setUser(null);
        }

        try {
            accountStatusType.setActionTimestamp(rs.getTimestamp(T_ACCOUNT_STATUS_TYPES_F_ACTION_TIMESTAMP).toLocalDateTime());
        } catch (Exception e) {
            accountStatusType.setActionTimestamp(null);
        }

        /*try {
            accountStatusType.setStatusType(rs.getString(T_ACCOUNT_STATUS_TYPES_F_STATUS_TYPE_ID));
        } catch (Exception e) {
            accountStatusType.setActionTimestamp(null);
        }*/

        return accountStatusType;
    }
}