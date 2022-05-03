package com.jwt.authentification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthentificationApplication.class, args);
    }

    /*
    @Bean
    CommandLineRunner run(UserService userService){
        return args-> {

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));


            userService.saveUser(new User(null, "John Travolta","john", "john@f4s.com", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Will Smith","will", "will@f4s.com", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Jim Carry","jim", "jim@f4s.com", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "arnold gomiz","arnold", "arnold@f4s.com", "1234", new ArrayList<>()));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("will", "ROLE_MANAGER");
            userService.addRoleToUser("jim", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_USER");





        };
    }



     */







}
