package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public void greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			@RequestParam(name = "verb", required = false, defaultValue = "happy") String verb, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("verb", verb);
	}
}
