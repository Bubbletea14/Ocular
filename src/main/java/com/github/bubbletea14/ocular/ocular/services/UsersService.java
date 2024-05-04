package com.github.bubbletea14.ocular.ocular.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Users;

//@Component
@Service
public class UsersService {

		private final List<Users> usersList = new ArrayList<>();
		
		public UsersService(){
			usersList.add(new Users(1L, "User1", "111", "User1.User1@User1.com"));
			usersList.add(new Users(2L, "User2", "222", "User2.User2@User2.com"));
		}

		public Users getUserById(Long id){
			for (Users user : usersList) {
				if (user.getId()==id) {
					return user;
				}
			}
			throw new NoSuchElementException("User with ID " + id + " not found"); 
		}

		//Get users list
		public List<Users> getUsers() {
			return usersList;
		}	
		
		//Add user
		public Users addUser(Users user) {
			usersList.add(user);
			return user;
		}
		
		//Update User
		public Users updateUser(Long id, Users newUser) {
			for (Users user : usersList) {
				if (user.getId() == id) {
					user.setUsername(newUser.getUsername());
					user.setPassword(newUser.getPassword());
					user.setEmail(newUser.getEmail());
					return user; // return update object
				} 
			}
			return null; //If no user with the given ID is found
		}
		
		public boolean deleteUser(Long id) {
			return usersList.removeIf(user -> user.getId() == id);
		}
	
		
		
}

