package com.jwt.authentification.Service;

import com.jwt.authentification.Domaine.User;
import com.jwt.authentification.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {


    public User updateUser(Long id, User user);
}
