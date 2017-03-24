package com.mtsmda.word.nonConfig.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by dminzat on 3/23/2017.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public String getHashedPassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

}