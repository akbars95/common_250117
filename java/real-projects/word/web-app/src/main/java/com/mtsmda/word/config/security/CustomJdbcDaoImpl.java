package com.mtsmda.word.config.security;

import com.mtsmda.real.project.user.rowmapper.TableAndFieldsName;
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

import static com.mtsmda.word.config.security.SecurityConfiguration.QUERY_AUTHORITY_BY_USERNAME;
import static com.mtsmda.word.config.security.SecurityConfiguration.QUERY_USER_BY_USERNAME;

/**
 * Created by dminzat on 3/3/2017.
 */
@Service("customJdbcDaoImpl")
public class CustomJdbcDaoImpl extends JdbcDaoImpl {

    @Autowired
    @Qualifier("driverManagerDataSource")
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Value(QUERY_USER_BY_USERNAME)
    @Override
    public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
        super.setUsersByUsernameQuery(usersByUsernameQueryString);
    }

    @Value(QUERY_AUTHORITY_BY_USERNAME)
    @Override
    public void setAuthoritiesByUsernameQuery(String queryString) {
        super.setAuthoritiesByUsernameQuery(queryString);
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(super.getUsersByUsernameQuery(), new Object[]{username}, (rs, rowNum) -> {
            return new User(rs.getString(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_USERNAME),
                    rs.getString(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_PASSWORD),
                    rs.getBoolean(TableAndFieldsName.UserT.T_USERS_F_USER_ACTIVE),
                    rs.getBoolean(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_NON_EXPIRED),
                    rs.getBoolean(TableAndFieldsName.AccountT.T_ACCOUNTS_F_CREDENTIALS_NON_EXPIRED),
                    rs.getBoolean(TableAndFieldsName.AccountT.T_ACCOUNTS_F_ACCOUNT_NON_LOCKED),
                    AuthorityUtils.NO_AUTHORITIES);
        });
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