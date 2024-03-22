package com.github.bubbletea14.ocular.ocular.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Users;

//@Component
@Service
public class UsersService {
    public List<Users> getUsers() {
		return List.of(
			new Users(
			1L,
			"Admin",
			"123",
			LocalDate.of(2010, Month.AUGUST,5),
			"admin.admin@admin.com"
			)
		);
	}
}
