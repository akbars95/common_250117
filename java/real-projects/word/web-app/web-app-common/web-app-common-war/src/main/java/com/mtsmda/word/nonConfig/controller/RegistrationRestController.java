package com.mtsmda.word.nonConfig.controller;

import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.nonConfig.common.LoggerAC;
import com.mtsmda.word.nonConfig.dto.RegistrationDTO;
import com.mtsmda.word.nonConfig.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dminzat on 3/16/2017.
 */
@RestController
public class RegistrationRestController extends LoggerAC {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value = "/register")
    public boolean register(@RequestBody RegistrationDTO registrationDTO) {
        if(ObjectHelper.objectIsNull(registrationDTO)){
            return false;
        }
        LOGGER.info(registrationDTO.toString());
        CommonResponse<Boolean> booleanCommonResponse = registrationService.register(registrationDTO);
        LOGGER.info(booleanCommonResponse.toString());
        return booleanCommonResponse.getObject();
    }

}