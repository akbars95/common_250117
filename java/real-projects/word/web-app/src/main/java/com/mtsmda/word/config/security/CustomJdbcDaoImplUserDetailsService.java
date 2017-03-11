package com.mtsmda.word.config.security;

import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName;
import com.mtsmda.word.repository.query.QueryWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by dminzat on 3/3/2017.
 */
@Service("customJdbcDaoImplUserDetailsService")
public class CustomJdbcDaoImplUserDetailsService extends JdbcDaoImpl {

    @Autowired
    @Qualifier("driverManagerDataSource")
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Value(QueryWarehouse.SpringSecurityQuery.QUERY_USER_BY_USERNAME)
    @Override
    public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
        super.setUsersByUsernameQuery(usersByUsernameQueryString);
    }

    @Value(QueryWarehouse.SpringSecurityQuery.QUERY_AUTHORITY_BY_USERNAME)
    @Override
    public void setAuthoritiesByUsernameQuery(String queryString) {
        super.setAuthoritiesByUsernameQuery(queryString);
    }

    @Value("true")
    @Override
    public void setEnableGroups(boolean enableGroups) {
        super.setEnableGroups(enableGroups);
    }

//    @Value()
    /*@Override
    public void setGroupAuthoritiesByUsernameQuery(String queryString) {

    }*/

    @Override
    protected List<GrantedAuthority> loadGroupAuthorities(String username) {
        super.setGroupAuthoritiesByUsernameQuery(QueryWarehouse.SpringSecurityQuery.QUERY_GET_USER_BY_USERNAME_GROUP_AUTHORITY);
        return super.loadGroupAuthorities(username);
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        List<UserDetails> userDetailses = getJdbcTemplate().query(super.getUsersByUsernameQuery(), new Object[]{username}, (rs, rowNum) -> {
            return new User(rs.getString(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME),
                    rs.getString(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_PASSWORD),
                    rs.getBoolean(TableAndFieldsName.UserT.T_USERS_F_USER_ACTIVE),
                    rs.getBoolean(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_NON_EXPIRED),
                    rs.getBoolean(TableAndFieldsName.AccountT.T_ACCOUNTS_F_CREDENTIALS_NON_EXPIRED),
                    rs.getBoolean(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_NON_LOCKED),
                    AuthorityUtils.NO_AUTHORITIES);
        });
        return userDetailses;
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