package com.me.callme.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.callme.model.Billing;
import com.me.callme.model.Redeem;
import com.me.callme.repository.BillingRepository;

import io.swagger.annotations.Api;

@RestController
//@RequestMapping("/billing")
@Api(value = "BillingApi", tags = { "Billing Api" })
@CrossOrigin(origins = "*")
public class BillingController {

	@Autowired
	BillingRepository mBillingRepository;

	@GetMapping("/Billing")
	public List<Billing> getAll() {
		return mBillingRepository.findAll();
	}

	// Get a Single Note
	@GetMapping("/Billing/{id}")
	public Billing getNoteById(@PathVariable(value = "id") Integer noteId) {
		return mBillingRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Billing", "id", noteId));
	}

	/*
	 * // Get a Single Note
	 * 
	 * @GetMapping("/Billing/{can}/getNodeWithCan/") public Billing
	 * getNoteByCan(@PathVariable(value = "can") String can) { return
	 * mBillingRepository.getForm(can); }
	 */

	// Update a Note
	@PostMapping("/Billing/{id}")
	public Billing updateNote(@PathVariable(value = "id") Integer noteId, @Valid @RequestBody Billing noteDetails) {
		Billing note = mBillingRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Billing", "id", noteId));
//		    note.setTitle(noteDetails.getTitle());
//		    note.setContent(noteDetails.getContent());
		Billing updatedNote = mBillingRepository.save(note);
		return updatedNote;
	}

	  @GetMapping("/Billing/{UserId}/getNodeWithUserId/") 
	  public List<Billing> getNoteByCan(@PathVariable(value = "UserId") Integer userid) { 
	
		  Pageable request = PageRequest.of(0, 10, Sort.Direction.DESC, "id");

		  return   mBillingRepository.findByUserId( userid,request); 
		  }
	
	
	@PostMapping("/Billing")
	public Billing SaveNote(@RequestBody Billing noteDetails) {

		Billing updatedNote = mBillingRepository.save(noteDetails);
		return updatedNote;
	}

//	// Delete a Note
//	@DeleteMapping("/Billing/{id}")
//	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
//		Billing note = mBillingRepository.findById(noteId)
//				.orElseThrow(() -> new ResourceNotFoundException("Billing", "id", noteId));
//
//		mBillingRepository.delete(note);
//
//		return ResponseEntity.ok().build();
//	}

}
