package com.mma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mma.business.JsonResponse;
import com.mma.business.LineItem;
import com.mma.db.LineItemRepository;

@RestController
@RequestMapping("/line-items")
public class LineItemController {

	@Autowired
	private LineItemRepository lineItemRepo;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(lineItemRepo.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (lineItemRepo.existsById(id)) {
				jr = JsonResponse.getInstance(lineItemRepo.findById(id));
			} else {
				jr = JsonResponse.getInstance("No lineItem found for id: " + id);
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/")
	public JsonResponse add(@RequestBody LineItem p) {
		JsonResponse jr = null;
		// NOTE: May need to enhance execption handling if more than one excpetion type
		// needs to be caught
		try {
			jr = JsonResponse.getInstance(lineItemRepo.save(p));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/")
	public JsonResponse update(@RequestBody LineItem p) {
		JsonResponse jr = null;
		// NOTE: May need to enhance execption handling if more than one excpetion type
		// needs to be caught
		try {
			if (lineItemRepo.existsById(p.getId())) {
				jr = JsonResponse.getInstance(lineItemRepo.save(p));
			} else {
				jr = JsonResponse
						.getInstance("LineItem ID: " + p.getId() + " does not exist and you are attempting to save it");
			}

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@DeleteMapping("/")
	public JsonResponse delete(@RequestBody LineItem p) {
		JsonResponse jr = null;
		// NOTE: May need to enhance execption handling if more than one excpetion type
		// needs to be caught
		try {
			if (lineItemRepo.existsById(p.getId())) {
				lineItemRepo.delete(p);
				jr = JsonResponse.getInstance("LineItem deleted");
			} else {
				jr = JsonResponse.getInstance(
						"LineItem ID: " + p.getId() + " does not exist and you are attempting to delete it.");
			}

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

}
