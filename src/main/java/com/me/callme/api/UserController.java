package com.me.callme.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.me.callme.model.Billing;
import com.me.callme.model.User;
import com.me.callme.model.UserMobile;
import com.me.callme.repository.UserMobileResponsibilty;
import com.me.callme.service.NotificationService;
import com.me.callme.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/user")
@Api(value = "UserApi", tags = { "User Api" })
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private UserMobileResponsibilty  mUserMobileResponsibilty;
	
	@Autowired
	private NotificationService mNotificationService;
	
	
	
	  @GetMapping("/mobile/{UserId}/") 
	  public List<UserMobile> getNoteByUSerID(@PathVariable(value = "UserId") Integer userid) { 
		  return   mUserMobileResponsibilty.findByUserId( userid); 
		  }
	
	
	  @PostMapping("/mobile/save")
		public UserMobile SaveNote(@RequestBody UserMobile noteDetails) {

		  UserMobile updatedNote = mUserMobileResponsibilty.save(noteDetails);
			return updatedNote;
		}
	
	
	@ApiOperation(value = "add user", nickname = "addUser", notes = "Add User")
	@PostMapping
	public ResponseEntity<Map<String, Object>> addUser(
			@RequestBody(required = false) User userjson) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			User userdata = userservice.persistuser(userjson);
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", userdata);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "image upload user", nickname = "imageuser", notes = "Image User")
	@PostMapping(value = "/imageupload")
	public ResponseEntity<Map<String, Object>> imageUser(
			@RequestParam(required = true) Long userId,@RequestParam(name="imagefile",required=false) MultipartFile imagefile) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			boolean userdata = userservice.imagestore(userId,imagefile);
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", userdata);
		
				mNotificationService.generateNotify(Integer.parseInt(userId+""), "Dear Subscriber, Your Profile Picture has been uploaded succesfully. Same will be approved within next 24 working Hrs"
						, "MESSAGE");
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("e at image method:"+e);
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "get user", nickname = "getUser", notes = "Get User")
	@GetMapping
	public ResponseEntity<Map<String, Object>> getUser(
			@RequestParam(value = "userid" , required = false) Long userId ,@RequestParam(value = "mobileno", required = false) String mobileno,@RequestParam(value = "username", required = false) String username) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			User userdata = userservice.getUser(userId,mobileno,username);
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", userdata);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "UserProfile", nickname = "UserPrfile", notes = "User Profile")
	@GetMapping(value = "/UserProfile")
	public ResponseEntity<Map<String, Object>> UserProfile(
			@RequestParam(value = "partyA" , required = true) Integer partyA ,@RequestParam(value = "PartyB", required = true) Integer PartyB) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			User userdata = userservice.getUser(new Long(PartyB),null,null);
			
			userdata.setIsBloccked(userservice.blockedUserStatus(new Long(partyA),new Long(PartyB)));
			
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", userdata);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@PutMapping
	@ApiOperation(value = "Edit user", nickname = "editUser", notes = "Edit User")
	public ResponseEntity<Map<String, Object>> edituser(@RequestBody(required = false) User userjson) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			User userdata = userservice.edituser(userjson);
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", userdata);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	
	}
	
	@PostMapping(value = "/blockuser")
	@ApiOperation(value = "Unblock/Blockuser", nickname = "blockUser", notes = "Block User")
	public ResponseEntity<Map<String, Object>> blockuser(@RequestParam(value = "blockedby") Long blockedby,@RequestParam(value = "blockedto") Long blockedto,@RequestParam(value = "blockstatus") boolean flag) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			boolean status = userservice.blockedUser(blockedby,blockedto,flag);
			if (Optional.ofNullable(status).isPresent()) {
				result.put("success", true);
				result.put("result", true);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/favuser")
	@ApiOperation(value = "Unfav/Favuser", nickname = "FavUser", notes = "Fav User")
	public ResponseEntity<Map<String, Object>> favuser(@RequestParam(value = "favby") Long blockedby,@RequestParam(value = "favto") Long blockedto,@RequestParam(value = "favstatus") boolean flag) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			boolean status = userservice.favourUser(blockedby,blockedto,flag);
			if (Optional.ofNullable(status).isPresent()) {
				result.put("success", true);
				result.put("result", true);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/loginlogout")
	@ApiOperation(value = "logout", nickname = "logout", notes = "logout User")
	public ResponseEntity<Map<String, Object>> logoutuser(@RequestParam(value = "userId") Long logoutid,@RequestParam(value = "loginflag") Boolean flag) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			User userdata = userservice.logoutuser(logoutid,flag);
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", true);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	
	}
	
	@PostMapping(value = "/approveimage")
	@ApiOperation(value = "approve Image", nickname = "Approve Image", notes = "Approve Image")
	public ResponseEntity<Map<String, Object>> approveimage(@RequestParam(value = "userId") Long userId,@RequestParam(value = "approveflag") Boolean flag) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			User userdata = userservice.approveimage(userId,flag);
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", true);
				//Add Notification
				mNotificationService.generateNotifyForApprovedImage(Integer.parseInt(userId+""));
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	
	}
	
	@PostMapping(value = "/fcmnotification")
	@ApiOperation(value = "fcmnotification", nickname = "rcmnotification", notes = "rcm notification")
	public ResponseEntity<Map<String, Object>> fcmnotify(@RequestParam(value = "userid") Long userid,@RequestParam(value = "rcmtoken") String rcmtoken) {

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			User userdata = userservice.fcmtoken(userid,rcmtoken);
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", true);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	
	}
	
	
	@ApiOperation(value = "get user online", nickname = "getUser", notes = "Get User")
	@GetMapping("/getOnlineUser")
	public ResponseEntity<Map<String, Object>> getOnlineUser(@RequestParam(value="pageNo", required=false, defaultValue = "0") String pageNo,@RequestParam(value = "userid") Long userid)
	{
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			Set<User> userdata = userservice.getUserOnline(pageNo,userid);
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", userdata);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "get user blocked", nickname = "getUserBlocked", notes = "Get User Blocked")
	@GetMapping("/getBlockedeUser")
	public ResponseEntity<Map<String, Object>> getBlockedUser(@RequestParam(value="pageNo", required=false, defaultValue = "0") String pageNo,@RequestParam(value = "userid") Long userid)
	{
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			List<User> userdata = userservice.blockedUserList(userid);
			if (Optional.ofNullable(userdata).isPresent()) {
				result.put("success", true);
				result.put("result", userdata);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	

	@ApiOperation(value = "get user status", nickname = "saveUSerStatus", notes = "Save User Status")
	@GetMapping("/saveUserStatus")
	public ResponseEntity<Map<String, Object>> SaveUserStatus(@RequestParam(value="status", required=true) Integer status,@RequestParam(value = "userid") Long userid)
	{
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);

		try {
			User userdata = userservice.saveUserStatus(userid,Integer.toString(status));
			
				if(userdata!=null)
				result.put("success", true);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}	
	
	@ApiOperation(value = "update user status", nickname = "saveUSerStatus", notes = "Save User Status")
	@PutMapping("/updateUserOnlineStatus")
	public ResponseEntity<Map<String, Object>> updateUserOnlineStatus(@RequestParam(required=true) Long userId,@RequestParam(required=true) String status)
	{
		Map<String, Object> result = new HashMap<>();
		try {
			User userdata = userservice.updateUserOnlineStatus(userId,status);
			
				if(userdata!=null) {
					result.put("success", true);
					result.put("result", userdata);
				}
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}	
	
	@ApiOperation(value = "update user status", nickname = "saveUSerStatus", notes = "Save User Status")
	@GetMapping("/getUserOnlineStatus")
	public ResponseEntity<Map<String, Object>> getUserOnlineStatus(@RequestParam(required=true) Long userId)
	{
		Map<String, Object> result = new HashMap<>();
		try {
			User userdata = userservice.getUser(userId);
			
				if(userdata!=null) {
					result.put("success", true);
					result.put("result", userdata.getCustomloginstatus());
				}
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
