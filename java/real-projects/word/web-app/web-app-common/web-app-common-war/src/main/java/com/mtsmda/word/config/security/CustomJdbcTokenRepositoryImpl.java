package com.mtsmda.word.config.security;

import com.mtsmda.helper.LocalDateTimeHelper;
import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.real.project.user.model.PersistentLogin;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.nonConfig.common.Converter;
import com.mtsmda.word.nonConfig.repository.PersistentLoginRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by dminzat on 3/7/2017.
 * temporary this class do not need
 */
@Deprecated
@Component
public class CustomJdbcTokenRepositoryImpl implements PersistentTokenRepository {

    private static final Logger LOGGER = Logger.getLogger(CustomJdbcTokenRepositoryImpl.class);

    @Autowired
    private PersistentLoginRepository persistentLoginRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        persistentLoginRepository.insertPersistentLogin(new PersistentLogin(new Account(token.getUsername()),
                token.getSeries(), token.getTokenValue(), LocalDateTimeHelper.convertDateToLocalDateTime(token.getDate())));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        persistentLoginRepository.updatePersistentLogin(new PersistentLogin(series, tokenValue, LocalDateTimeHelper.convertDateToLocalDateTime(lastUsed)));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            CommonResponse<PersistentLogin> persistentLoginBySeries = persistentLoginRepository.getPersistentLoginBySeries(seriesId);
            if (ObjectHelper.objectIsNotNull(persistentLoginBySeries.getObject())) {
                Converter<PersistentLogin, PersistentRememberMeToken> persistentLoginPersistentRememberMeTokenConverter = persistentLogin ->
                        new PersistentRememberMeToken(persistentLogin.getAccount().getAccountUsername(), persistentLogin.getSeries(),
                                persistentLogin.getToken(), LocalDateTimeHelper.convertLocalDateTimeToDate(persistentLogin.getLastUsed()));
                return persistentLoginPersistentRememberMeTokenConverter.convert(persistentLoginBySeries.getObject());
            } else {
                throw new IncorrectResultSizeDataAccessException(persistentLoginBySeries.getMessageErrorDescription(), persistentLoginBySeries.getCode());
            }
        } catch (EmptyResultDataAccessException zeroResults) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Querying token for series '" + seriesId
                        + "' returned no results.", zeroResults);
            }
        } catch (IncorrectResultSizeDataAccessException moreThanOne) {
            LOGGER.error("Querying token for series '" + seriesId
                    + "' returned more than one value. Series" + " should be unique");
        } catch (DataAccessException e) {
            LOGGER.error("Failed to load token for series " + seriesId, e);
        }
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        persistentLoginRepository.deletePersistentLoginByUsername(username);
    }
}