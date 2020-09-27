package com.me.callme.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.callme.model.Billing;
import com.me.callme.model.Product;
import com.me.callme.repository.BillingRepository;
import com.me.callme.repository.ProductRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/product")
@Api(value = "ProductApi", tags = { "product Api" })
public class ProductController {

	@Autowired
	ProductRepository mproductRepository;

	@GetMapping("/Product")
	public List<Product> getAll() {
		return mproductRepository.findAll();
	}

	// Get a Single Note
	@GetMapping("/Product/{id}")
	public Product getNoteById(@PathVariable(value = "id") Long noteId) {
		return mproductRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", noteId));
	}

	/*
	 * // Get a Single Note
	 * 
	 * @GetMapping("/Billing/{can}/getNodeWithCan/") public Billing
	 * getNoteByCan(@PathVariable(value = "can") String can) { return
	 * mBillingRepository.getForm(can); }
	 */

	// Update a Note
	@PostMapping("/Product/{id}")
	public Product updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Billing noteDetails) {
		Product note = mproductRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", noteId));
//		    note.setTitle(noteDetails.getTitle());
//		    note.setContent(noteDetails.getContent());
		Product updatedNote = mproductRepository.save(note);
		return updatedNote;
	}

	@PostMapping("/Product")
	public Product SaveNote(@RequestBody Product noteDetails) {

		Product updatedNote = mproductRepository.save(noteDetails);
		return updatedNote;
	}

//	// Delete a Note
//	@DeleteMapping("/Product/{id}")
//	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
//		Product note = mproductRepository.findById(noteId)
//				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", noteId));
//
//		mproductRepository.delete(note);
//
//		return ResponseEntity.ok().build();
//	}

	
}
