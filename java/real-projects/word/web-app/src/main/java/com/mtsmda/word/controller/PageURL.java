package com.mtsmda.word.controller;

/**
 * Created by dminzat on 2/15/2017.
 */
public class PageURL {

    public static final String ROOT = "/";

    public static class StaticPageURL{
        public static final String INDEX_PAGE_IN = "index";
        public static final String INDEX_PAGE_URL = ROOT + INDEX_PAGE_IN;
    }

    public static class ProtectedPageURL{

        public static final String PROTECT = "protect" + ROOT;

        public static final String PROTECT_INDEX_PAGE_IN = PROTECT + "index";
        public static final String PROTECT_INDEX_PAGE_URL = ROOT + PROTECT_INDEX_PAGE_IN;
    }

}