package com.jwt.authentification.Domaine;


import lombok.*;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.AUTO;


/** Entity Role porte les roles  ...*/

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;



}
