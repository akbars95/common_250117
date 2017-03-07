package com.mtsmda.word.repository.query;

import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.*;

import static com.mtsmda.helper.QueryCreatorHelper.*;

/**
 * Created by dminzat on 3/6/2017.
 */
public class QueryWarehouse {

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

    public static void main(String[] args) {
        showQuery("QUERY_GET_ACCOUNT_BY_USERNAME", AccountQuery.QUERY_GET_ACCOUNT_BY_USERNAME);
        showQuery("QUERY_GET_USER_ID_BY_USERNAME", AccountQuery.QUERY_GET_USER_ID_BY_USERNAME);
        showQuery("QUERY_GET_USER_BY_USERNAME", UserQuery.QUERY_GET_USER_BY_USERNAME);
        showQuery("QUERY_GET_USER_ATTEMPT_BY_USERNAME", UserAttemptQuery.QUERY_GET_USER_ATTEMPT_BY_USERNAME);
    }

    private static final void showQuery(String queryName, String query){
        System.out.println(queryName + " - " + query);
    }

}