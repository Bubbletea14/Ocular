package com.github.bubbletea14.ocular.ocular.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.bubbletea14.ocular.ocular.services.UsersService;
import com.github.bubbletea14.ocular.ocular.tables.Users;

import jakarta.servlet.http.HttpServletResponse;

//This class will have all of the resources for our api

@RestController		// Annotation for Restfuls API
@RequestMapping(path = "api/v1/Users")
public class UsersController {
    //Reference
    private final UsersService usersService;

    @RequestMapping("/login")
    public String loginPage() {
        return "login"; 
    }

    @Autowired
    //Constructor
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    // User login endpoint
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody Users loginData) {
        var authenticatedUser = usersService.authenticate(loginData.getUsername(), loginData.getPassword());
    
        if (authenticatedUser.isPresent()) {
            return "Login successful"; 
        } else {
            throw new IllegalArgumentException("Invalid username or password."); // Return 400 Bad Request on failure
        }
    }


    @GetMapping		//Annotation for mapping HTTP GET requests onto specific handler methods
    public List<Users> getUsers() {
        return usersService.getUsers();
	}

    // Add Users (Created Users / register)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Users registerUser(@RequestBody Users user) {
        // Check the vilidation
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("Cant be null");
        }
        return usersService.registerUser(user);
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @PutMapping("/{id}")
    public Optional<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return usersService.deleteUser(id);
    }
}
