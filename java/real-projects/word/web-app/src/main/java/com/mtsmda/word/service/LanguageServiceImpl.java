package com.mtsmda.word.service;

import com.mtsmda.word.model.Language;
import com.mtsmda.word.repository.LanguageRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dminzat on 2/6/2017.
 */
@Service
public class LanguageServiceImpl extends ParentService implements LanguageService {

    private static final Logger LOGGER = Logger.getLogger(LanguageServiceImpl.class);

    @Autowired
    private LanguageRepository languageRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Language> getAllLanguages() {
        LOGGER.info("get all languages");
        return languageRepository.getAllLanguages();
    }
}