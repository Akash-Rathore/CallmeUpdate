package com.me.callme.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.me.callme.model.RedeemResponse;
import com.me.callme.model.User;



public interface UserService {
	
	public User persistuser(User userjson);
	public User edituser(User userjson);
	public User getUser(Long id,String mobileno,String username);
	public Set<User> getUserOnline(String pageNo,Long userId);
	public User saveUserStatus(Long id,String status);
	public User logoutuser(Long id,Boolean flag);
	public User approveimage(Long id,Boolean flag);
	public boolean blockedUser(Long blockedby,Long blockedto,Boolean flag);
	public boolean blockedUserStatus(Long blockedby,Long blockedto);
	public List<User> blockedUserList(Long blockedby);

	public boolean favourUser(Long favby,Long favto,Boolean flag);
	public User fcmtoken(Long userid,String rcmtoken);
	public boolean imagestore(Long userId,MultipartFile imagefile);
	public User updateUserOnlineStatus(Long userId, String status);
	public User getUser(Long id);
	public List<User> getAllUser();
	public void deleteUser(int delete);
	public Optional<User> getSingleUser(long singleRecord);
	public int UpdateUser(User user);
	public int userApprovalStatus(long userId , String pending_img , String status);
	public List<User> BlackUser(long userId);
	public List<User> FavouritUser(long userId);
	public Long countUser();
	public List<RedeemResponse> redeemAll();
	public List<User> genderCategory(String gender);//getsubadmin
	public List<User> getsubadmin();
	public void addsubadmin(User user);
	public void deletesubadmin(int delete);
	public List<User> subAdminLogin(String email,String password);
	public int adminUpdateUser(User user);
}
