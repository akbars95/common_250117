package com.mtsmda.word.listener.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by dminzat on 2/8/2017.
 */
public class SessionHTTPListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("-----session create-----" + se.getSession().getId());
        se.getSession().setMaxInactiveInterval(5 * 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("-----session destroy-----" + se.getSession().getId());
    }
}