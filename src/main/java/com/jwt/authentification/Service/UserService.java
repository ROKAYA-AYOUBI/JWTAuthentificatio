package com.jwt.authentification.Service;

import com.jwt.authentification.Domaine.Role;
import com.jwt.authentification.Domaine.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    //-------update un user-------
    public User updateUser(Long id, User user);
    //----add Role -----
    public Role saveRole(Role role);


}
