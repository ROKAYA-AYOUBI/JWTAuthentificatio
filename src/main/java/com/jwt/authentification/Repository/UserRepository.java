package com.jwt.authentification.Repository;


import com.jwt.authentification.Domaine.Role;
import com.jwt.authentification.Domaine.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long>{

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);



    //------------forget pwd
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
    public User findByPassword(String token);





}
