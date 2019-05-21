package stuffy;

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
@RequestMapping("/stuffies")
public class StuffyController {
	
	@Autowired
	private StuffyRepository stuffyRepository;
	
	@GetMapping("/")
	public @ResponseBody Iterable<Stuffy> getAllStuffies() {
		return stuffyRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Optional<Stuffy> getStuffy(@PathVariable int id) {
		return stuffyRepository.findById(id);		
	}
	
	@PostMapping("/")
	public @ResponseBody String addStuffy(@RequestBody Stuffy stuffy) {
		stuffyRepository.save(stuffy);
		return "Stuffy saved";
	}
	
	@DeleteMapping("/")
	public @ResponseBody String deleteStuffy(@RequestBody Stuffy stuffy) {
		if (stuffyRepository.existsById(stuffy.getId())) {
			stuffyRepository.delete(stuffy);
		} else {
			return "That stuffy does not exist. Delete failed.";
		}
		
		return "Stuffy deleted successfully";
	}
	
	@PutMapping("/")
	public @ResponseBody String updateStuffy(@RequestBody Stuffy stuffy) {
		if (stuffyRepository.existsById(stuffy.getId())) {
			stuffyRepository.save(stuffy);
		} else {
			return "That stuffy does not exist. Update failed.";
		}
		
		return "Stuffy updated successfully";
	}
}
