package com.mtsmda.word.config.security;

import com.mtsmda.word.controller.PageURL;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import static com.mtsmda.word.controller.PageURL.*;
import static com.mtsmda.word.controller.PageURL.StaticPageURL.ACCESS_DENIED_PAGE_URL;
import static com.mtsmda.word.controller.PageURL.StaticPageURL.LOGIN_PAGE_URL;

/**
 * Created by dminzat on 2/15/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String QUERY_USER_BY_USERNAME = "select a.*, u.USER_ACTIVE\n" +
            "from t_users u inner join t_accounts a on u.USER_ID=a.ACCOUNT_USER_ID\n" +
            "where a.ACCOUNT_USERNAME = ?";
    public static final String QUERY_AUTHORITY_BY_USERNAME = "select a.ACCOUNT_USERNAME, r.ROLE_NAME\n" +
            "from t_users u inner join t_accounts a on u.USER_ID = a.ACCOUNT_USER_ID\n" +
            "inner join T_USER_ROLES ur on ur.USER_ID = u.USER_ID\n" +
            "inner join T_ROLES r on r.ROLE_ID = ur.ROLE_ID\n" +
            "where a.ACCOUNT_USERNAME = ?";

    @Autowired
    private BasicDataSource basicDataSource;

    @Autowired
    private LimitLoginAuthenticationProvider limitLoginAuthenticationProvider;

    @Autowired
    private CustomJdbcDaoImpl customJdbcDao;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("simple").password("simple").roles("USER");
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("simple2").password("simple2").roles("USER");

        /*authenticationManagerBuilder.jdbcAuthentication().dataSource(basicDataSource)
                .usersByUsernameQuery(QUERY_USER_BY_USERNAME)
                .authoritiesByUsernameQuery(QUERY_AUTHORITY_BY_USERNAME);*/
        authenticationManagerBuilder.authenticationProvider(limitLoginAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(ROOT + PageURL.ProtectedPageURL.PROTECT + TWO_ASTERIX).access("hasRole('ROLE_ADMIN')")
                .and().formLogin().loginPage(LOGIN_PAGE_URL).failureUrl(LOGIN_PAGE_URL + QUESTION_MARK + "loginError")
                .usernameParameter("w_username").passwordParameter("w_password").permitAll()
                .and().exceptionHandling().accessDeniedPage(ACCESS_DENIED_PAGE_URL);

        //logout
        http.logout()/*.logoutUrl(LOGOUT_PAGE_URL)*/.logoutSuccessUrl(ROOT).invalidateHttpSession(true).deleteCookies("JSESSIONID");

        //sessionManagement
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).sessionFixation()
                .newSession().maximumSessions(1).expiredUrl(LOGIN_PAGE_URL + QUESTION_MARK + "expired");
        http.sessionManagement().invalidSessionUrl(LOGIN_PAGE_URL + QUESTION_MARK + "invalid_session");

        //csrf
        http.csrf().disable();//if disabled csrf log out do not work

        //httpBasic
        http.httpBasic().realmName(CustomBasicAuthenticationEntryPoint.REALM_NAME)
                .authenticationEntryPoint(getCustomBasicAuthenticationEntryPoint());

        //rememberMe
        http.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7).rememberMeParameter("w_remember_me")
                .tokenRepository(persistentTokenRepository()).key("rem-me-key")
        .rememberMeCookieName("remember-me-cookie")/*.userDetailsService(customJdbcDao)*/;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(basicDataSource);
        return jdbcTokenRepository;
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*public static void main(String[] args) {
        System.out.println(BCryptPasswordEncoderHelper.getBCryptPasswordEncoder("durov.daniil"));
    }*/
}