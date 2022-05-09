package com.jwt.authentification.Service;

import com.jwt.authentification.Domaine.User;
import com.jwt.authentification.Exception.ResourceNotFoundException;
import com.jwt.authentification.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PwdService {

    @Autowired
    private UserRepository userRepository;


    public void updateResetPasswordToken(String token, String email) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(token);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepository.findByPassword(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setPassword(null);
        userRepository.save(user);
    }
}

