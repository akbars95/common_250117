package com.mtsmda.word.rowmapper;

import com.mtsmda.word.model.Language;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.word.rowmapper.TableAndFieldsName.*;
/**
 * Created by dminzat on 2/15/2017.
 */
public class LanguageRowMapper implements RowMapper<Language> {

    @Override
    public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
        Language language = new Language();
        try{
            language.setLanguageId(rs.getInt(LanguageT.T_LANGUAGES_F_LANGUAGE_ID));
        }
        catch (Exception e){
            language.setLanguageId(null);
        }

        try{
            language.setLanguageName(rs.getString(LanguageT.T_LANGUAGES_F_LANGUAGE_NAME));
        }
        catch (Exception e){
            language.setLanguageName(null);
        }
        return language;
    }
}