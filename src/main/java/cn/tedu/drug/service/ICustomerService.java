package cn.tedu.drug.service;

import java.util.List;
import java.util.Map;

import cn.tedu.drug.entity.Customer;
import cn.tedu.drug.entity.CustomerTime;
import cn.tedu.drug.entity.domain.PaginationVO;
import cn.tedu.drug.service.exception.DeleteException;
import cn.tedu.drug.service.exception.EmailDuplicateException;
import cn.tedu.drug.service.exception.InsertException;
import cn.tedu.drug.service.exception.PasswordNotMatchException;
import cn.tedu.drug.service.exception.PhoneDuplicateException;
import cn.tedu.drug.service.exception.PhoneNotFoundException;
import cn.tedu.drug.service.exception.UpdateException;
import cn.tedu.drug.service.exception.UserNotFoundException;
import cn.tedu.drug.service.exception.YearMonthException;

/**
 * 客户,业务层,接口
 * @author PHP
 *
 */
public interface ICustomerService {
	/**
	 * 	客户简单注册
	 * @param employees
	 * @return 返回注册成功与否信息
	 * @throws EmailDuplicateException 邮箱被占用时的异常
	 * @throws PhoneDuplicateException 电话被占用时的异常
	 * @throws InsertException	插入数据失败时的异常
	 */
	void reg(Customer customer) throws EmailDuplicateException,PhoneDuplicateException,UserNotFoundException,InsertException;
	
	/**
	 * 根据手机号进行登录判断
	 * @param phone
	 * @return 返回（uid,username,password,salt,isDelete,phone,avatar）
	 * @throws PhoneDuplicateException
	 * @throws PasswordNotMatchException
	 */
	Customer getloginCustomer(String phone,String password) throws PhoneNotFoundException,PasswordNotMatchException;
	
	/**
	 * 根据条件查询客户所有数据
	 */
	PaginationVO<Customer> getSelectCustomer(Map<String,Object> map);
	
	/**
	 * 删除药品
	 * @param uid
	 * @return 
	 */
	void getdeleteId(Integer uid,String username)throws DeleteException;
	
	/**
	 * 修改客户数据
	 * @param customer
	 * @return
	 */
	void getupdateCustomer(Customer customer,String username) throws UpdateException,UserNotFoundException;
	
	/**
	 * 根据uid出现所有个人数据
	 * @param uid
	 * @return
	 */
	Customer getfindByUid(Integer uid);
	
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 */
	void getfindByUidPassword(Integer uid,String username,String oldPassword,String newPassword)throws UserNotFoundException,PasswordNotMatchException,UpdateException;
	
	/**
	 * 根据uid修改头像
	 * @param uid
	 */
	void changeAvatar(String avatar,Integer uid);
	
	/**
	 * 查询客户数量
	 * @return
	 */
	Long getselectIdCount();
	
	/**
	 * 根据年份，查询每年的12月注册数据
	 * @param year
	 * @return
	 */
	List<CustomerTime> getselectYearMonth(Map<String,Object> map)throws YearMonthException;
	
	
	
	
	
	
}
