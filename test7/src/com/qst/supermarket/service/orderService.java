/**
 * 
 */
package com.qst.supermarket.service;

import java.util.List;

import com.qst.supermarket.dao.UserDAO;

/**
 * @author 12345678
 *
 */
public class orderService {
	UserDAO userDAO=new UserDAO();

	/**
	 * @return
	 */
	public static List<PieData> getEmloyeeStaticData() {
		// TODO Auto-generated method stub
		return UserDAO.getEmloyeeStaticData();
	}

}
