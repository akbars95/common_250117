package com.mtsmda.word.listener.web;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by dminzat on 2/8/2017.
 */
public class SessionHTTPListener implements HttpSessionListener {

    private static final Logger LOGGER = Logger.getLogger(SessionHTTPListener.class);

    private static int currentCountOnlineSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.info("-----session create-----" + se.getSession().getId());
        se.getSession().setMaxInactiveInterval(5 * 60);
        LOGGER.info("set default session timeout " + (5 * 60));
        LOGGER.info(++currentCountOnlineSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.info("-----session destroy-----" + se.getSession().getId());
        LOGGER.info(--currentCountOnlineSessions);
    }
}