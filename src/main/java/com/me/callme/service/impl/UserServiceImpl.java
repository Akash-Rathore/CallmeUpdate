package com.me.callme.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.me.callme.model.BlockedUser;
import com.me.callme.model.BlockerResponse;
import com.me.callme.model.FavouritResponse;
import com.me.callme.model.FavouriteUser;
import com.me.callme.model.Product;
import com.me.callme.model.RedeemResponse;
import com.me.callme.model.User;
import com.me.callme.model.ps_aors;
import com.me.callme.model.ps_auths;
import com.me.callme.model.ps_endpoints;
import com.me.callme.repository.BlockedUserRepository;
import com.me.callme.repository.FavoriteUserRepository;
import com.me.callme.repository.Ps_aorsRepository;
import com.me.callme.repository.Ps_authsRepository;
import com.me.callme.repository.Ps_endpointsRepository;
import com.me.callme.repository.UserRepository;
import com.me.callme.service.UserService;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private Ps_aorsRepository ps_aorsrepository;
	@Autowired
	private Ps_authsRepository ps_authsRepository;
	@Autowired
	private Ps_endpointsRepository ps_endpointsRepository;

	@Autowired
	private BlockedUserRepository blockeduserRepository;

	@Autowired
	private FavoriteUserRepository favuserRepository;

	public enum Level {
		no, yes
	}

	public static final int PAGE_SIZE = 10;
	public long totalUsersCount;

	
	
	
	
	@Override
	public User persistuser(User userjson) {
		try {

			User ar = new User();
			if (userrepository.findByMobile(userjson.getMobile()) == null
					|| userrepository.findByUsername(userjson.getUsername()) == null) {

				ar.setAge(userjson.getAge());
				ar.setUsername(userjson.getUsername());
				ar.setCity(userjson.getCity());
				ar.setDescription(userjson.getDescription());
				ar.setLang(userjson.getLang());
				ar.setMobile(userjson.getMobile());
				ar.setRegisterdatetime(new Date());
				ar.setGender(userjson.getGender());
				ar.setLoginstatus(true);
				ar.setLastlogintime(new Date());
				ar.setLookFor(userjson.getLookFor());
				ar.setCustomloginstatus("1");
				userrepository.save(ar);

				String mUserId = Long.toString(ar.getUserId());

				ps_aors mPs_aors = new ps_aors();
				ps_auths mPs_auths = new ps_auths();
				ps_endpoints mPs_endpoints = new ps_endpoints();
				mPs_aors.setId((int) (long) (ar.getUserId()));
				mPs_aors.setMax_contacts(1);
				mPs_aors.setRemove_existing("yes");
				mPs_aors.setQualify_frequency(5);
				mPs_aors.setQualify_timeout(5f);

				mPs_auths.setId((int) (long) (ar.getUserId()));
				mPs_auths.setAuth_type("userpass");
				mPs_auths.setPASSWORD(mUserId);
				mPs_auths.setUsername(mUserId);

				mPs_endpoints.setId((int) (long) (ar.getUserId()));
				mPs_endpoints.setAors(mUserId);
				mPs_endpoints.setAuth(mUserId);
				mPs_endpoints.setContext("testing1");
				mPs_endpoints.setDisallow("all");
				mPs_endpoints.setAllow("gsm,g729");
				mPs_endpoints.setDirect_media("no");
				mPs_endpoints.setRtp_symmetric("yes");
				mPs_endpoints.setMessage_contex("message");
				mPs_endpoints.setTransport("transport-udp");
				mPs_endpoints.setExternal_media_address("13.232.162.128");
				mPs_endpoints.setForce_rport("yes");
				mPs_endpoints.setIce_support("yes");
				mPs_endpoints.setRewrite_contact("yes");
				mPs_endpoints.setUse_avpf("yes");
				mPs_endpoints.setMedia_encryption("NO");
				mPs_endpoints.setMedia_address("13.232.162.128");
				mPs_endpoints.setForce_avp("yes");
				mPs_endpoints.setMedia_use_received_transport("NO");
				mPs_endpoints.setRctp_mux("yes");

				ps_aorsrepository.save(mPs_aors);
				ps_authsRepository.save(mPs_auths);
				ps_endpointsRepository.save(mPs_endpoints);

			} else {
				ar = userrepository.findByMobile(userjson.getMobile());
				if (ar == null) {
					ar = userrepository.findByUsername(userjson.getUsername());
				}
			}
			return ar;
		} catch (Exception e) {
			// log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public User edituser(User userjson) {
		try {
			Optional<User> areaRes = userrepository.findById(userjson.getUserId());
			User dbobject = areaRes.get();

			dbobject.setAge(userjson.getAge());
			dbobject.setCity(userjson.getCity());
			dbobject.setDescription(userjson.getDescription());
			dbobject.setGender(userjson.getGender());
			dbobject.setLang(userjson.getLang());
			dbobject.setMobile(userjson.getMobile());
			dbobject.setLastupdatetime(new Date());
			dbobject.setUsername(userjson.getUsername());
			dbobject.setLookFor(userjson.getLookFor());
			userrepository.save(dbobject);
			return dbobject;
		} catch (Exception e) {
			// log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean imagestore(Long userId, MultipartFile imagefile) {
		try {
			try {

				// Get the file and save it somewhere
				
				String pathfile = "/home/centos/images/" + userId + "/";
				File makedir = new File(pathfile);
				makedir.mkdir();

				String[] files = makedir.list();

				for (String file : files)
				{
					File n=new File(pathfile+file);
					n.delete();
					System.out.println("File Name---------"+file);
				}
				byte[] bytes = imagefile.getBytes();
				Path path = Paths.get(pathfile + imagefile.getOriginalFilename());
				Files.write(path, bytes);
				Optional<User> areaRes = userrepository.findById(userId);
				User dbobject = areaRes.get();

				dbobject.setPendingImg(path.toString());
				userrepository.save(dbobject);

			} catch (IOException e) {
				// System.out.print("e:"+e.printStackTrace());
				System.out.print("e at inner try:" + e);
				e.printStackTrace();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("e at outer try:" + e);
			// log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public User logoutuser(Long logoutid, Boolean flag) {
		try {
			Optional<User> areaRes = userrepository.findById(logoutid);
			User dbobject = areaRes.get();
			if (flag == false) {
				dbobject.setLoginstatus(false);
			} else {
				dbobject.setLoginstatus(true);
			}
			dbobject.setLastlogouttime(new Date());
			userrepository.save(dbobject);
			return dbobject;
		} catch (Exception e) {
			// log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public User approveimage(Long userid, Boolean flag) {
		try {
			Optional<User> areaRes = userrepository.findById(userid);
			User dbobject = areaRes.get();
			String path = dbobject.getPendingImg();
			if (flag == false) {

				File f = new File(path);
				if (f.exists()) // returns Boolean value
				{
					if (f.delete()) {
						System.out.println(f.getName() + " deleted");
					} // getting and printing the file name
				}

				dbobject.setApprovedImg(null);
				dbobject.setPendingImg(null);
			} else {
				dbobject.setApprovedImg(path);
				dbobject.setPendingImg(null);
			}
			dbobject.setLastlogouttime(new Date());
			userrepository.save(dbobject);
			return dbobject;
		} catch (Exception e) {
			// log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public User fcmtoken(Long userid, String rcmtoken) {
		try {
			Optional<User> areaRes = userrepository.findById(userid);
			User dbobject = areaRes.get();
			dbobject.setFCMtoken(rcmtoken);
			userrepository.save(dbobject);
			return dbobject;
		} catch (Exception e) {
			// log.error(e.getMessage());
			throw e;
		}
	}

	
	
	
	@Override
	public boolean blockedUserStatus(Long blockedby,Long blockedto)
	{
		
		try {
		   BlockedUser blockuser = blockeduserRepository.findByIdblockedto(blockedby, blockedto.toString());
		if(blockuser!=null)
           return true;
		} catch (Exception e) {
			throw e;
		}
		
		
		
		return false;
	}

	
	@Override
	public User getUser(Long id, String mobileno, String username) {
		try {
			User dbobject = null;
			if (id != null) {
				Optional<User> areaRes = userrepository.findById(id);
				dbobject = areaRes.get();
			} else if (mobileno != null) {
				User userbymobileno = userrepository.findByMobile(mobileno);
				dbobject = userbymobileno;
			} else {
				User userbyusername = userrepository.findByUsername(username);
				dbobject = userbyusername;
			}
			return dbobject;
		} catch (Exception e) {
			// log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public User saveUserStatus(Long id,String status)
	{
	User user=	userrepository.findById(id).get();
	user.setCustomloginstatus(status);
	userrepository.save(user);
		return user;
		
	}
	
	
	public PageRequest gotoPage(int page) {
		PageRequest request = PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "userId");
		return request;
	}

	@Override
	public Set<User> getUserOnline(String pageNo, Long userId) {
		try {
			
			System.out.println("getUserOnline User Id: "+userId);
			
			int lastPageNo;
			int gotoPageNo = Integer.parseInt(pageNo);
			Set<User> filterallUsers = new LinkedHashSet<User>();
			// List<User> activeonline = userrepository.findByAccountstatusLoginstatus();
//			Set<User> allUsers = new LinkedHashSet<User>();
//
//			// session.setAttribute("currentPageNo", 0);
//
//			for (User u : userrepository.findAll(gotoPage(gotoPageNo))) // fetches rows from DB as per Page No
//			{
//				// if(userrepository.findByAccountstatusLoginstatusUserId(u.getUserId())!=null)
//				allUsers.add(u);
//			}
//			totalUsersCount = userrepository.count(); // total no of users
//			if (totalUsersCount % PAGE_SIZE != 0)
//				lastPageNo = (int) (totalUsersCount / PAGE_SIZE) + 1; // get last page No (zero based)
//			else
//				lastPageNo = (int) (totalUsersCount / PAGE_SIZE);

			for (User u : userrepository.findAll(gotoPage(gotoPageNo))) {
				/*
				 * if (u.getLoginstatus() == true) { filterallUsers.add(u); }
				 */
			
				System.out.println("User Id: "+u.getUserId());
				
				
				if (!u.getUserId().equals(userId) ) {
					filterallUsers.add(u);
				}
			}

			return filterallUsers;
		} catch (Exception e) {
			// log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean blockedUser(Long blockedby, Long blockedto, Boolean flag) {
		try {
			System.out.println("get data1");
			BlockedUser blockuser = blockeduserRepository.findByIdblockedto(blockedby, blockedto.toString());
			System.out.println("get data2");
			if (flag == true) {
				if (!Optional.ofNullable(blockuser).isPresent()) {
					BlockedUser blockusersave = new BlockedUser();
					blockusersave.setBlockedby(blockedby);
					blockusersave.setBlockeduser(blockedto.toString());
					blockeduserRepository.save(blockusersave);
				}
			} else {
				if (Optional.ofNullable(blockuser).isPresent()) {
					blockeduserRepository.deleteById(blockuser.getId());
				}
			}

			return true;
		} catch (Exception e) {
			System.out.println("e-----------------------------------:" + e);
			// log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<User> blockedUserList(Long blockedby) {
		// TODO Auto-generated method stub
	
	
		List<User> userList=new ArrayList<User>();
		
		 List<BlockedUser>  blockedUserList=blockeduserRepository.getAllBlockedUserById(blockedby);
		
		 
		 for (BlockedUser blockedUser:blockedUserList)
		 {
			User mUser= getUser(Long.parseLong(  blockedUser.getBlockeduser()),null,null);
			userList.add(mUser);
		 }
		 return userList;
		
		
	}
	
	

	@Override
	public boolean favourUser(Long favby, Long favuser, Boolean flag) {
		try {
			System.out.println("get data1");
			FavouriteUser favrtuser = favuserRepository.findByIdfavouriteto(favby, favuser.toString());
			System.out.println("get data2");
			if (flag == true) {
				if (!Optional.ofNullable(favrtuser).isPresent()) {
					FavouriteUser favyuuser = new FavouriteUser();
					favyuuser.setFavouriteby(favby);
					favyuuser.setFavouriteuser(favuser.toString());
					favuserRepository.save(favyuuser);
				}
			} else {
				if (Optional.ofNullable(favrtuser).isPresent()) {
					favuserRepository.deleteById(favrtuser.getId());
				}
			}

			return true;
		} catch (Exception e) {
			System.out.println("e-----------------------------------:" + e);
			// log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public User updateUserOnlineStatus(Long userId, String status) {
		Optional<User> areaRes = userrepository.findById(userId);
		User user = areaRes.get();
		user.setCustomloginstatus(status);
		userrepository.save(user);
		return user;
	}

	@Override
	public User getUser(Long id) {
		Optional<User> areaRes = userrepository.findById(id);
		User user = areaRes.get();
		return user;
	}
	
	public List<User> getAllUser() {
		List<User> areaRes = userrepository.findAll();
		return areaRes;
	}

	@Override
	public void deleteUser(int delete) {
	    userrepository.deleteById((long) delete);
	}

	@Override
	public Optional<User> getSingleUser(long singleRecord) {
		Optional<User> areaRes = userrepository.findById(singleRecord);
	   return areaRes;
	}

	@Override
	public int UpdateUser(User user) {
		  
		   int updateuser=userrepository.updateUser(user.getUserId() ,user.getUsername(), user.getGender() , user.getCity() , user.getMobile() , user.getPassword());
		   return updateuser;
	 }

	@Override 
	public int userApprovalStatus(long userId, String pending_img,String status) {
		
		int change_status=1;
		
		if(status.equalsIgnoreCase("reject"))
		{
			
			return userrepository.rejectApprovalStatus(userId , pending_img);
			
	        		
		}else if(status.equalsIgnoreCase("accept")) {
		
			return userrepository.acceptApprovalStatus(userId , pending_img,change_status);
			
		}
		
		return 0;
	}

	
	@Override
	public List<User> BlackUser(long userId) {
		
		List<BlockerResponse> blockuser=userrepository.blockedUser(userId);
		Iterator<BlockerResponse> iterator = blockuser.iterator();
		List<User> userlist = new ArrayList<>();
		while(iterator.hasNext()) {
			
			BlockerResponse blockerResponse = iterator.next();
			long id = Long.parseLong(blockerResponse.getBlocked_user());
		    Optional<User> blockUser=userrepository.findById(id);   
		    if(blockUser.isPresent()) {
		    	  userlist.add(blockUser.get());
			}
			
		}
		
		return userlist;
	}
	
	

	@Override
	public List<User> FavouritUser(long userId) {
		
		List<FavouritResponse> favourituser=userrepository.favouritUser(userId);
		Iterator<FavouritResponse> iterator = favourituser.iterator();
		List<User> userlist = new ArrayList<>();
		while(iterator.hasNext()) {
			FavouritResponse favouritResponse = iterator.next();          
			long id = Long.parseLong(favouritResponse.getFavouriteuser());
			Optional<User> favouritUser=userrepository.findById(id);   
		      if(favouritUser.isPresent()) {
		    	  userlist.add(favouritUser.get());
			  }
			
		}
		
		return userlist;
	}

	@Override
	public Long countUser() {
		
		
		return userrepository.countUser();
	}

	
	@Override
	public List<RedeemResponse> redeemAll() {
		List<RedeemResponse> redemresponse=userrepository.redeemAll();
		return redemresponse;
	}

	@Override
	public List<User> genderCategory(String gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getsubadmin() {
		List<User> areaRes = userrepository.getsubadmin();
		return areaRes;
	}

	@Override
	public void addsubadmin(User user) {
		userrepository.save(user);
	}

	@Override
	public void deletesubadmin(int delete) {
         userrepository.deleteById((long) delete);
	}

	@Override
	public List<User> subAdminLogin(String email, String password) {
		return userrepository.subAdminLogin(email,password);
	}

	@Override
	public int adminUpdateUser(User user) {
	   
		 int updateuser=userrepository.adminUpdateUser(user.getUserId() ,user.getUsername(), user.getGender() , user.getCity() , user.getMobile() , user.getPassword(),user.getNotifi_rights(),user.getPic_approval_rights(),user.getRedem_rights());
		 return updateuser;
		
	}


	

}
