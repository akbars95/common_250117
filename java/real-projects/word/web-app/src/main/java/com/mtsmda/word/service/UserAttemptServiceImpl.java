package com.mtsmda.word.service;

import com.mtsmda.real.project.user.model.UserAttempt;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.repository.UserAttemptRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dminzat on 3/6/2017.
 */
@Service
public class UserAttemptServiceImpl extends ParentService implements UserAttemptService{

    @Autowired
    private UserAttemptRepository userAttemptRepository;

    @Override
    public UserAttempt getUserAttemptByUsername(String username) {
        CommonResponse<UserAttempt> userAttemptByUsername = userAttemptRepository.getUserAttemptByUsername(username);
        if(userAttemptByUsername.getCode().equals(CommonResponse.SUCCESS)){
            return userAttemptByUsername.getObject();
        }
        return null;
    }

    public <T> void setLogger() {
        LOGGER = Logger.getLogger(UserAttemptServiceImpl.class);
    }
}