package com.mma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mma.business.Invoice;
import com.mma.business.JsonResponse;
import com.mma.db.InvoiceRepository;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepo;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(invoiceRepo.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (invoiceRepo.existsById(id)) {
				jr = JsonResponse.getInstance(invoiceRepo.findById(id));
			} else {
				jr = JsonResponse.getInstance("No invoice found for id: " + id);
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/")
	public JsonResponse add(@RequestBody Invoice i) {
		JsonResponse jr = null;
		// NOTE: May need to enhance execption handling if more than one excpetion type
		// needs to be caught
		try {
			jr = JsonResponse.getInstance(invoiceRepo.save(i));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/")
	public JsonResponse update(@RequestBody Invoice i) {
		JsonResponse jr = null;
		// NOTE: May need to enhance execption handling if more than one excpetion type
		// needs to be caught
		try {
			if (invoiceRepo.existsById(i.getId())) {
				jr = JsonResponse.getInstance(invoiceRepo.save(i));
			} else {
				jr = JsonResponse
						.getInstance("Invoice ID: " + i.getId() + " does not exist and you are attempting to save it");
			}

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@DeleteMapping("/")
	public JsonResponse delete(@RequestBody Invoice i) {
		JsonResponse jr = null;
		// NOTE: May need to enhance execption handling if more than one excpetion type
		// needs to be caught
		try {
			if (invoiceRepo.existsById(i.getId())) {
				invoiceRepo.delete(i);
				jr = JsonResponse.getInstance("Invoice deleted");
			} else {
				jr = JsonResponse.getInstance(
						"Invoice ID: " + i.getId() + " does not exist and you are attempting to delete it.");
			}

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

}
