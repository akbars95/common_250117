package com.mtsmda.word.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.sql.DataSource;

/**
 * Created by dminzat on 3/16/2017.
 */
@Deprecated //TODO: will be finished in future
/*@Configuration
@EnableSocial*/
public class SpringSocialConfiguration /*implements SocialConfigurer*/ {

    /*@Autowired
    private AccountConnectionSignUpService accountConnectionSignUpService;

    @Autowired
    private DataSource dataSource;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(new LinkedInConnectionFactory(
                environment.getProperty("spring.social.linkedin.appId"),
                environment.getProperty("spring.social.linkedin.appSecret")));
        connectionFactoryConfigurer.addConnectionFactory(new GitHubConnectionFactory(
                environment.getProperty("spring.social.github.appId"),
                environment.getProperty("spring.social.github.appSecret")));
        connectionFactoryConfigurer.addConnectionFactory(new TwitterConnectionFactory(
                environment.getProperty("spring.social.twitter.appId"),
                environment.getProperty("spring.social.twitter.appSecret")));
        GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(
                environment.getProperty("spring.social.google.appId"),
                environment.getProperty("spring.social.google.appSecret"));
        googleConnectionFactory.setScope("email");
        connectionFactoryConfigurer.addConnectionFactory(googleConnectionFactory);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setConnectionSignUp(accountConnectionSignUpService);
        return jdbcUsersConnectionRepository;
    }*/
}