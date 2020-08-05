package com.me.callme.api;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.me.callme.model.Billing;
import com.me.callme.model.Redeem;
import com.me.callme.repository.RedeemRepository;
import com.me.callme.repository.productRepository;
import com.me.callme.service.NotificationService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Redeem", tags = { "Redeem Api" })
@CrossOrigin(origins = "*")
public class RedeemController {

	@Autowired
	RedeemRepository mRedeemRepository;
	

	@Autowired
	private NotificationService mNotificationService;
	
	
	@GetMapping("/Redeem")
	public List<Redeem> getAll() {
		return mRedeemRepository.findAll();
	}

	// Get a Single Note
	@GetMapping("/Redeem/{id}")
	public Redeem getNoteById(@PathVariable(value = "id") Integer noteId) {
		return mRedeemRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Redeem", "id", noteId));
	}

	
	  // Get a Single Note
	  
	  @GetMapping("/Redeem/{UserId}/getNodeWithUserId/") 
	  public List<Redeem> getNoteByCan(@PathVariable(value = "UserId") Integer userid) { 
		
		  
		  Pageable request = PageRequest.of(0, 10, Sort.Direction.DESC, "id");

		  return   mRedeemRepository.findByUserId(userid,request); 
		  }
	 

	// Update a Note
	@PostMapping("/Redeem/{id}")
	public Redeem updateNote(@PathVariable(value = "id") Integer noteId, @Valid @RequestBody Redeem noteDetails) {
		Redeem note = mRedeemRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Redeem", "id", noteId));
//		    note.setTitle(noteDetails.getTitle());
//		    note.setContent(noteDetails.getContent());
		note.setStatus(noteDetails.getStatus());
		Redeem updatedNote = mRedeemRepository.save(note);
		
		if (Optional.ofNullable(updatedNote).isPresent()) {
			String msg1="",msg = "Dear Subscriber, Your redemption request has been Failed. Please check the redemption details and try again.";
			if(updatedNote.getStatus()==2) {
				msg = "Dear Subscriber, Your redemption request has been processed Successfully. Now GET Rs.2/Min for all your incoming calls.";
				
			}else if(updatedNote.getStatus()==0) {
				msg = "We have received your redemption request. Same will be processed sortly.";
			}
		
			mNotificationService.generateNotify(updatedNote.getUser_id(), msg, "MESSAGE");
			
			if(updatedNote.getStatus()==2 && (updatedNote.getAmount()/25==1 || updatedNote.getAmount()/25==2 || updatedNote.getAmount()%100==0)) {
				msg1 = "Dear Subscriber, You are now eligible to Redeem Your incoming minutes. Now GET Rs.2/Min for all your incoming calls.";
				mNotificationService.generateNotify(updatedNote.getUser_id(), msg1, "MESSAGE");
			}
		}
		return updatedNote;
	}

	@PostMapping("/Redeem")
	public Redeem SaveNote(@RequestBody Redeem noteDetails) {

		Redeem updatedNote = mRedeemRepository.save(noteDetails);
		if (Optional.ofNullable(updatedNote).isPresent()) {
			mNotificationService.notificationRedeem(updatedNote.getUser_id());
		}
		return updatedNote;
	}

//	// Delete a Note
//	@DeleteMapping("/Redeem/{id}")
//	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
//		Redeem note = mRedeemRepository.findById(noteId)
//				.orElseThrow(() -> new ResourceNotFoundException("Redeem", "id", noteId));
//
//		mRedeemRepository.delete(note);
//
//		return ResponseEntity.ok().build();
//	}
	
	
}
