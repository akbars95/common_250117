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
    public int sessionTimeOut = 5 * 60;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.info("-----session create-----" + se.getSession().getId());
        se.getSession().setMaxInactiveInterval(sessionTimeOut);
        LOGGER.info("set default session timeout " + sessionTimeOut);
        LOGGER.info(++currentCountOnlineSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.info("-----session destroy-----" + se.getSession().getId());
        LOGGER.info(--currentCountOnlineSessions);
    }
}