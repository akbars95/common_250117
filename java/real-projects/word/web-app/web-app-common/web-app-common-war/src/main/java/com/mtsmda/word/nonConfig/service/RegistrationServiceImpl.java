package com.mtsmda.word.nonConfig.service;

import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.real.project.user.model.Account;
import com.mtsmda.real.project.user.model.Group;
import com.mtsmda.real.project.user.model.User;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.validation.structure.StructureValidator;
import com.mtsmda.word.nonConfig.dto.RegistrationDTO;
import com.mtsmda.word.nonConfig.repository.AccountRepository;
import com.mtsmda.word.nonConfig.repository.GroupRepository;
import com.mtsmda.word.nonConfig.repository.UserRepository;
import com.mtsmda.word.nonConfig.validation.order.RegistrationOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.groups.Default;

/**
 * Created by dminzat on 3/16/2017.
 */
@Service
public class RegistrationServiceImpl extends ParentService implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private GroupRepository groupRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public CommonResponse<Boolean> register(RegistrationDTO registrationDTO) {
        StructureValidator<RegistrationDTO> registrationDTOStructureValidator = new StructureValidator<>();
        StructureValidator<RegistrationDTO>.StructureValidationResult validate =
                registrationDTOStructureValidator.validate(registrationDTO, Default.class, RegistrationOrder.class);
        LOGGER.warn("success - " + validate.isSuccessValidation());
        if (!validate.isSuccessValidation()) {
            setMessage("Cannot insert user");
            LOGGER.warn(validate.getStringMessageForLogger());
            LOGGER.warn(getMessage());
            return new CommonResponse<>(false, CommonResponse.VALIDATION_ERROR, getMessage());
        }
        User convertedUser = registrationDTO.convert();
        LOGGER.info("convert success, " + convertedUser.toString());
        CommonResponse<Boolean> booleanCommonResponseUser = userRepository.insertUser(convertedUser);
        if (!booleanCommonResponseUser.getObject()) {
            setMessage("Cannot insert user");
            LOGGER.info(getMessage());
            return new CommonResponse<>(false, CommonResponse.ERROR, getMessage());
        }
        LOGGER.info("success insert user");
        CommonResponse<User> lastAddUserBy = userRepository.getLastAddUserBy();
        if (ObjectHelper.objectIsNull(lastAddUserBy.getObject())) {
            setMessage("Cannot get last added user");
            LOGGER.info(getMessage());
            return new CommonResponse<>(false, CommonResponse.ERROR, getMessage());
        }
        LOGGER.info("success get last added user, " + lastAddUserBy.getObject());
        lastAddUserBy.getObject().setAccount(convertedUser.getAccount());
        Account account = lastAddUserBy.getObject().getAccount();
        account.setUser(lastAddUserBy.getObject());
        account.setAccountPassword(commonService.getHashedPassword(account.getAccountPassword()));
        CommonResponse<Boolean> booleanCommonResponseAccount = accountRepository.insertAccount(account);
        if (!booleanCommonResponseAccount.getObject()) {
            setMessage("Cannot insert account");
            LOGGER.info(getMessage());
            return new CommonResponse<>(false, CommonResponse.ERROR, getMessage());
        }
        LOGGER.info("success insert account");
        CommonResponse<Group> groupCommonResponse = groupRepository.getGroupByName("USERS");
        if (ObjectHelper.objectIsNull(groupCommonResponse.getObject())) {
            setMessage("Cannot get group");
            LOGGER.info(getMessage());
            return new CommonResponse<>(false, CommonResponse.ERROR, getMessage());
        }
        lastAddUserBy.getObject().setGroup(groupCommonResponse.getObject());
        LOGGER.info("success get group, group id - " + groupCommonResponse.getObject().getGroupId());
        CommonResponse<Boolean> booleanCommonResponse = userRepository.insertUserGroup(lastAddUserBy.getObject());
        if(!booleanCommonResponse.getObject()){
            setMessage("Cannot insert group for user");
            LOGGER.info(getMessage());
            return new CommonResponse<>(false, CommonResponse.ERROR, getMessage());
        }
        LOGGER.info("success insert user group");

        return new CommonResponse<>(booleanCommonResponseUser.getObject() && booleanCommonResponseAccount.getObject(), CommonResponse.SUCCESS);
    }
}