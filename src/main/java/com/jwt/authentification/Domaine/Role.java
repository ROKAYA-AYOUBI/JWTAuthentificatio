package com.jwt.authentification.Domaine;


import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;


/** Entity Role porte les roles  ...*/

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String name;

   // @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
   @ManyToMany
    private Set<User> users;





}
