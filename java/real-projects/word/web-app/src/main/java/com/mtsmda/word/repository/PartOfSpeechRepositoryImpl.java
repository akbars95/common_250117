package com.mtsmda.word.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by dminzat on 2/28/2017.
 */
@Repository
public class PartOfSpeechRepositoryImpl extends ParentRepository implements PartOfSpeechRepository {

    /*@Override
    public boolean insertPartOfSpeech(PartOfSpeech partOfSpeech) {
        setQuery("insert into T_PART_OF_SPEECHES(PART_OF_SPEECH)" +
                "values(:PART_OF_SPEECH)");
        clearParamIfNotEmpty();
        params.put("PART_OF_SPEECH", partOfSpeech.getPartOfSpeech());
        int update = getNamedParameterJdbcTemplate().update(getQuery(), params);
        return isSuccess(update);
    }

    @Override
    public boolean deleteAllPartOfSpeech() {
        setQuery("delete from T_PART_OF_SPEECHES");
        clearParamIfNotEmpty();
        int update = getNamedParameterJdbcTemplate().update(getQuery(), params);
        return isSuccess(update);
    }
*/
    @Override
    public <T> void setLogger() {
        LOGGER = Logger.getLogger(PartOfSpeechRepositoryImpl.class);
    }
}