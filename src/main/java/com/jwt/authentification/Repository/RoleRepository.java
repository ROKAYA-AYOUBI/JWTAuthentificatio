package com.jwt.authentification.Repository;

import com.jwt.authentification.Domaine.ERole;
import com.jwt.authentification.Domaine.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
   //Optional<Role> findByName(ERole name);

   Optional<Role> findByName(String name);


}
