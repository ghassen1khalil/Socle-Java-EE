package com.sifast.socle.javaee.webservice.soap;

import java.util.List;

import javax.jws.WebService;

import org.springframework.transaction.annotation.Transactional;

import com.sifast.socle.javaee.entities.User;

@WebService
@Transactional
public interface IUserServiceSoap {

    List<User> findAll();

    User save(User user);

    User update(User user);

    void delete(int idUser);
}