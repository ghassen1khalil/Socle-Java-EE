package com.sifast.socle.javaee.service;

import org.springframework.transaction.annotation.Transactional;

import com.sifast.socle.javaee.entities.Role;

@Transactional
public interface IRoleService extends IGenericService<Role, Integer> {

    Role findRoleByDesignation(String designation);
}