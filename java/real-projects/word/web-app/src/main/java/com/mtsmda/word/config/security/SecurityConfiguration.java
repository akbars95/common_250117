package com.mtsmda.word.config.security;

import com.mtsmda.word.controller.PageURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.mtsmda.word.controller.PageURL.*;

/**
 * Created by dminzat on 2/15/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication().withUser("simple").password("simple").roles("USER");
        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("AUTH");
        authenticationManagerBuilder.inMemoryAuthentication().withUser("simple2").password("simple2").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(ROOT + PageURL.ProtectedPageURL.PROTECT + TWO_ASTERIX).access("hasRole('ROLE_AUTH')")
            .and().formLogin().loginPage(StaticPageURL.LOGIN_PAGE_URL).failureUrl(StaticPageURL.LOGIN_PAGE_URL + QUESTION_MARK + "loginError")
            .usernameParameter("w_username").passwordParameter("w_password")
            .and().exceptionHandling().accessDeniedPage(StaticPageURL.ACCESS_DENIED_PAGE_URL);

        //logout
        http.logout().logoutUrl(StaticPageURL.LOGOUT_PAGE_URL).logoutSuccessUrl(ROOT).invalidateHttpSession(true);

        //csrf
        http.csrf();
    }
}