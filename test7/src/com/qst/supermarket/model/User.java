/**
 * 
 */
package com.qst.supermarket.model;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * @author 12345678
 *
 */
//  ��Plain Old Java Object�� �򵥵�Java����  Ҳ����POJO��ͨjava�� ������javaPackege  view service dao �н������ݴ��ݣ�User�൱��һ�ű����Ժ����ݿ�һһ��Ӧ
public class User {
	private Integer id;
	private String password;
	private String userType;
    private String username;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", userType=" + userType + ", username=" + username + "]";
	}	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
