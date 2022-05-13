package com.jwt.authentification.Service;

import com.jwt.authentification.Domaine.Role;
import com.jwt.authentification.Domaine.User;
import com.jwt.authentification.Exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


public interface UserService {

    //-------update un user-------
    public User updateUser(Long id, User user);

    //----add Role -----
    public Role saveRole(Role role);

    //----Rest password
    public void updateResetPasswordToken(String token, String email) throws ResourceNotFoundException;
    public User getByResetPasswordToken(String token);
    public void updatePassword(User user, String newPassword);

    //-----------update methode





}
