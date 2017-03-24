package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.helper.ExceptionMessageHelper;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.nonConfig.common.LoggerAC;
import com.mtsmda.word.nonConfig.common.LoggerI;
import com.mtsmda.word.nonConfig.common.MessageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dminzat on 2/15/2017.
 */
public abstract class ParentRepository extends LoggerAC{

    protected static Logger LOGGER = null;
    protected Map<String, Object> params = new HashMap<>();
    private boolean success = false;

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
        return new CommonResponse<T>((T)null, code, ExceptionMessageHelper.exceptionDescription(e));
    }

    protected <T>CommonResponse<List<T>> exceptionHandlerReturnList(Integer code, Exception e, Class<T> classObject){
        LOGGER.error(ExceptionMessageHelper.exceptionDescription(e));
        return new CommonResponse<List<T>>((List<T>)null, code, ExceptionMessageHelper.exceptionDescription(e));
    }

    protected CommonResponse<Boolean> exceptionHandlerCUD(Integer code, Exception e){
        LOGGER.error(ExceptionMessageHelper.exceptionDescription(e));
        return new CommonResponse<>(false, code, ExceptionMessageHelper.exceptionDescription(e));
    }

    protected CommonResponse<Boolean> resultSuccessCUD(Integer code){
        LOGGER.info("is ok - " + isSuccess());
        return new CommonResponse<>(isSuccess(), code);
    }

    protected boolean isSuccess() {
        return success;
    }

    protected void setSuccess(boolean success) {
        this.success = success;
    }

}