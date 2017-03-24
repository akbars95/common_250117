package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.helper.ExceptionMessageHelper;
import com.mtsmda.helper.ListHelper;
import com.mtsmda.helper.MapHelper;
import com.mtsmda.helper.QueryCreatorHelper;
import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.spring.helper.response.CommonResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mtsmda.real.project.user.rowmapper.TableAndFieldsName.AccountT.*;
import static com.mtsmda.spring.helper.response.CommonResponse.ERROR;
import static com.mtsmda.spring.helper.response.CommonResponse.SUCCESS;

/**
 * Created by dminzat on 3/22/2017.
 */
@Repository
public class AccountRepositoryImpl extends ParentRepository implements AccountRepository {

    @Override
    public CommonResponse<Boolean> insertAccount(Account account) {
        List<String> listWithParams = ListHelper.getListWithData(T_ACCOUNTS_F_ACCOUNT_USER_ID, T_ACCOUNTS_F_ACCOUNT_USERNAME,
                T_ACCOUNTS_F_ACCOUNT_PASSWORD);
        try {
            setQuery(QueryCreatorHelper.insertGenerate(T_ACCOUNTS, listWithParams));
            params = MapHelper.getMap(listWithParams, ListHelper.getListWithData(account.getUser().getUserId() , account.getAccountUsername(),
                    account.getAccountPassword()));
            setSuccess(isSuccess(getNamedParameterJdbcTemplate().update(getQuery(), params)));
            return resultSuccessCUD(SUCCESS);
        } catch (Exception e) {
            return exceptionHandlerCUD(ERROR, e);
        }
    }

}