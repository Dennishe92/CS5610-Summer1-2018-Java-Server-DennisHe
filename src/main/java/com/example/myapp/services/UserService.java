package com.example.myapp.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.User;
import com.example.myapp.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	// Get the list of Users 
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	// Create a User
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	// Delete a User
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {
		repository.deleteById(userId);
	}
	
	// Find a User given its userId
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	// Update User information
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser,
			HttpServletResponse response) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	// findByUserName
	@GetMapping("/api/register/{username}")
	public User findUserByUsername(@PathVariable ("username") String username) {
		return repository.findUserByUsername(username);
	}
	
	// register
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session, 
			HttpServletResponse response) {
		User data = repository.findUserByUsername(user.getUsername());
		if(data == null) {
			User result = createUser(user);
			return result;
			} else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
			}
		}
	
	
	// find user by username and password
	@GetMapping("/api/login/{username}/{password}")
	public User findUserByCredentials(@PathVariable ("username") String username,
			@PathVariable ("password") String password) {
		return repository.findUserByUsernameAndPassword(username, password);
		}
	
	
	// login 
	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpServletRequest request, 
			HttpServletResponse response) {
		User res = repository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (res == null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
			} else {
				request.getServletContext().setAttribute("currentUser", res);
				System.out.print(res.getUsername());
				return res;
				}
		}
	
	
	// Update Profile
	@PutMapping("/api/profile")
	public User	updateProfile(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		//Optional<User> data = repository.findById(userId);
		//User currentUser = (User) session.getAttribute("currentUser");
		User cur = (User) request.getServletContext().getAttribute("currentUser");
		
		if (cur != null) {
			cur.setPhone(user.getPhone());
			cur.setEmail(user.getEmail());
			cur.setRole(user.getRole());
			cur.setDateOfBirth(user.getDateOfBirth());
			repository.save(cur);
			return cur;
		} else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
	}
	
	// Get Profile
	@GetMapping("/api/profile")
	public User getProfile(HttpServletRequest request, HttpServletResponse response) {
		Object curr = request.getServletContext().getAttribute("currentUser");
		
		if(curr != null) {
			return (User) curr;
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
    // Logout
	@PostMapping("/api/profile")
	public void logout(HttpServletRequest request) {
		request.getServletContext().removeAttribute("currentUser");
	}
	
	

	
	

}
