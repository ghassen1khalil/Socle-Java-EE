package com.sifast.socle.javaee.dao;

import com.sifast.socle.javaee.entities.PasswordResetToken;

public interface IPasswordResetTokenDao extends IGenericDao<PasswordResetToken, Integer> {

    PasswordResetToken findByToken(String token);
}