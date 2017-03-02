package com.mtsmda.word.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dminzat on 2/15/2017.
 */
public abstract class ParentRepository {

    protected static Logger LOGGER = null;
    protected Map<String, Object> params = new HashMap<>();

    public ParentRepository() {
        setLogger();
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String query;

    protected String getQuery() {
        return query;
    }

    protected void setQuery(String query) {
        this.query = query;
        LOGGER.info("query - " + query);
    }

    protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    protected void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    protected abstract <T> void setLogger();

    protected void clearParamIfNotEmpty() {
        if (!params.isEmpty()) {
            params.clear();
        }
    }

    protected boolean isSuccess(int code) {
        return code > 0;
    }

}