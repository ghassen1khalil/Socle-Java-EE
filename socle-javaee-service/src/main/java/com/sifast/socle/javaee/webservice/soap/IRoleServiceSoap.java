package com.sifast.socle.javaee.webservice.soap;

import java.util.List;

import javax.jws.WebService;

import org.springframework.transaction.annotation.Transactional;

import com.sifast.socle.javaee.entities.Role;

@WebService
@Transactional
public interface IRoleServiceSoap {

    List<Role> findAll();

    Role save(Role role);

    Role update(Role role);

    void delete(int idRole);
}