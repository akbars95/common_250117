package com.mtsmda.word.config.security;

import com.mtsmda.helper.ListHelper;
import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName;
import com.mtsmda.word.nonConfig.common.LoggerI;
import com.mtsmda.word.nonConfig.repository.query.QueryWarehouse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by dminzat on 3/3/2017.
 */
@Service("customJdbcDaoImplUserDetailsService")
public class CustomJdbcDaoImplUserDetailsService extends JdbcDaoImpl implements LoggerI {

    protected static Logger LOGGER = null;

    @Autowired
    @Qualifier("driverManagerDataSource")
    private DataSource dataSource;

    @Override
    @PostConstruct
    public <T> void setLogger() {
        if(ObjectHelper.objectIsNull(LOGGER)){
            LOGGER = Logger.getLogger(this.getClass());
        }
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        LOGGER.info("add datasource");
    }

    @Value(QueryWarehouse.SpringSecurityQuery.QUERY_USER_BY_USERNAME)
    @Override
    public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
        super.setUsersByUsernameQuery(usersByUsernameQueryString);
        setLogger();
        LOGGER.info("set query - " + usersByUsernameQueryString);
    }

    @Value(QueryWarehouse.SpringSecurityQuery.QUERY_AUTHORITY_BY_USERNAME)
    @Override
    public void setAuthoritiesByUsernameQuery(String queryString) {
        super.setAuthoritiesByUsernameQuery(queryString);
        setLogger();
        LOGGER.info("set query - " + queryString);
    }

    @Value("true")
    @Override
    public void setEnableGroups(boolean enableGroups) {
        super.setEnableGroups(enableGroups);
        setLogger();
        LOGGER.info("enable groups - " + enableGroups);
    }

//    @Value()
    /*@Override
    public void setGroupAuthoritiesByUsernameQuery(String queryString) {

    }*/

    @Override
    protected List<GrantedAuthority> loadGroupAuthorities(String username) {
        super.setGroupAuthoritiesByUsernameQuery(QueryWarehouse.SpringSecurityQuery.QUERY_GET_USER_BY_USERNAME_GROUP_AUTHORITY);
        List<GrantedAuthority> grantedAuthorities = super.loadGroupAuthorities(username);
        LOGGER.info("get " + username + " grants " + ListHelper.toStringList(grantedAuthorities));
        return grantedAuthorities;
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        List<UserDetails> userDetails = getJdbcTemplate().query(super.getUsersByUsernameQuery(), new Object[]{username}, (rs, rowNum) -> {
            return new User(rs.getString(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME),
                    rs.getString(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_PASSWORD),
                    rs.getBoolean(TableAndFieldsName.UserT.T_USERS_F_USER_ACTIVE),
                    rs.getBoolean(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_NON_EXPIRED),
                    rs.getBoolean(TableAndFieldsName.AccountT.T_ACCOUNTS_F_CREDENTIALS_NON_EXPIRED),
                    rs.getBoolean(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_NON_LOCKED),
                    loadGroupAuthorities(username));
        });
        LOGGER.info("get " + username + " user details " + ListHelper.toStringList(userDetails));
        return userDetails;
    }

    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
        String usernameReturn = userFromUserQuery.getUsername();
        if (super.isUsernameBasedPrimaryKey()) {
            usernameReturn = username;
        }
        return new User(usernameReturn, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(),
                userFromUserQuery.isAccountNonExpired(), userFromUserQuery.isCredentialsNonExpired(), userFromUserQuery.isAccountNonLocked(), userFromUserQuery.getAuthorities());
    }

}