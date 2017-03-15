package com.mtsmda.word.test.repository;

import com.mtsmda.real.project.user.model.Role;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.word.nonConfig.repository.RoleRepository;
import com.mtsmda.word.test.config.TestApplicationContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by dminzat on 3/13/2017.
 */
/*@Test
@Import(value = BeanConfiguration.class)
//@ContextConfiguration(classes = , loader = AnnotationConfigContextLoader.class)
@TestPropertySource("classpath:spring/properties/database.properties")
@ComponentScan(basePackages = {"com.mtsmda.word.nonConfig"})
@DirtiesContext*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApplicationContext.class}, loader = AnnotationConfigContextLoader.class)
public class RoleRepositoryImplTest /*extends AbstractTestNGSpringContextTests*//*extends TestApplicationContext*/{

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void test() {
        assertNotNull(roleRepository);
        CommonResponse<List<Role>> userRolesByUsername = roleRepository.getUserRolesByUsername("ivanov.ivan");
        assertNotNull(userRolesByUsername.getObject());
        List<Role> object = userRolesByUsername.getObject();
        assertTrue(object.size() == 2);
    }

}