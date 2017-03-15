package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.user.model.Role;
import com.mtsmda.spring.helper.response.CommonResponse;

import java.util.List;

/**
 * Created by dminzat on 3/13/2017.
 */
public interface RoleRepository {

    CommonResponse<List<Role>> getUserRolesByUsername(String username);

}