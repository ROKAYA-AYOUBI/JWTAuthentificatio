package com.jwt.authentification.Domaine;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Size;



/** Entity User contine les information necessaire ...*/


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles= new HashSet<>();



    public User(String username, String email, String password) {


        this.username = username;
        this.email = email;
        this.password = password;

    }


}
