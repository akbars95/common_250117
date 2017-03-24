package com.mtsmda.word.nonConfig.repository;

import com.mtsmda.real.project.user.model.Group;
import com.mtsmda.spring.helper.response.CommonResponse;

/**
 * Created by dminzat on 3/23/2017.
 */
public interface GroupRepository {

    CommonResponse<Group> getGroupByName(String groupName);

}