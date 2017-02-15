package com.mtsmda.word.repository;

import com.mtsmda.word.model.Language;

import java.util.List;

/**
 * Created by dminzat on 2/6/2017.
 */
public interface LanguageRepository {

    List<Language> getAllLanguages();

}