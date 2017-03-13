package com.sifast.socle.javaee.webservice.soap;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.sifast.socle.javaee.entities.Role;
import com.sifast.socle.javaee.service.IRoleService;

@WebService(endpointInterface = "com.sifast.socle.javaee.webservice.soap.IRoleServiceSoap")
public class RoleServiceSoap implements IRoleServiceSoap {

    @Autowired
    private IRoleService roleService;

    @Override
    public List<Role> findAll() {
        return roleService.findAll(Role.class);
    }

    @Override
    public Role save(Role role) {
        return roleService.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleService.update(role);
    }

    @Override
    public void delete(int idRole) {
        Role roleToBeDeleted = roleService.findById(Role.class, idRole);
        roleService.delete(roleToBeDeleted);
    }
}
