package com.me.callme.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.callme.model.BlockerResponse;
import com.me.callme.model.FavouritResponse;
import com.me.callme.model.RedeemResponse;
import com.me.callme.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query(value="from User m where m.mobile = :mobile")
	User findByMobile(@Param("mobile") String mobile);
	
	@Query(value="from User m where m.username = :username")
	User findByUsername(@Param("username") String username);
	
	//@Query("from User m where m.accountstatus =true and m.loginstatus=true and m.userId=userId")
	//List<User> findByAccountstatusLoginstatusUserId(Long userId);
	
	@Query(value="from User m where m.accountstatus =true and m.loginstatus=true and m.userId=userId")
	User findByAccountstatusLoginstatusUserId(Long userId);
	
	//@Query("from User m where m.accountstatus =true and loginstatus=true")
	public Iterable<User> findAll(Pageable page);
	
	@Query(value="select *  from users where approved_status='0' and role is null" , nativeQuery=true)
	public List<User> findAll();
	
	@Transactional
    @Modifying(clearAutomatically=true)
    @Query(value="UPDATE User m set m.username=:username , m.gender=:gender , m.city=:city , m.mobile=:mobile , m.password=:password  WHERE m.userId=:userId")
	public int updateUser(@Param("userId")long userId , @Param("username")String username , @Param("gender")String gender, @Param("city")String city , @Param("mobile")String mobile  , @Param("password")String password);

	

	@Transactional
	@Modifying
	@Query(value="UPDATE User m set m.PendingImg=:pending_img WHERE m.userId=:userId")
    public int rejectApprovalStatus(@Param("userId")long userId , @Param("pending_img") String pending_img);
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE User m set m.ApprovedImg=:accept_img , approved_status =:change_status WHERE m.userId=:userId")
    public int acceptApprovalStatus(@Param("userId")long userId , @Param("accept_img") String accept_img , @Param("change_status")int change_status);
	
	
	
	@Query(value="SELECT  new com.me.callme.model.BlockerResponse(b.blockeduser) FROM User m JOIN m.blockedUser b where b.blockedby=:userId")
	public  List<BlockerResponse> blockedUser(@Param("userId")long userId);

	@Query(value="SELECT  new com.me.callme.model.FavouritResponse(f.favouriteuser) FROM User m JOIN m.favouriteUser f where f.favouriteby=:userId")
	public  List<FavouritResponse> favouritUser(@Param("userId")long userId);

	@Query(value = "SELECT count(user_id) FROM users where role is null" , nativeQuery=true)
	public Long countUser();

	
	@Query(value="SELECT  new com.me.callme.model.RedeemResponse(m.userId , m.username , r.mobile , r.amount , r.payment_type) FROM User m JOIN m.redeem r GROUP BY m.username")
	public  List<RedeemResponse> redeemAll();

	@Query(value="select *  from users where role='subadmin'" , nativeQuery=true)
	public List<User> getsubadmin();
	
	
	@Query(value="select *  from users where email = ?1 and password= ?2" , nativeQuery=true)
	public List<User> subAdminLogin(String email , String password);
	
	
	@Transactional
    @Modifying(clearAutomatically=true)
    @Query(value="UPDATE User m set m.username=:username , m.gender=:gender , m.city=:city , m.mobile=:mobile , m.password=:password , m.notifi_rights=:notifi_rights , m.pic_approval_rights=:pic_approval_rights , m.redem_rights=:redem_rights  WHERE m.userId=:userId")
	public int adminUpdateUser(@Param("userId")long userId , @Param("username")String username , @Param("gender")String gender, @Param("city")String city , @Param("mobile")String mobile  , @Param("password")String password, @Param("notifi_rights")String notifi_rights , @Param("pic_approval_rights")String pic_approval_rights  , @Param("redem_rights")String redem_rights);

	
	//@Query("from User m where m.accountstatus =true and loginstatus=true")
	//public Iterable<User> findAllByAccountstatusLoginstatus(Boolean status,Boolean status1,Pageable page);
}
