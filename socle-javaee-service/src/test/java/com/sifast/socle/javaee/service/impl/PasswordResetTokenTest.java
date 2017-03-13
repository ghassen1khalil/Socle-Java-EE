package com.sifast.socle.javaee.service.impl;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sifast.socle.javaee.dao.IRoleDao;
import com.sifast.socle.javaee.entities.Role;
import com.sifast.socle.javaee.service.IRoleService;

@RunWith(EasyMockRunner.class)
public class PasswordResetTokenTest {

    public static final Logger logger = LoggerFactory.getLogger(PasswordResetTokenTest.class);

    private static IRoleDao roleDao;

    private static IRoleService roleService;

    @BeforeClass
    public static void setUpClass() {
        // Create a mock object
        roleDao = EasyMock.createMock(IRoleDao.class);
        roleService = EasyMock.createMock(IRoleService.class);
    }

    @Test
    public void testSave() {
        Role role = new Role();

        // add the behavior of save dao
        EasyMock.expect(roleDao.save(role)).andReturn(saveRoleInDao(role));

        // activate the mock
        EasyMock.replay(roleDao);

        roleDao.save(role);

        // verify call to calcService is made or not
        EasyMock.verify(roleDao);

        // --------------- call service ----------------------------------
        EasyMock.expect(roleService.save(role)).andReturn(saveRoleInService(role));
        EasyMock.replay(roleService);
        Role roleFromService = roleService.save(role);
        logger.info(">>>>Role retrieved by service : id =" + role.getId() + " designation = " + role.getDesignation());
        EasyMock.verify(roleService);

        // Verifications
        Assert.assertNotNull(roleFromService);
        Assert.assertTrue(roleFromService.getId() == role.getId());
        Assert.assertTrue(roleFromService.getId() == 1);
        Assert.assertTrue(roleFromService.getDesignation().equals("admin"));
    }

    @SuppressWarnings("nls")
    private Role saveRoleInDao(Role role) {
        Role role2Return = role;
        role.setId(1);
        return role2Return;
    }

    @SuppressWarnings("nls")
    private Role saveRoleInService(Role role) {
        Role role2Return = role;
        role2Return.setDesignation("admin");
        return role2Return;
    }

}