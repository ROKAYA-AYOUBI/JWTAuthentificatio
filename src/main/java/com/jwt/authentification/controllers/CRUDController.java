package com.jwt.authentification.controllers;

import com.jwt.authentification.Domaine.Role;
import com.jwt.authentification.Domaine.User;
import com.jwt.authentification.Exception.ResourceNotFoundException;
import com.jwt.authentification.Repository.RoleRepository;
import com.jwt.authentification.Repository.UserRepository;
import com.jwt.authentification.Service.UserService;
import com.jwt.authentification.payload.request.UpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
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
    /*
    Au fait, tu ne met pas à jour l'utilisateur dans la session hibernate. Tu le récupère via retrieve et tu persiste celui en paramètre donc l'utilisateur dans la sessiosn hibernate n'a pas été altéré.

Tu doit le récupérer et tu mes à jour les données depuis celui en paramètre et puis tu fais update sur le premier (récupéré depuis retrieve)
     */
        //------ Probleme update user whitout  role
    @PutMapping("/{id}")
    public User updateUsertw(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        return userRepository.saveAndFlush(user);

    }



                            //-------Not work----------//

    @PostMapping ("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") UpdateRequest updateRequest) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        user.setId(id);

        user.setUsername(updateRequest.getUsername());

        user.setRoles(updateRequest.getRole());

        userService.updateUser(id,user);
                //updateUser(user);

        return "update ";

    }

                        //-------Not work----------//

    @PutMapping("/updatenew/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                                                               @Valid @RequestBody User userDetails ) throws ResourceNotFoundException {
                            User user = userRepository.findById(id)
                                    .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
                           // Role name = roleRepository.findByName(Rolename).orElse(null);

                            System.out.println(user.getRoles());
                            user.setId(userDetails.getId());
                            user.setUsername(userDetails.getUsername());
                            user.setEmail(userDetails.getEmail());
                            log.info("role ; {}",userDetails.getRoles());

                            //user.setRoles((Set<Role>) name);
                            user.setPassword(userDetails.getPassword());


                            final User updatedUser = userRepository.save(user);
                            return ResponseEntity.ok(updatedUser);
                        }



    //---------------------------------------------------












}
