package com.mtsmda.word.config.security;

import com.mtsmda.word.controller.PageURL;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static com.mtsmda.word.controller.PageURL.*;

/**
 * Created by dminzat on 2/15/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String QUERY_USER_BY_USERNAME = "select a.ACCOUNT_USERNAME, a.ACCOUNT_PASSWORD, u.USER_ACTIVE\n" +
            "from t_users u inner join t_accounts a on u.USER_ID=a.ACCOUNT_USER_ID\n" +
            "where a.ACCOUNT_USERNAME = ?";
    private static final String QUERY_AUTHORITY_BY_USERNAME = "select a.ACCOUNT_USERNAME, r.ROLE_NAME\n" +
            "from t_users u inner join t_accounts a on u.USER_ID = a.ACCOUNT_USER_ID\n" +
            "inner join T_USER_ROLES ur on ur.USER_ID = u.USER_ID\n" +
            "inner join T_ROLES r on r.ROLE_ID = ur.ROLE_ID\n" +
            "where a.ACCOUNT_USERNAME = ?";

    @Autowired
    private BasicDataSource basicDataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("simple").password("simple").roles("USER");
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("simple2").password("simple2").roles("USER");

        authenticationManagerBuilder.jdbcAuthentication().dataSource(basicDataSource)
                .usersByUsernameQuery(QUERY_USER_BY_USERNAME)
                .authoritiesByUsernameQuery(QUERY_AUTHORITY_BY_USERNAME);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(ROOT + PageURL.ProtectedPageURL.PROTECT + TWO_ASTERIX).access("hasRole('ROLE_ADMIN')")
                .and().formLogin().loginPage(StaticPageURL.LOGIN_PAGE_URL).failureUrl(StaticPageURL.LOGIN_PAGE_URL + QUESTION_MARK + "loginError")
                .usernameParameter("w_username").passwordParameter("w_password").permitAll()
                .and().exceptionHandling().accessDeniedPage(StaticPageURL.ACCESS_DENIED_PAGE_URL);

        //logout
        http.logout().logoutUrl(StaticPageURL.LOGOUT_PAGE_URL).logoutSuccessUrl(ROOT).invalidateHttpSession(true).deleteCookies("JSESSIONID");

        //sessionManagement
        http.sessionManagement()/*.sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/.sessionFixation().newSession().maximumSessions(1);

        //csrf
        http.csrf()/*.disable()*/;//if disabled csrf log out do not work

        //httpBasic
        http.httpBasic().realmName(CustomBasicAuthenticationEntryPoint.REALM_NAME)
                .authenticationEntryPoint(getCustomBasicAuthenticationEntryPoint());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "*");
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getCustomBasicAuthenticationEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }
}