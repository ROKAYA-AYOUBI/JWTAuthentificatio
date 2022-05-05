package com.jwt.authentification.controllers;



import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {


    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/meb")
    @PreAuthorize("hasRole('MEMBER')")
    public String memberAccess() {
        return "Member Board.";
    }

    @GetMapping("/adh")
    @PreAuthorize("hasRole('ADHERENT')")
    public String sdherentAccess() {
        return "Adherent Board.";
    }

    @GetMapping("/par")
    @PreAuthorize("hasRole('PARTENAIRE')")
    public String partenaireAccess() {
        return "Partenaire Board.";
    }



    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
