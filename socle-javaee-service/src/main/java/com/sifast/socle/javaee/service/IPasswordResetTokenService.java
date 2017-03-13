package com.sifast.socle.javaee.service;

import org.springframework.transaction.annotation.Transactional;

import com.sifast.socle.javaee.entities.PasswordResetToken;

@Transactional
public interface IPasswordResetTokenService extends IGenericService<PasswordResetToken, Integer> {

    PasswordResetToken findTokenByToken(String token);

    boolean resetPassword(String email);
}