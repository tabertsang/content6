package org.joel.content.dto;

/**
 * 传输给前端页面的用户数据
 * Created by joel on Jun 22, 2016
 *
 */
public class UserData {
	private String username;  // 用户别名
	private short usertype;   // 用户类型
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public short getUsertype() {
		return usertype;
	}
	public void setUsertype(short usertype) {
		this.usertype = usertype;
	}

}
