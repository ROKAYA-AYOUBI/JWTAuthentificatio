package com.jwt.authentification.controllers;

import com.jwt.authentification.Domaine.Role;
import com.jwt.authentification.Domaine.User;
import com.jwt.authentification.Repository.RoleRepository;
import com.jwt.authentification.Repository.UserRepository;
import com.jwt.authentification.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/info")
public class CRUDController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;
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

    //--------------supprimir user---------------


    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

   //---------Ajouter une role--------------

    @PostMapping("/addRole")
    public Role saveRole(@RequestBody Role name){
        return userService.saveRole(name);
    }


    //--------------------------------Update-------------------------------------------//
        //------ Probleme update user whitout  role
    @PutMapping("/{id}")
    public User updateUsertw(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        return userRepository.saveAndFlush(user);
    }

                            //-------Not work----------//

    @PutMapping ("/users/{id}")
    public ResponseEntity<User>updateUser(@PathVariable("id") Long id,@RequestBody User newUser) {
        return ResponseEntity.ok().body(userService.updateUser(id,newUser));

    }

                        //-------Not work----------//


    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> setAdmin(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);
       // Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        Optional<Role> adminRole = roleRepository.findByName("name");
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole.get());
        if (!userData.isPresent()) {
            User _user = userData.get();
            _user.setRoles(roles);
            user.setId(id);
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //---------------------------------------------------









}
