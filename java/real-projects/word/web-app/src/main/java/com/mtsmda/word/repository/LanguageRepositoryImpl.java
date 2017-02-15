package com.mtsmda.word.repository;

import com.mtsmda.word.model.Language;
import com.mtsmda.word.rowmapper.LanguageRowMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mtsmda.word.rowmapper.TableAndFieldsName.LanguageT;

/**
 * Created by dminzat on 2/6/2017.
 */
@Repository
public class LanguageRepositoryImpl extends ParentRepository implements LanguageRepository {

    private static final Logger LOGGER = Logger.getLogger(LanguageRepositoryImpl.class);

    @Override
    public List<Language> getAllLanguages() {
        setQuery("select * from " + LanguageT.T_LANGUAGES);
        LOGGER.info(getQuery());
        return getNamedParameterJdbcTemplate().query(getQuery(), new LanguageRowMapper());
    }
}