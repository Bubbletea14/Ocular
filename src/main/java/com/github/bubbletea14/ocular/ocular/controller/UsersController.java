package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//This class will have all of the resources for our api
@RestController		// Annotation for Restfuls API
@RequestMapping(path = "api/v1/Users")
public class UsersController {
    //Reference
    private final UsersService usersService;

    @Autowired
    //Constructor
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping		//Annotation for mapping HTTP GET requests onto specific handler methods
    public List<Users> getUsers() {
        return usersService.getUsers();
	}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Users addUser(@RequestBody Users user) {
        return usersService.addUser(user);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return usersService.deleteUser(id);
    }

}
