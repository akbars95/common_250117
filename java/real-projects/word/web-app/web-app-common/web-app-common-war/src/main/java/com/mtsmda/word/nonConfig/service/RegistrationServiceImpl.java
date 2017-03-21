package com.mtsmda.word.nonConfig.service;

import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.validation.structure.StructureValidator;
import com.mtsmda.word.nonConfig.dto.RegistrationDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.groups.Default;

/**
 * Created by dminzat on 3/16/2017.
 */
@Service
public class RegistrationServiceImpl extends ParentService implements RegistrationService{

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public CommonResponse<Boolean> register(RegistrationDTO registrationDTO) {
        StructureValidator<RegistrationDTO> registrationDTOStructureValidator = new StructureValidator<>();
        StructureValidator<RegistrationDTO>.StructureValidationResult validate = registrationDTOStructureValidator.validate(registrationDTO, Default.class);
        LOGGER.warn("success - " + validate.isSuccessValidation());
        if(!validate.isSuccessValidation()){
            LOGGER.warn(validate.getStringMessageForLogger());
            return new CommonResponse<>(false, CommonResponse.VALIDATION_ERROR);
        }

        return new CommonResponse<>(true, CommonResponse.SUCCESS);
    }
}