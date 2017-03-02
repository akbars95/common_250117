package com.mtsmda.word.repository;

import com.mtsmda.real.project.user.model.UserAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by dminzat on 3/1/2017.
 */
@Repository
public class UserDetailRepositoryImpl extends JdbcDaoSupport implements UserDetailRepository {

    private static final String QUERY_GET_BY_USERNAME_USER_ID = "select u.USER_ID\n" +
            "from T_USERS u inner join T_ACCOUNTS a on u.USER_ID = a.ACCOUNT_USER_ID\n" +
            "where a.ACCOUNT_USERNAME = ?";
    private static final String QUERY_RESET_FAIL_ATTEMPTS = "UPDATE USER_ATTEMPTS SET ATTEMPTS = 0, LAST_MODIFIED = NULL WHERE USER_ID = (" +
            QUERY_GET_BY_USERNAME_USER_ID + ")";
    private static final String QUERY_GET_USER_ATTEMPTS = "select * from USER_ATTEMPTS WHERE USER_ID = ?";

    @Autowired
    @Qualifier("driverManagerDataSource")
    private DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void updateFailAttempts(String username) {
        /*UserAttempt userAttempt = getUserAttempts(userId);
        if(ObjectHelper.objectIsNull(userAttempt)){
            if(is)
        }else{

        }*/
    }

    @Override
    public void resetFailAttempts(String username) {
        getJdbcTemplate().update(QUERY_RESET_FAIL_ATTEMPTS, new Object[]{username});
    }

    @Override
    public UserAttempt getUserAttempts(String username) {
        UserAttempt userAttempt = new UserAttempt();

//        getJdbcTemplate().queryForObject(QUERY_GET_USER_ATTEMPTS, new Object[]{username})

        return userAttempt;
    }

    /*private Integer getCurrentUserId(String username) {
        if(ObjectHelper.objectIsNull(currentUserId)){
            currentUserId = getJdbcTemplate().queryForObject(QUERY_GET_BY_USERNAME_USER_ID, new Object[]{username}, Integer.class);
        }
        return currentUserId;
    }*/
}