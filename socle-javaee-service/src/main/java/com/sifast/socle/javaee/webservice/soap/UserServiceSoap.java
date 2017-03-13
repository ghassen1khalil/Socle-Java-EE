package com.sifast.socle.javaee.webservice.soap;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.sifast.socle.javaee.entities.User;
import com.sifast.socle.javaee.service.IUserService;

@WebService(endpointInterface = "com.sifast.socle.javaee.webservice.soap.IUserServiceSoap")
public class UserServiceSoap implements IUserServiceSoap {

    @Autowired
    private IUserService userService;

    @Override
    public List<User> findAll() {
        return userService.findAll(User.class);
    }

    @Override
    public User save(User user) {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        user.setPassword(encoder.encodePassword(user.getPassword(), user.getEmail()));
        return userService.save(user);
    }

    @Override
    public User update(User user) {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        user.setPassword(encoder.encodePassword(user.getPassword(), user.getEmail()));
        return userService.update(user);
    }

    @Override
    public void delete(int idUser) {
        User userToBeDeleted = userService.findById(User.class, idUser);
        userService.delete(userToBeDeleted);
    }
}
