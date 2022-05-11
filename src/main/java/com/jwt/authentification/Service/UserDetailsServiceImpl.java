package com.jwt.authentification.Service;


import com.jwt.authentification.Domaine.ERole;
import com.jwt.authentification.Domaine.Role;
import com.jwt.authentification.Domaine.User;
import com.jwt.authentification.Repository.RoleRepository;
import com.jwt.authentification.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService , UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
    //------------les methode  de interface UserService ------
    @Override
    public User updateUser(Long id, User user) {

        Optional<User> retrievedUser=userRepository.findById(id);
        if(retrievedUser==null)
            try {
                throw new Exception("User not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        userRepository.save(user);
        return userRepository.findById(id).get();

    }


   //----- add roles
    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }


}
