package com.mtsmda.word.repository;

import com.mtsmda.helper.ExceptionMessageHelper;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.common.LoggerI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dminzat on 2/15/2017.
 */
public abstract class ParentRepository implements LoggerI {

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

    protected void clearParamIfNotEmpty() {
        if (!params.isEmpty()) {
            params.clear();
        }
    }

    protected boolean isSuccess(int code) {
        return code > 0;
    }

    protected <T>CommonResponse<T> exceptionHandler(Integer code, Exception e, Class<T> classObject){
        LOGGER.error(ExceptionMessageHelper.exceptionDescription(e));
        return new CommonResponse<T>(code, ExceptionMessageHelper.exceptionDescription(e));
    }

    protected CommonResponse<Boolean> exceptionHandlerCUD(Integer code, Exception e){
        LOGGER.error(ExceptionMessageHelper.exceptionDescription(e));
        return new CommonResponse<>(false, code, ExceptionMessageHelper.exceptionDescription(e));
    }

}