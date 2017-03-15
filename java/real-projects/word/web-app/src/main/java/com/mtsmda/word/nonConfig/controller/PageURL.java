package com.mtsmda.word.nonConfig.controller;

/**
 * Created by dminzat on 2/15/2017.
 */
public class PageURL {

    public static final String ROOT = "/";
    public static final String QUESTION_MARK = "?";
    public static final String ASTERIX = "*";
    public static final String TWO_ASTERIX = ASTERIX + ASTERIX;

    public static class StaticPageURL{
        public static final String INDEX_PAGE_IN = "index";
        public static final String INDEX_PAGE_URL = ROOT + INDEX_PAGE_IN;

        public static final String LOGOUT_PAGE_IN = "w_logout";
        public static final String LOGOUT_PAGE_URL = ROOT + LOGOUT_PAGE_IN;

        public static final String LOGIN_PAGE_IN = "w_login";
        public static final String LOGIN_PAGE_URL = ROOT + LOGIN_PAGE_IN;

        public static final String ACCESS_DENIED_PAGE_IN = "accessDenied";
        public static final String ACCESS_DENIED_PAGE_URL = ROOT + ACCESS_DENIED_PAGE_IN;

        public static final String REGISTRATION_PAGE_IN = "registration";
        public static final String REGISTRATION_PAGE_URL = ROOT + REGISTRATION_PAGE_IN;
    }

    public static class ProtectedPageURL{

        public static final String PROTECT = "protect" + ROOT;

        public static final String PROTECT_INDEX_PAGE_IN = PROTECT + "index";
        public static final String PROTECT_INDEX_PAGE_URL = ROOT + PROTECT_INDEX_PAGE_IN;
    }

}