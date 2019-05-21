package hello;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Optional<User> getUser(@PathVariable int id) {
		return userRepository.findById(id);		
	}
	
	@PostMapping("/")
	public @ResponseBody String addUser(@RequestBody User user) {
		userRepository.save(user);
		return "User saved";
	}
	
	@DeleteMapping("/")
	public @ResponseBody String deleteUser(@RequestBody User user) {
		if (userRepository.existsById(user.getId())) {
			userRepository.delete(user);
		} else {
			return "That user does not exist. Delete failed.";
		}
		
		return "User deleted succesfully";
	}
	
	@PutMapping("/")
	public @ResponseBody String updateUser(@RequestBody User user) {
		if (userRepository.existsById(user.getId())) {
			userRepository.save(user);
		} else {
			return "That user does not exist. Update failed.";
		}
		
		return "User updated succesfully";
	}
}
