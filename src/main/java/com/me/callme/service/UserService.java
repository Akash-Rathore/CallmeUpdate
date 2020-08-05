package com.me.callme.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

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
}
