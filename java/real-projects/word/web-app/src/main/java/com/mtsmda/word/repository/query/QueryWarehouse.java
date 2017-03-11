package com.mtsmda.word.repository.query;

import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.real.project.user.model.PersistentLogin;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.*;

import static com.mtsmda.helper.QueryCreatorHelper.*;
import static com.mtsmda.helper.QueryCreatorHelper.queryCreator;

/**
 * Created by dminzat on 3/6/2017.
 */
public class QueryWarehouse {

    public static final class SpringSecurityQuery {
        public static final String QUERY_GET_USER_BY_USERNAME_GROUP_AUTHORITY = "SELECT G.GROUP_ID, G.GROUP_NAME, R.ROLE_NAME " +
                "FROM T_GROUPS G, T_GROUP_ROLES GR, T_GROUP_USERS GU, T_ROLES R " +
                "WHERE GU.USER_ID = (" + AccountQuery.QUERY_GET_USER_ID_BY_USERNAME + ") " +
                "AND G.GROUP_ID = GR.GROUP_ID " +
                "AND G.GROUP_ID = GU.GROUP_ID " +
                "AND GR.ROLE_ID = R.ROLE_ID";
    }

    public static class UserQuery {
        public static final String QUERY_GET_USER_BY_USERNAME = queryCreator(SELECT_ALL_WITH_SPACE, FROM_WITH_SPACE,
                UserT.T_USERS, WHERE_SPACE_BOTH, UserT.T_USERS_F_USER_ID,
                EQUAL_SPACE_BOTH, OPEN_PARENTHESIS, AccountQuery.QUERY_GET_USER_ID_BY_USERNAME, CLOSE_PARENTHESIS);
    }

    public static class AccountQuery {
        public static final String QUERY_GET_ACCOUNT_BY_USERNAME = queryCreator(SELECT_ALL_WITH_SPACE, FROM_WITH_SPACE,
                AccountT.T_ACCOUNTS, WHERE_SPACE_BOTH, AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME,
                EQUAL_SPACE_BOTH, COLON, AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME);
        public static final String QUERY_GET_USER_ID_BY_USERNAME = queryCreator(SELECT_WITH_SPACE,
                AccountT.T_ACCOUNTS_F_ACCOUNT_USER_ID, FROM_SPACE_BOTH, AccountT.T_ACCOUNTS,
                WHERE_SPACE_BOTH, AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME,
                EQUAL_SPACE_BOTH, COLON, AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME);
    }

    public static class UserAttemptQuery {
        public static final String QUERY_GET_USER_ATTEMPT_BY_USERNAME = queryCreator(SELECT_ALL_WITH_SPACE, FROM_WITH_SPACE,
                UserAttemptT.T_USER_ATTEMPTS, WHERE_SPACE_BOTH, UserAttemptT.T_USER_ATTEMPTS_F_ACCOUNT_USER_ID, EQUAL_SPACE_BOTH,
                OPEN_PARENTHESIS, AccountQuery.QUERY_GET_USER_ID_BY_USERNAME, CLOSE_PARENTHESIS);
    }

    public static class PersistentLoginQuery {
        public static final String QUERY_INSERT_PERSISTENT_LOGIN = queryCreator(INSERT_INTO_WITH_SPACE, PersistentLoginT.T_PERSISTENT_LOGINS,
                SPACE_WITH_OPEN_PARENTHESIS, getInsertParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_ACCOUNT_USER_ID, false), getInsertParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_SERIES, false),
                getInsertParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_TOKEN, false), getInsertParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_LAST_USED, true),
                SPACE_VALUES_WITH_OPEN_PARENTHESIS, OPEN_PARENTHESIS, AccountQuery.QUERY_GET_USER_ID_BY_USERNAME,
                CLOSE_PARENTHESIS, COMMA_WITH_SPACE,
                getParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_SERIES, true, false), getParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_TOKEN, true, false),
                getToDateOracleAsParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_LAST_USED), CLOSE_PARENTHESIS);
        public static final String QUERY_UPDATE_PERSISTENT_LOGIN_TOKEN_AND_LAST_USER_BY_SERIES = queryCreator(UPDATE_WITH_SPACE, PersistentLoginT.T_PERSISTENT_LOGINS,
        SET_SPACE_BOTH, getUpdateParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_TOKEN, false), PersistentLoginT.T_PERSISTENT_LOGINS_F_LAST_USED, EQUAL_SPACE_BOTH,
                getToDateOracleAsParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_LAST_USED),
                WHERE_SPACE_BOTH, getUpdateParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_SERIES, true));
        public static final String QUERY_DELETE_BY_USERNAME = queryCreator(DELETE_FROM_WITH_SPACE, PersistentLoginT.T_PERSISTENT_LOGINS, WHERE_SPACE_BOTH,
                PersistentLoginT.T_PERSISTENT_LOGINS_F_ACCOUNT_USER_ID, EQUAL_SPACE_BOTH, OPEN_PARENTHESIS, UserQuery.QUERY_GET_USER_BY_USERNAME, CLOSE_PARENTHESIS);
        public static final String QUERY_SELECT_PERSISTENT_LOGIN_BY_SERIES = queryCreator(SELECT_WITH_SPACE,
                getSelectParam(AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME),
                getSelectParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_TOKEN), getSelectParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_SERIES),
                PersistentLoginT.T_PERSISTENT_LOGINS_F_LAST_USED, FROM_SPACE_BOTH,
                getInnerJoinFirstJoin(PersistentLoginT.T_PERSISTENT_LOGINS, PersistentLoginT.PERSISTENT_LOGIN_PREFIX,
                        PersistentLoginT.T_PERSISTENT_LOGINS_F_ACCOUNT_USER_ID,
                        AccountT.T_ACCOUNTS, AccountT.ACCOUNT_PREFIX, AccountT.T_ACCOUNTS_F_ACCOUNT_USER_ID),
                WHERE_SPACE_BOTH, getUpdateParam(PersistentLoginT.T_PERSISTENT_LOGINS_F_SERIES, true));


    }

    public static void main(String[] args) {
        showQuery("QUERY_GET_ACCOUNT_BY_USERNAME", AccountQuery.QUERY_GET_ACCOUNT_BY_USERNAME);
        showQuery("QUERY_GET_USER_ID_BY_USERNAME", AccountQuery.QUERY_GET_USER_ID_BY_USERNAME);
        showQuery("QUERY_GET_USER_BY_USERNAME", UserQuery.QUERY_GET_USER_BY_USERNAME);
        showQuery("QUERY_GET_USER_ATTEMPT_BY_USERNAME", UserAttemptQuery.QUERY_GET_USER_ATTEMPT_BY_USERNAME);
        showQuery("QUERY_INSERT_PERSISTENT_LOGIN", PersistentLoginQuery.QUERY_INSERT_PERSISTENT_LOGIN);
        showQuery("QUERY_UPDATE_PERSISTENT_LOGIN_TOKEN_AND_LAST_USER_BY_SERIES", PersistentLoginQuery.QUERY_UPDATE_PERSISTENT_LOGIN_TOKEN_AND_LAST_USER_BY_SERIES);
        showQuery("QUERY_DELETE_BY_USERNAME", PersistentLoginQuery.QUERY_DELETE_BY_USERNAME);
        showQuery("QUERY_SELECT_PERSISTENT_LOGIN_BY_SERIES", PersistentLoginQuery.QUERY_SELECT_PERSISTENT_LOGIN_BY_SERIES);
        showQuery("QUERY_GET_USER_BY_USERNAME_GROUP_AUTHORITY", SpringSecurityQuery.QUERY_GET_USER_BY_USERNAME_GROUP_AUTHORITY);
    }

    private static final void showQuery(String queryName, String query){
        System.out.println(queryName + "\t\t - \t" + query);
    }

}