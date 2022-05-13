package com.jwt.authentification.Repository;

import com.jwt.authentification.Domaine.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {


  Optional<Role> findByName(String name);
/*
   @Query("SELECT r FROM Role r WHERE r.name = ?1")
   public Role findByName(String name);

 */
}
