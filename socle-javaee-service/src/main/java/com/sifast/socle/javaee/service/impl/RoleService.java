package com.sifast.socle.javaee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sifast.socle.javaee.dao.impl.RoleDao;
import com.sifast.socle.javaee.entities.Role;
import com.sifast.socle.javaee.service.IRoleService;
import com.sifast.socle.javaee.service.tracker.Trackable;

@Trackable
@Service("roleService")
public class RoleService extends GenericService<Role, Integer> implements IRoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findRoleByDesignation(String designation) {
        return roleDao.findByDesignation(designation);
    }
}