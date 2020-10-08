/**
 * 
 */
package com.qst.supermarket.dao;

import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.qst.supermarket.model.User;
import com.qst.supermarket.service.PieData;
import com.qst.supermarket.utils.DBUtils;

/**
 * @author 12345678
 *
 */

// UserDAO直接和数据库的表来匹配
public class UserDAO {

	/**
	 * @param username
	 * @param passwrod
	 * @param accountType
	 * @return
	 */
	public User login(String username, String passwrod, String usertype) {
		// TODO Auto-generated method stub
		
		String sql="select id,username,password,usertype from users where username=? and password=? and usertype=?";
		
		
		User user=DBUtils.selectSingleObject(User.class, sql, username,passwrod,usertype);
		
		return user;
	}

	/**
	 * @return
	 */
	public static List<PieData> getEmloyeeStaticData() {
		
		String sql="select SUM(o.total),o.barcode,o.buyAmmount,o.cashierId,u.username,u.usertype from orders o LEFT OUTER JOIN users u on u.id=o.cashierId group by u.id";
		List<PieData>	pieDatas=DBUtils.query(PieData.class, sql);
		
		
		return pieDatas;
	}

}
