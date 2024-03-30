package com.github.bubbletea14.ocular.ocular.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Users;

//@Component
@Service
public class UsersService {


    // public List<Users> getUsers() {
	// 	return List.of(
	// 		new Users(
	// 		1L,
	// 		"Admin",
	// 		"111",
	// 		LocalDate.of(2010, Month.AUGUST,5),
	// 		"admin.admin@admin.com"
	// 		),

	// 		new Users(
	// 		2L,
	// 		"User1",
	// 		"222",
	// 		LocalDate.of(2010, Month.JANUARY,3),
	// 		"user1.user1@user1.com"
	// 		)
	// 	);
		private final List<Users> usersList = new ArrayList<>();
		
		public UsersService(){
			usersList.add(new Users(1L, "Admin", "111", LocalDate.of(2010, Month.AUGUST, 5), "admin.admin@admin.com"));
			usersList.add(new Users(2L, "User1", "222", LocalDate.of(2011, Month.JANUARY, 3), "admin.admin@admin.com"));
		}

		public Users getUserById(Long id){
			for (Users user : usersList) {
				if (user.getId()==id) {
					return user;
				}
			}
			return null; // If no user with the given ID is found
		}

		public List<Users> getUsers() {
			return usersList;
		}	
	
		public void addUser(Users user) {
			usersList.add(user);
		}

		public void updateUser(Long id, Users newUser) {
			// for (int i = 0;i<usersList.size();i++){
			// 	if(usersList.get(i).getId() == id) {
			// 		Users user = usersList.get(i);
			// 	}
			// 	//return null
			// }
			for (Users user : usersList) {
				if (user.getId() == id) {
					user.setUsername(newUser.getUsername());
					user.setPassword(newUser.getPassword());
					user.setDob(newUser.getDob());
					user.setEmail(newUser.getEmail());
					break;
				}
			}
		}
		
		public void deleteUser(Long id) {
			usersList.removeIf(user -> user.getId() == id);
		}
		
}

