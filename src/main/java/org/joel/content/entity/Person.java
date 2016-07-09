package org.joel.content.entity;

public class Person {
	private int id;
	private String userName;
	private String password;
	private String nickName;
	private short userType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public short getUserType() {
		return userType;
	}
	public void setUserType(short userType) {
		this.userType = userType;
	}
	
	
}
