package com.mtsmda.word.repository;



import com.mtsmda.real.project.word.model.Language;

import java.util.List;

/**
 * Created by dminzat on 2/6/2017.
 */
public interface LanguageRepository {

    boolean insertLanguage(Language language);
    boolean deleteAllLanguage();
    List<Language> getAllLanguages();

}