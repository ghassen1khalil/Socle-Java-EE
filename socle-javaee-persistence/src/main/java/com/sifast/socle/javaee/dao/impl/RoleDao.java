package com.sifast.socle.javaee.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sifast.socle.javaee.dao.IRoleDao;
import com.sifast.socle.javaee.entities.Role;

@Repository("roleDao")
public class RoleDao extends GenericDao<Role, Integer> implements IRoleDao {

    @Override
    public Role findByDesignation(String designation) {
        Query query = getSession().getNamedQuery("findByDesignation").setString("designation", designation);
        return findOne(query);
    }
}