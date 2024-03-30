package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bubbletea14.ocular.ocular.services.UsersService;
import com.github.bubbletea14.ocular.ocular.tables.Users;

// This class will have all of the resources for our api
@RestController		// Annotation for Restfuls API
@RequestMapping(path = "api/v1/Users")
public class UsersController {
	
    // Reference
    private final UsersService usersService;

    @Autowired
    // Constructor
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping		// 	Annotation for mapping HTTP GET requests onto specific handler methods
    public List<Users> getUsers() {
        return usersService.getUsers();
	}

     @PostMapping
    public void addUser(@RequestBody Users user) {
        usersService.addUser(user);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Users user) {
        usersService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }

   

}
