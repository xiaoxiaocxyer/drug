package cn.tedu.drug.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.drug.service.exception.UserNotFoundException;
import cn.tedu.drug.service.exception.YearMonthException;
import cn.tedu.drug.dao.CustomerDao;
import cn.tedu.drug.entity.Customer;
import cn.tedu.drug.entity.CustomerTime;
import cn.tedu.drug.entity.domain.PaginationVO;
import cn.tedu.drug.service.ICustomerService;
import cn.tedu.drug.service.ILogOperationService;
import cn.tedu.drug.service.exception.DeleteException;
import cn.tedu.drug.service.exception.EmailDuplicateException;
import cn.tedu.drug.service.exception.ForeignKeyReferenceException;
import cn.tedu.drug.service.exception.InsertException;
import cn.tedu.drug.service.exception.PasswordNotMatchException;
import cn.tedu.drug.service.exception.PhoneDuplicateException;
import cn.tedu.drug.service.exception.PhoneNotFoundException;
import cn.tedu.drug.service.exception.UpdateException;
/**
 * 客户，业务层，实现类
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired private CustomerDao customerdao;
	
	ILogOperationService logOPerationServiceImpl = new LogOperationServiceImpl();
	
	/**
	 * 	客户简单注册
	 * @param employees
	 * @return 返回注册成功与否信息
	 * @throws EmailDuplicateException 邮箱被占用时的异常
	 * @throws PhoneDuplicateException 电话被占用时的异常
	 * @throws InsertException	插入数据失败时的异常
	 */
	public void reg(Customer customer) throws EmailDuplicateException,PhoneDuplicateException,UserNotFoundException,InsertException{
		
		String username = customer.getUsername();
		String phone = customer.getPhone();
		String email = customer.getEmail();
		Customer resultPhone = findByPhone(phone);
		if( resultPhone != null ) {
			throw new PhoneDuplicateException("您尝试的电话号码（"+phone+"）已经被占用");
		}
		Customer resultEmail = findByEmail(email);
		if( resultEmail != null ) {
			throw new EmailDuplicateException("您尝试的邮箱（"+email+"）已经被占用");
		}
		
		//设置is_delete
		customer.setIsDelete(0);
		//设置四项日志
		if( customer != null ) {	//杜绝Null字符串异常
			Date time = new Date();
			customer.setCreatedUser(username);
			customer.setCreatedTime(time);
			customer.setModifiedUser(username);
			customer.setModifiedTime(time);
		}
		//密码加密---(md5摘要算法加密)---生成随机盐值，并加密密码
		String salt = UUID.randomUUID().toString().toUpperCase();
		String password = customer.getPassword();
		String getMd5Password = getMd5Password(password,salt);
		customer.setPassword(getMd5Password);
		customer.setSalt(salt);
		//没有被占用，可以注册
		insertCustomer(customer);
	};
	
	/**
	 * 根据手机号进行登录判断
	 * @param phone
	 * @return 返回（uid,username,password,salt,isDelete,phone,avatar）
	 * @throws PhoneDuplicateException
	 * @throws PasswordNotMatchException
	 */
	public Customer getloginCustomer(String phone,String password) throws PhoneNotFoundException,PasswordNotMatchException{
		Customer resultPhone = loginCustomer(phone);
		if( resultPhone == null ) {
			throw new PhoneNotFoundException("登录失败，手机号（"+phone+"）不存在");
		}
		if( resultPhone.getIsDelete().equals(1) ) {	//判断用户是否删除
			throw new PhoneNotFoundException("登录失败，手机号（"+phone+"）不存在");
		}
		//对用户输入的密码进行加密
		String Md5Password = getMd5Password(password,resultPhone.getSalt());
		if( resultPhone.getPassword().equals(Md5Password) ) {	//判断密码是否正确
			resultPhone.setPassword(null);
			resultPhone.setSalt(null);
			resultPhone.setIsDelete(null);
		}else {
			throw new PasswordNotMatchException("登录失败，密码不正确");
		}
		return resultPhone;
	};
	
	/**
	 * 根据条件查询客户所有数据
	 */
	public PaginationVO<Customer> getSelectCustomer(Map<String,Object> map){
		List<Customer> list = selectCustomerForPage(map);
		long count = selectCustomerCount(map);
		PaginationVO<Customer> VO = new PaginationVO<Customer>();
		//把List和long封装成VO
		VO.setCount(count);
		VO.setDataList(list);
		return VO;
	};
	
	/**
	 * 删除客户数据
	 * @param uid
	 * @return 
	 */
	public void getdeleteId(Integer uid,String username) throws DeleteException{
		Integer count = deleteCustomerId( uid );
		if( count != 0 ){
			Customer customer = findByUid( uid );
			throw new ForeignKeyReferenceException("（"+customer.getUsername()+"）客户,有数据引用，不能删除");
		}else{
			//设置is_delete
			Integer isDelete = 1;
			String modifiedUser = username;
			Date modifiedTime = new Date();
			deleteCustomer(uid,isDelete,modifiedUser,modifiedTime);
		}
	};
	
	/**
	 * 修改客户数据
	 * @param customer
	 * @return
	 */
	public void getupdateCustomer(Customer customer,String username) throws UpdateException,UserNotFoundException{
		String phone = customer.getPhone();
		String email = customer.getEmail();
		Customer resultPhone = findByPhone(phone);
		if( resultPhone.getIsDelete().equals(1) ) { //该用户已经删除 throw new
			throw new UserNotFoundException("获取用户信息失败"); 
		}
		Customer resultEmail = findByEmail(email);
		if( resultEmail.getIsDelete().equals(1) ) { //该用户已经删除 throw new
			throw new UserNotFoundException("获取用户信息失败"); 
		}
		//设置四项日志
		if( customer != null ) {	//杜绝Null字符串异常
			Date time = new Date();
			customer.setModifiedUser(username);
			customer.setModifiedTime(time);
		}
		updateCustomer(customer);
	};

	/**
	 * 根据uid出现所有个人数据
	 * @param uid
	 * @return
	 */
	public Customer getfindByUid(Integer uid){
		Customer cus = findByUid(uid);
		return cus;
	};
	
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 */
	public void getfindByUidPassword(Integer uid,String username,String oldPassword,String newPassword) 
			throws UserNotFoundException,PasswordNotMatchException,UpdateException {
		Customer customer = findByuidPassword(uid);
		if( customer == null ) {
			throw new UserNotFoundException("修改失败，用户名不存在");
		}
		if( customer.getIsDelete().equals(1) ) {	//该用户已经删除
			throw new UserNotFoundException("修改失败，用户名不存在");
		}
		//对旧密码进行加密，以便于和数据库中比较
		String salt = customer.getSalt();
		String getMd5PasswordOld = getMd5Password(oldPassword, salt);
		if( ! customer.getPassword().equals(getMd5PasswordOld) ) {
			throw new PasswordNotMatchException("修改失败，原密码不正确");
		}
		//对新密码进行加密
		String getMd5PasswordNew = getMd5Password(newPassword, salt);
		//设置修改人，修改时间
		String modifiedUser = username;
		Date modifiedTime = new Date();
		updateByUidPassword(uid, getMd5PasswordNew, modifiedUser, modifiedTime);
	};
	
	/**
	 * 根据uid修改头像
	 */
	@Override
	public void changeAvatar(String avatar,Integer uid) {
		uploadAvatar(avatar,uid);
	}
	
	/**
	 * 查询客户数量
	 * @return
	 */
	public Long getselectIdCount(){
		return selectIdCount();
	};
	
	/**
	 * 根据年份，查询每年的12月注册数据
	 * @param year
	 * @return
	 */
	public List<CustomerTime> getselectYearMonth(Map<String,Object> map) throws YearMonthException{
		return selectYearMonth(map);
	};
	
	
	
	
	
	
	/****************************************************************************************************************/
	
	
	
	/**
	 * 根据年份，查询每年的12月注册数据
	 * @param year
	 * @return
	 */
	private List<CustomerTime> selectYearMonth(Map<String,Object> map){
		List<CustomerTime> time = customerdao.selectYearMonth(map);
		if( time == null ){
			throw new YearMonthException("查询错误，数据中没有您选择的年份");
		}
		return time;
	};
	
	/**
	 * 查询客户数量
	 * @return
	 */
	private Long selectIdCount(){
		return customerdao.selectIdCount();
	};
	
	/**
	 * 根据uid修改员工头像
	 * @param avatar
	 * @param uid
	 */
	private void uploadAvatar(String avatar,Integer uid) {
		Integer row = customerdao.uploadAvata(avatar,uid);
		if(row != 1) {
			throw new UpdateException("修改头像时出现未知错误!");
		}
	}
	
	/**
	 * 根据uid查询密码，和颜值
	 * @param uid
	 * @return
	 */
	private Customer findByuidPassword(Integer uid){
		return customerdao.findByuidPassword(uid);
	};
	
	/**
	 * 根据uid修改密码
	 * @param uid
	 * @param password
	 * @return
	 */
	private void updateByUidPassword(@Param("uid")Integer uid,@Param("password")String password,
			@Param("modifiedUser")String modifiedUser, @Param("modifiedTime")Date modifiedTime){
		Integer count = customerdao.updateByUidPassword(uid, password,modifiedUser,modifiedTime);
		if( count != 1 ) {
			throw new UpdateException("修改用户数据时出现未知错误！");
		}
	};
	
	/**
	 * 根据uid出现所有个人数据
	 * @param uid
	 * @return
	 */
	private Customer findByUid(Integer uid){
		return customerdao.findByUid(uid);
	};
	
	/**
	 * 修改客户数据
	 * @param customer
	 * @return
	 */
	private void updateCustomer(Customer customer){
		Integer count = customerdao.updateCustomer(customer);
		if( count != 1 ) {
			throw new UpdateException("修改数据时出现未知错误！");
		}
	};
	
	/**
	 * 根据uid删除客户信息
	 * @param uid
	 * @return
	 */
	private void deleteCustomer(Integer uid,Integer isDelete,String modifiedUser,Date modifiedTime){
		Integer count = customerdao.deleteCustomer(uid, isDelete, modifiedUser, modifiedTime);
		if( count != 1 ) {
			throw new DeleteException("插入用户数据时出现未知错误！");
		}
	};
	
	/**
	 * 判断该条数据是否被引用
	 * @param uid
	 * @return
	 */
	private Integer deleteCustomerId(Integer uid){
		return customerdao.deleteCustomerId(uid);
	};
	
	/**
	 * 根据条件查询客户所有数据
	 * @return
	 */
	private List<Customer> selectCustomerForPage(Map<String,Object> map){
		return customerdao.selectCustomerForPage(map);
	};
	
	/**
	 * 根据条件查询客户总条数
	 * @param map
	 * @return
	 */
	private Long selectCustomerCount(Map<String,Object> map){
		return customerdao.selectCustomerCount(map);
	};
	
	/**
	 * 根据手机号进行登录判断
	 * @param phone
	 * @return
	 */
	private Customer loginCustomer(String phone) {
		return customerdao.loginCustomer(phone);
	};
	
	/**
	 * 根据电话返回用户部分信息,主要用于保证插入数据时，电话的唯一性
	 * @param phone
	 * @return
	 */
	private Customer findByPhone(String phone) {
		return customerdao.findByPhone(phone);
	};
	
	/**
	 * 根据邮箱返回用户部分信息,主要用于保证插入数据时，邮箱的唯一性
	 * @param email
	 * @return
	 */
	private Customer findByEmail(String email) {
		return customerdao.findByEmail(email);
	};
	
	/**
	 * 客户简单注册（用户名、密码、盐值、手机号、邮箱，性别）
	 * @param customer
	 * @return
	 */
	private void insertCustomer(Customer customer) {
		Integer count = customerdao.insertCustomer(customer);
		if( count != 1 ) {
			throw new InsertException("插入用户数据时出现未知错误！");
		}
	}
	
	/**
	 * 获取执行MD5加密后的密码
	 * @param password
	 * @param salt
	 * @return	返回加密后的密码
	 */
	private String getMd5Password(String password,String salt) {
		//加密规则---自行加密---使用(“盐+密码+盐”)作为原始数据,执行5次进行加密
		String result = salt+password+salt;
		for (int i = 0; i < 5; i++) {
			result = DigestUtils.md5DigestAsHex( result.getBytes() ).toUpperCase();
		}
		return result;
	}
}
