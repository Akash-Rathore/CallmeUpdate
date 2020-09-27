package com.me.callme.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.me.callme.model.Notification;
import com.me.callme.model.Product;
import com.me.callme.model.RedeemResponse;
import com.me.callme.model.User;
import com.me.callme.service.NotificationService;
import com.me.callme.service.ProductService;
import com.me.callme.service.RedeemService;
import com.me.callme.service.UserService;


@Controller
public class WebController {

	@Autowired
	private RedeemService redeemService;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private ProductService productservice;

	@Autowired
	private NotificationService notificationService;
	

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	

	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}

	
	@PostMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException, JSONException{
		
		  String  email=request.getParameter("email");
		  String  password=request.getParameter("password");
		  PrintWriter out=response.getWriter();
		  JSONObject json=new JSONObject();
		  if(email.equalsIgnoreCase("tiger@gmail.com") && password.equalsIgnoreCase("tiger"))
		  {
			    session.setAttribute("email" , email);
				json.put("status" , true);
			    out.println(json);
		  }else {
			  
				json.put("status" , false);
			    out.println(json);
		  }

	}


	
	@GetMapping("/dashboard")
	public ModelAndView dashboard() {

		  ModelAndView modelAndView = new ModelAndView("dashboard");
		  modelAndView.addObject("usercount", userservice.countUser());
		  return modelAndView;
	}

	@GetMapping("/category-list")
	public String category() {
		return "category-list";

	}
	
	
	@GetMapping("/subadmin")
	public ModelAndView subadmin() {
		List<User> allsubadmin = userservice.getsubadmin();
		ModelAndView modelAndView = new ModelAndView("subadmin");
		modelAndView.addObject("allsubdmin", allsubadmin);
		return modelAndView;
	}
	
	@GetMapping("/addsubadmin")
	public String addsubadmin() {
		return "addsubadmin";
	}

	@GetMapping("/new-category")
	public String newcategory() {
		return "new-category";
	}

	@GetMapping("/notification")
	public   ModelAndView notification() {
	  
	  List<Notification> allnotification=notificationService.getAllNotification();
	  ModelAndView modelAndView = new ModelAndView("notification");
	  modelAndView.addObject("allnotification",allnotification);
	  return modelAndView;
	
	}


	
	@GetMapping("/edit-user/{UserId}")
	public ModelAndView editUser(@PathVariable(value = "UserId") long userid , HttpServletRequest request , HttpServletResponse response) {
	     
		  Optional<User> alluser = userservice.getSingleUser(userid);
		  List<User> arraylist = new ArrayList<>();
		  if(alluser.isPresent()) {
			  arraylist.add(alluser.get());
		  }
		 
		 
		  ModelAndView modelAndView = new ModelAndView("edit-user");
		  modelAndView.addObject("singleUser",arraylist);
		  return modelAndView;
	}
   
	
	
	@GetMapping("/user-list")
	public ModelAndView userList() {
		List<User> alluser = userservice.getAllUser();
		ModelAndView modelAndView = new ModelAndView("user-list");
		modelAndView.addObject("alluser" ,  alluser);
	    return modelAndView;
	}

	
	  
	  @PostMapping("/allUser") 
	  public void AllUser(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
	 
	   PrintWriter out=response.getWriter(); 
	   List<User> alluser = userservice.getAllUser();
	   UserJsonObject<User> userJsonObject=new UserJsonObject<User>(); 
	   userJsonObject.setiTotalDisplayRecords(alluser.size());
	   userJsonObject.setiTotalRecords(alluser.size());
	   userJsonObject.setAaData(alluser);
       Gson gson = new GsonBuilder().setPrettyPrinting().create();
  	   String json2 = gson.toJson(userJsonObject);
  	   out.print(json2);
	  }
	  
	  
	  @PostMapping("/allUserDelete") 
	  public void AllUserDelete(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
	 
		   PrintWriter out=response.getWriter();
		   int delete=Integer.parseInt(request.getParameter("userId"));
		   userservice.deleteUser(delete);
		   Gson gson=new Gson();
		   JSONObject json=new JSONObject();
		   String message;
		   json.put("status",true);
	 	  //message=gson.toJson(json);
	       out.println(json);
	 
	 } 
	  
	 
	  @PostMapping("/imgPending") 
	  public void userImageStatus(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
	      
		    PrintWriter out=response.getWriter();
		    int userId=Integer.parseInt(request.getParameter("userId"));
		    String approvalstatus=request.getParameter("status");
		    String pendingImageurl=request.getParameter("url");
		    int condition=0;
		    if(approvalstatus.equalsIgnoreCase("reject")) {
		     condition=userservice.userApprovalStatus(userId ,"" ,approvalstatus);
		    }else if(approvalstatus.equalsIgnoreCase("accept")){
		     condition=userservice.userApprovalStatus(userId , pendingImageurl , approvalstatus);
		    }
		   JSONObject json=new JSONObject();
		   if(condition>0) {
			   json.put("imagestatus" , approvalstatus);
			   json.put("status" , true);
		 	   out.println(json);
		   }else {
			   json.put("status" , false);
		 	   out.println(json);
		   }
		  
	 } 
	 
	  @PostMapping("/updateuser") 
	  public void UpdateUser(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
	 
		    PrintWriter out=response.getWriter();
		    int userId=Integer.parseInt(request.getParameter("userId"));
		    String username=request.getParameter("username");
		    String gender=request.getParameter("gender");
		    String city=request.getParameter("city");
		    String mobile=request.getParameter("mobile");
		    String password=request.getParameter("password");
		    
		    String notifi_rights="";
			String pic_approval_rights="";
			String redem_rights="";
			 
			 if(request.getParameter("notifi_rights")==null)
			 {
				 notifi_rights="off";
				 	 
			 }else {
				 
				 notifi_rights=request.getParameter("notifi_rights");
			 }
			 
			 if(request.getParameter("pic_approval_rights")==null)
			 {
				 pic_approval_rights="off";
				 	 
			 }else {
				 
				 pic_approval_rights=request.getParameter("pic_approval_rights");
			 }
			 
			 if(request.getParameter("redem_rights")==null)
			 {
				 redem_rights="off";
				 	 
			 }else {
				 
				 redem_rights=request.getParameter("redem_rights");
			 }
		  
			//	System.out.println(notifi_rights+pic_approval_rights+redem_rights+"testing purpose only");
		  
			
		    User user=new User(userId,username,gender,city,mobile,password,notifi_rights,pic_approval_rights,redem_rights);
		    int condition=userservice.adminUpdateUser(user);
		    JSONObject json=new JSONObject();
	
		    if(condition>0)
		    {
			    json.put("status" , true);
		 	    out.println(json);
		 	    	
		    }else {
		    	
		    	json.put("status" , false);
		 	    out.println(json);
		 	    
		    }
		    
	
	 }  
	  
	  
	  
	  @GetMapping("/packages-list") 
	  public ModelAndView allProduct(){
	 	    
		    List<Product> allproduct = productservice.getAllProduct();
			ModelAndView modelAndView = new ModelAndView("packages-list");
			modelAndView.addObject("allProduct"  ,  allproduct);
		    return modelAndView;
	  
	  }
	  
	  @GetMapping("/profile") 
	  public ModelAndView profile(HttpSession session){
	 	 
		  Optional<User> alluser = userservice.getSingleUser(1152);
		  List<User> arraylist = new ArrayList<>();
		  if(alluser.isPresent()) {
			  arraylist.add(alluser.get());
		  }
		  ModelAndView modelAndView = new ModelAndView("profile");
		  session.setAttribute("adminName" , alluser.get().getUsername());
		  //modelAndView.addObject("adminName", alluser.get().getUsername());
		  modelAndView.addObject("profileRecord",arraylist);
		  return modelAndView;
		 
	  }
	  
	  
		@GetMapping("/new-package")
		public String newPackage() {
		    return "new-package";
		}
      
		@PostMapping("/addPackage")
		public void addPackage(HttpServletRequest request , HttpServletResponse response){
			
			 Integer amount=Integer.parseInt(request.getParameter("amount"));
			 Integer country=Integer.parseInt(request.getParameter("country"));
			 Integer min=Integer.parseInt(request.getParameter("min"));
			 Integer sms=Integer.parseInt(request.getParameter("sms"));
			 Integer status=Integer.parseInt(request.getParameter("status"));
			 String unit=request.getParameter("unit");
			 Product product=new Product(amount,country,min,sms,status,unit);
			 try {
			 
				 PrintWriter out=response.getWriter();
				 JSONObject json=new JSONObject();
				 productservice.newPackage(product);
				 json.put("status" , true);
			     out.println(json);
			 
			 }
			 catch(Exception exception) {
				 
				 PrintWriter out;
				try {
					
					 out = response.getWriter();
					 JSONObject json=new JSONObject();
					 json.put("status" , false);
					 out.println(json);
					 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
			 }
			    
			 
		}

		
		  @GetMapping("/edit-package/{package_id}") 
		  public ModelAndView editPackage(@PathVariable(value = "package_id") long package_id ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		 	  
			  Optional<Product> singlePackage = productservice.getSinglePackage(package_id);
			  List<Product> arraylist = new ArrayList<>();
			  if(singlePackage.isPresent()) {
				  arraylist.add(singlePackage.get());
			  }
			  ModelAndView modelAndView = new ModelAndView("edit-package");
			  modelAndView.addObject("singlePackage",arraylist);
			  return modelAndView;
		  }
	   

		  @PostMapping("/updatePackage") 
		  public void UpdatePackage(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
				
		  
		    PrintWriter out=response.getWriter();
		    Integer package_id=Integer.parseInt(request.getParameter("package_id"));
		    Integer country=Integer.parseInt(request.getParameter("country"));
		    Integer amount=Integer.parseInt(request.getParameter("amount"));
		    String  unit=request.getParameter("unit");
		    Integer status=Integer.parseInt(request.getParameter("status"));
		    Integer min=Integer.parseInt(request.getParameter("min"));
		    Integer sms=Integer.parseInt(request.getParameter("sms"));
		    Product product=new Product(amount,country,min,sms,status,unit,package_id);
		    int condition=productservice.getUpdatePackage(product);
		    JSONObject json=new JSONObject();
			if(condition>0)
		    {
			    json.put("status" , true);
		 	    out.println(json);
		 	    	
		    }else {
		    	
		    	json.put("status" , false);
		 	    out.println(json);
		 	    
		    } 
		
		 }		
		  
		 @GetMapping("/block-user/{userId}") 
		 public ModelAndView blockUser(@PathVariable(value = "userId") long userId)
		 {
			  List<User> blockuser=userservice.BlackUser(userId);
			  ModelAndView modelAndView = new ModelAndView("block-user");
			  modelAndView.addObject("blockuser" , blockuser);
			  return modelAndView;
		 }
		 
		 @GetMapping("/favourit-user/{userId}") 
		 public ModelAndView favourtUser(@PathVariable(value = "userId") long userId)
		 {
			  List<User> favourit=userservice.FavouritUser(userId);
			  ModelAndView modelAndView = new ModelAndView("favourit-user");
			  modelAndView.addObject("favourit" , favourit);
			  return modelAndView;
		 }
		 
		 
		  @GetMapping("/redeem") 
		  public ModelAndView allredem(){
		 	    
			    List<RedeemResponse> allredem = userservice.redeemAll();
				ModelAndView modelAndView = new ModelAndView("redeem");
				modelAndView.addObject("allredeem"  ,  allredem);
			    return modelAndView;
		  
		  }
		  
		  
		  
		  @PostMapping("/payment_status") 
		  public void paymentStatus(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		      
			    PrintWriter out=response.getWriter();
			 //   int userId=Integer.parseInt(request.getParameter("userId"));
			   // String approvalstatus=request.getParameter("status");
			     JSONObject json=new JSONObject();
			     if(true) {
				   json.put("status" , true);
			 	   out.println(json);
			     }
			  
		 } 
		  
		  
		  @PostMapping("/addNewCategory") 
		  public void addNewCategory(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
				
			List<User> alluser = userservice.getAllUser();
		    
			PrintWriter out=response.getWriter();
		    JSONObject json=new JSONObject();
			if(true)
		    {
			    json.put("status" , true);
		 	    out.println(json);
		 	    	
		    }
		
		 }		
		  
		  @PostMapping("/gender_selecter") 
		  public void genderSelector(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
				
			   PrintWriter out=response.getWriter(); 
			   List<User> alluser = userservice.getAllUser();
			   
			   UserJsonObject<User> userJsonObject=new UserJsonObject<User>(); 
			   userJsonObject.setiTotalDisplayRecords(alluser.size());
			   userJsonObject.setiTotalRecords(alluser.size());
			   userJsonObject.setAaData(alluser);
		       Gson gson = new GsonBuilder().setPrettyPrinting().create();
		  	   String json2 = gson.toJson(userJsonObject);
		  	   out.print(json2);  
			
		 }	
		  
		  
		  @PostMapping("/redeemDateSelector") 
		  public void redeemDateSelector(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
			
				
				 String startdate=request.getParameter("startdate"); 
				 String enddate=request.getParameter("enddate"); 
				 PrintWriter out=response.getWriter(); 
				 List<RedeemResponse> redeemdate=redeemService.redeemdate(startdate,enddate); 
				 FilterDateJsonObject filterDateJsonObject=new FilterDateJsonObject();
				  filterDateJsonObject.setiTotalDisplayRecords(redeemdate.size());
				  filterDateJsonObject.setiTotalRecords(redeemdate.size());
				  filterDateJsonObject.setAaData(redeemdate);
				  Gson gson = new GsonBuilder().setPrettyPrinting().create();
				  String json2 = gson.toJson(filterDateJsonObject); 
				  out.print(json2);
				 
	     	 
		  }		
		  
		  @PostMapping("/addsubadmin")
			public void subadmin(HttpServletRequest request , HttpServletResponse response) throws IOException, JSONException{
				
				 String name=request.getParameter("name");
				 String role=request.getParameter("role");
				 String email=request.getParameter("email");
				 String mobile=request.getParameter("mobile");
				 String city=request.getParameter("city");
				 String password=request.getParameter("password");
				 String gender=request.getParameter("gender");
				 String notifi_rights="";
				 String pic_approval_rights="";
				 String redem_rights="";
				 
				 if(request.getParameter("notifi_rights")==null)
				 {
					 notifi_rights="off";
					 	 
				 }else {
					 
					 notifi_rights=request.getParameter("notifi_rights");
				 }
				 
				 if(request.getParameter("pic_approval_rights")==null)
				 {
					 pic_approval_rights="off";
					 	 
				 }else {
					 
					 pic_approval_rights=request.getParameter("pic_approval_rights");
				 }
				 
				 if(request.getParameter("redem_rights")==null)
				 {
					 redem_rights="off";
					 	 
				 }else {
					 
					 redem_rights=request.getParameter("redem_rights");
				 }
				 User user=new User(name,role,email,mobile,city,password,gender,notifi_rights,pic_approval_rights,redem_rights);
				 userservice.addsubadmin(user);
				 PrintWriter out=response.getWriter();
				 JSONObject json=new JSONObject();
			     if(true)
				 {
					    json.put("status" , true);
				 	    out.println(json);
				 }
				 
			}
		  
		  
		  @PostMapping("/deletesubadmin") 
		  public void deletesubadmin(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		 
			   PrintWriter out=response.getWriter();
			   int delete=Integer.parseInt(request.getParameter("userId"));
			   userservice.deletesubadmin(delete);
			   JSONObject json=new JSONObject();
			   json.put("status",true);
		 	   out.println(json);
		 } 
		  
		//--------------------------------------------subadmin start  -------------------------------------------
		  @GetMapping("/subadmin/")
			public String subIndex() {
				return "subadmin/index";
			}
		  
		  
		  @PostMapping("/subadmin/login")
		  public void subadminlogin(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException, JSONException{
				
				   String  email=request.getParameter("email");
				   String  password=request.getParameter("password");
				   
				   PrintWriter out=response.getWriter();
				   JSONObject json=new JSONObject();
				   List<User> subAdminLogin=userservice.subAdminLogin(email , password);
				   Iterator<User> iterator = subAdminLogin.iterator();
                   if(iterator.hasNext())  
         	       {
                	    User fetch = iterator.next();
                	    session.setAttribute("subadminEmail" , fetch.getEmail());
                	    //session.setAttribute("redem_rights_two" , fetch.getNotifi_rights());
                	    //session.setAttribute("notification_rights" , fetch.getNotifi_rights());
                	    session.setAttribute("notifi_rights" , fetch.getNotifi_rights());
                	    session.setAttribute("pic_approval_rights" , fetch.getPic_approval_rights());
                	    session.setAttribute("redem_rights" , fetch.getRedem_rights());
                	    session.setAttribute("subadminName" , fetch.getUsername());
                	    session.setAttribute("subadminUserId" , fetch.getUserId());
                	    
                	    json.put("status" , true);
					    out.println(json);
					    
                	    
         	       }else {
					  
						json.put("status" , false);
					    out.println(json);
				  }

                   
			}
		  
		  
			@GetMapping("/subadmin/dashboard")
			public ModelAndView subadminDashboard() {

				  ModelAndView modelAndView = new ModelAndView("subadmin/dashboard");
				  modelAndView.addObject("usercount", userservice.countUser());
				  return modelAndView;
			}
		  
			
			
			@GetMapping("/subadmin/user-list")
			public ModelAndView subadminUserList() {
				List<User> alluser = userservice.getAllUser();
				ModelAndView modelAndView = new ModelAndView("subadmin/user-list");
				modelAndView.addObject("alluser" ,  alluser);
			    return modelAndView;
			}
			
			
			  @PostMapping("/subadmin/imgPending") 
			  public void usubadminserImageStatus(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
			      
				  
				    PrintWriter out=response.getWriter();
				    int userId=Integer.parseInt(request.getParameter("userId"));
				    String approvalstatus=request.getParameter("status");
				    String pendingImageurl=request.getParameter("url");
				    int condition=0;
				    if(approvalstatus.equalsIgnoreCase("reject")) {
				     condition=userservice.userApprovalStatus(userId ,"" ,approvalstatus);
				    }else if(approvalstatus.equalsIgnoreCase("accept")){
				     condition=userservice.userApprovalStatus(userId , pendingImageurl , approvalstatus);
				    }
				   JSONObject json=new JSONObject();
				   if(condition>0) {
					   json.put("imagestatus" , approvalstatus);
					   json.put("status" , true);
				 	   out.println(json);
				   }else {
					   json.put("status" , false);
				 	   out.println(json);
				   }
				  
			 } 
			
		
				@GetMapping("/subadmin/notification")
				public   ModelAndView subadminNotification() {
				  
				  List<Notification> allnotification=notificationService.getAllNotification();
				  ModelAndView modelAndView = new ModelAndView("/subadmin/notification");
				  modelAndView.addObject("allnotification",allnotification);
				  return modelAndView;
				
				}

				@GetMapping("/subadmin/new-category")
				public String subadminnewcategory() {
					return "subadmin/new-category";
				}
			  
			  
				  @PostMapping("/subadmin/gender_selecter") 
				  public void subadmingenderSelector(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
						
					   PrintWriter out=response.getWriter(); 
					   List<User> alluser = userservice.getAllUser();
					   UserJsonObject<User> userJsonObject=new UserJsonObject<User>(); 
					   userJsonObject.setiTotalDisplayRecords(alluser.size());
					   userJsonObject.setiTotalRecords(alluser.size());
					   userJsonObject.setAaData(alluser);
				       Gson gson = new GsonBuilder().setPrettyPrinting().create();
				  	   String json2 = gson.toJson(userJsonObject);
				  	   out.print(json2);  
					
				 }	
				  
				  @PostMapping("/subadmin/addNewCategory") 
				  public void subadminaddNewCategory(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
					
					List<User> alluser = userservice.getAllUser();
				    PrintWriter out=response.getWriter();
				    JSONObject json=new JSONObject();
					if(true)
				    {
					    json.put("status" , true);
				 	    out.println(json);
				 	    	
				    }
				
				 }
				  
				  
				  
				  @GetMapping("/subadmin/redeem") 
				  public ModelAndView subadminallredem(){
				 	    
					    List<RedeemResponse> allredem = userservice.redeemAll();
						ModelAndView modelAndView = new ModelAndView("/subadmin/redeem");
						modelAndView.addObject("allredeem"  ,  allredem);
					    return modelAndView;
				  
				  }  
				  
				  
				  
				  @PostMapping("/subadmin/payment_status") 
				  public void subadminpaymentStatus(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
				      
					    PrintWriter out=response.getWriter();
					 //   int userId=Integer.parseInt(request.getParameter("userId"));
					   // String approvalstatus=request.getParameter("status");
					     JSONObject json=new JSONObject();
					     if(true) {
						   json.put("status" , true);
					 	   out.println(json);
					     }
					  
				 }   
				  
				  @PostMapping("/subadmin/redeemDateSelector") 
				  public void subadminredeemDateSelector(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
					
						
						 String startdate=request.getParameter("startdate"); 
						 String enddate=request.getParameter("enddate"); 
						 PrintWriter out=response.getWriter(); 
						 List<RedeemResponse> redeemdate=redeemService.redeemdate(startdate,enddate); 
						 FilterDateJsonObject filterDateJsonObject=new FilterDateJsonObject();
						  filterDateJsonObject.setiTotalDisplayRecords(redeemdate.size());
						  filterDateJsonObject.setiTotalRecords(redeemdate.size());
						  filterDateJsonObject.setAaData(redeemdate);
						  Gson gson = new GsonBuilder().setPrettyPrinting().create();
						  String json2 = gson.toJson(filterDateJsonObject); 
						  out.print(json2);
						 
			     	 
				  }		
				  
				  @GetMapping("/subadmin/logout")
				  public String Subadmin(HttpSession session){
						session.invalidate();
						return "redirect:/subadmin/";
				  } 
				  
				 
				  @GetMapping("/subadmin/profile") 
				  public ModelAndView subadminprofile(HttpSession session){
				 	 
					  long subadminUserId=(long)session.getAttribute("subadminUserId");
					  Optional<User> alluser = userservice.getSingleUser(subadminUserId);
					  List<User> arraylist = new ArrayList<>();
					  if(alluser.isPresent()) {
						  arraylist.add(alluser.get());
					  }
					  ModelAndView modelAndView = new ModelAndView("/subadmin/profile");
					  modelAndView.addObject("profileRecord",arraylist);
					  return modelAndView;
					 
				  }
				  
				  
				  @PostMapping("/subadmin/updateuser") 
				  public void sUpdateUser(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
				 
					    PrintWriter out=response.getWriter();
					    int userId=Integer.parseInt(request.getParameter("userId"));
					    String username=request.getParameter("username");
					    String gender=request.getParameter("gender");
					    String city=request.getParameter("city");
					    String mobile=request.getParameter("mobile");
					    String password=request.getParameter("password");
					  
					    User user=new User(userId,username,gender,city,mobile,password);
					    int condition=userservice.UpdateUser(user);
					    JSONObject json=new JSONObject();
				
					    if(condition>0)
					    {
						    json.put("status" , true);
					 	    out.println(json);
					 	    	
					    }else {
					    	
					    	json.put("status" , false);
					 	    out.println(json);
					 	    
					    }
				 }
		//--------------------------------------------subadmin end  -------------------------------------------
			  
		 
		 
}
