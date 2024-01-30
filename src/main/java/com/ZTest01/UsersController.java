package com.ZTest01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersRepository usersRepository;

	@PostMapping("/add")
	public String addUser(@RequestBody Users user) {
		usersRepository.save(user);
		return "User added successfully";
	}

	@GetMapping("/getAll")
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@GetMapping("/get/{userName}")
	public ResponseEntity<Object> getUserByUserName(@PathVariable String userName) {
	    Users user = usersRepository.findByUserName(userName);
	    if (user != null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", true);
	        response.put("message", "User retrieved successfully");
	        response.put("data", user);
	        return ResponseEntity.ok(response);
	    } else {
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", false);
	        response.put("message", "User not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}


	@PutMapping("/update/{userId}")
	public String updateUser(@PathVariable String userId, @RequestBody Users updatedUser) {
		Users existingUser = usersRepository.findById(userId).orElse(null);
		if (existingUser != null) {
			existingUser.setUserName(updatedUser.getUserName());
			usersRepository.save(existingUser);
			return "User updated successfully";
		} else {
			return "User not found";
		}
	}

	@DeleteMapping("/delete/{userId}")
	public String deleteUser(@PathVariable String userId) {
		Users existingUser = usersRepository.findById(userId).orElse(null);
		if (existingUser != null) {
			usersRepository.delete(existingUser);
			return "User deleted successfully";
		} else {
			return "User not found";
		}
	}
}
