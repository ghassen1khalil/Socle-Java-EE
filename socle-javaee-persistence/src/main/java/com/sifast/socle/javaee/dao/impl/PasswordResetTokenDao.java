package com.sifast.socle.javaee.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sifast.socle.javaee.dao.IPasswordResetTokenDao;
import com.sifast.socle.javaee.entities.PasswordResetToken;

@Repository("passwordResetTokenDao")
public class PasswordResetTokenDao extends GenericDao<PasswordResetToken, Integer> implements IPasswordResetTokenDao {

    @Override
    public PasswordResetToken findByToken(String token) {
        Query query = getSession().getNamedQuery("findByToken").setString("token", token);
        return findOne(query);
    }
}
