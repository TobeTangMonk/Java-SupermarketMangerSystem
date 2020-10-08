/**
 * 
 */
package com.qst.supermarket.service;

import com.qst.supermarket.dao.UserDAO;
import com.qst.supermarket.model.User;

/**
 * @author 12345678
 *
 */
public class UserService {
//  userDAO 专门操作用户表的类
	
  private UserDAO userDAO=new UserDAO();

/**
 * @param username
 * @param passwrod
 * @param accountType
 * @return
 */
public User login(String username, String passwrod,String accountType) {
	// TODO Auto-generated method stub
	return userDAO.login( username, passwrod,accountType);
}
	
	

}
