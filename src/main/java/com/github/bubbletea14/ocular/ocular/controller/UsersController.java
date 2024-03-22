package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
