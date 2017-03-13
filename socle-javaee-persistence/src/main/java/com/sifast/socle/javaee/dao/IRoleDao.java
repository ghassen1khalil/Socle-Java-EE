package com.sifast.socle.javaee.dao;

import com.sifast.socle.javaee.entities.Role;

public interface IRoleDao extends IGenericDao<Role, Integer> {

    Role findByDesignation(String designation);
}