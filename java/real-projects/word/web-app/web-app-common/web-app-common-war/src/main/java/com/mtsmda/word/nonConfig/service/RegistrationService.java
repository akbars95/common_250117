package com.mtsmda.word.nonConfig.service;

import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.nonConfig.dto.RegistrationDTO;

/**
 * Created by dminzat on 3/16/2017.
 */
public interface RegistrationService {

    CommonResponse<Boolean> register(RegistrationDTO registrationDTO);

}