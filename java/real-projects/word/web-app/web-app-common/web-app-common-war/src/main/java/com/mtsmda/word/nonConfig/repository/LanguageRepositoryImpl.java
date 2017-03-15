package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.word.model.Language;
import com.mtsmda.real.project.word.rowmapper.LanguageRowMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mtsmda.real.project.word.rowmapper.TableAndFieldsName.LanguageT.*;

/**
 * Created by dminzat on 2/6/2017.
 */
@Repository
public class LanguageRepositoryImpl extends ParentRepository implements LanguageRepository {

    @Override
    public boolean insertLanguage(Language language) {
        setQuery("insert into " + T_LANGUAGES + "(" + T_LANGUAGES_F_LANGUAGE_NAME + ")" +
                "values(:" + T_LANGUAGES_F_LANGUAGE_ID + ")");
        clearParamIfNotEmpty();
        params.put(T_LANGUAGES_F_LANGUAGE_NAME, language.getLanguageName());
        int update = getNamedParameterJdbcTemplate().update(getQuery(), params);
        return isSuccess(update);
    }

    @Override
    public boolean deleteAllLanguage() {
        setQuery("delete from " + T_LANGUAGES);
        clearParamIfNotEmpty();
        int update = getNamedParameterJdbcTemplate().update(getQuery(), params);
        return isSuccess(update);
    }

    @Override
    public List<Language> getAllLanguages() {
        setQuery("select * from " + T_LANGUAGES);
        return getNamedParameterJdbcTemplate().query(getQuery(), new LanguageRowMapper());
    }
    @Override
    public void setLogger() {
        LOGGER = Logger.getLogger(LanguageRepositoryImpl.class);
    }
}