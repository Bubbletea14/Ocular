package com.github.bubbletea14.ocular.ocular.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Users;
import com.github.bubbletea14.ocular.ocular.tables.UsersRepository;

//@Component
@Service
public class UsersService {

		private final UsersRepository usersRepository;

		@Autowired
		public UsersService(UsersRepository usersRepository) {
			this.usersRepository = usersRepository;
		}

		// Get User by Id
		public Optional<Users> getUserById(Long id){
			return usersRepository.findById(id);
		}

		// Get users list
		public List<Users> getUsers() {
			return usersRepository.findAll();
		}	
		
		// Add(Register) user
		public Users registerUser(Users user) {
			return usersRepository.save(user);
		}
		
		// Update User
		public Optional<Users> updateUser(Long id, Users newUser) {
			return usersRepository.findById(id).map(existingUser -> {
				existingUser.setUsername(newUser.getUsername());
				existingUser.setPassword(newUser.getPassword());
				existingUser.setEmail(newUser.getEmail());
				return usersRepository.save(existingUser);
			});
		}
		
		public boolean deleteUser(Long id) {
			if (usersRepository.existsById(id)) {
				usersRepository.deleteById(id);
			}
			return true;
		}

		// Authenticate a user
		public Optional<Users> authenticate(String username, String password) {
			// Find user by username
			Optional<Users> user = Optional.ofNullable(usersRepository.findByUsername(username));
			 if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user; // Successful authentication
        } else {
            return Optional.empty(); // Authentication failed
        }
		}
	
		
		
}

