package com.jwt.authentification.controllers;

import com.jwt.authentification.Domaine.Role;
import com.jwt.authentification.Domaine.User;
import com.jwt.authentification.Exception.ResourceNotFoundException;
import com.jwt.authentification.Repository.RoleRepository;
import com.jwt.authentification.Repository.UserRepository;
import com.jwt.authentification.Service.UserService;
import com.jwt.authentification.payload.request.SignupRequest;
import com.jwt.authentification.payload.request.UpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/info")
@Slf4j
public class CRUDController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;
    //-------------------------------------CRUD----------------------------------//


    //--------------affiche tout les users------
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //------------ affiche user par id----------

    @GetMapping("/users/{id}")
    public User getUserInfo(@PathVariable("id") Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        return optional.orElseGet(User::new);
    }
    //--------------affiche tout les users------
    @GetMapping("/users/list")
    public Page<User> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }


    //---------Ajouter une role--------------

    @PostMapping("/addRole")
    public Role saveRole(@RequestBody Role name){
        return userService.saveRole(name);
    }



    //--------------supprimir user---------------


    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }


    //--------------------------------Update-------------------------------------------//





















                                    //-----------------------update------------------------


        //------ Probleme update user whitout  role
    @PutMapping("/{id}")
    public User updateUsertw(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        return userRepository.saveAndFlush(user);

    }




                        //------- work- without Roles change---------//

    @PutMapping("/updatenew/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                                                               @Valid @RequestBody User userDetails ) throws ResourceNotFoundException {
                            User user = userRepository.findById(id)
                                    .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
                           // Role name = roleRepository.findByName(Rolename).orElse(null);

                            //System.out.println(user.getRoles());
                            log.info("role ; {}",user.getRoles());
                            user.setId(userDetails.getId());
                            user.setUsername(userDetails.getUsername());
                            user.setEmail(userDetails.getEmail());
                            user.setPassword(encoder.encode(userDetails.getPassword()));

                            log.info("role ; {}",userDetails.getRoles());
                             user.setRoles(userDetails.getRoles());
                            final User updatedUser = userRepository.save(user);
                            return ResponseEntity.ok(updatedUser);
                        }



    //------------------------------Is not Work---------------------

    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateUserRole(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody UpdateRequest updateRequest)throws ResourceNotFoundException{

        // update user object

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));


        user.setUsername(updateRequest.getUsername());
        user.setEmail(updateRequest.getEmail());
        user.setPassword(encoder.encode(updateRequest.getPassword()));

        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User update successfully", HttpStatus.OK);

    }










}
