package com.mtsmda.real.project.word.rowmapper;

import com.mtsmda.real.project.word.model.Language;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mtsmda.real.project.word.rowmapper.TableAndFieldsName.LanguageT.T_LANGUAGES_F_LANGUAGE_ID;
import static com.mtsmda.real.project.word.rowmapper.TableAndFieldsName.LanguageT.T_LANGUAGES_F_LANGUAGE_NAME;

/**
 * Created by dminzat on 2/15/2017.
 */
public class LanguageRowMapper implements RowMapper<Language> {

    @Override
    public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
        Language language = new Language();
        try{
            language.setLanguageId(rs.getInt(T_LANGUAGES_F_LANGUAGE_ID));
        }
        catch (Exception e){
            language.setLanguageId(null);
        }

        try{
            language.setLanguageName(rs.getString(T_LANGUAGES_F_LANGUAGE_NAME));
        }
        catch (Exception e){
            language.setLanguageName(null);
        }
        return language;
    }
}