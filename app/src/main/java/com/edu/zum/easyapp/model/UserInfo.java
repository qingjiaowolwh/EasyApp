package com.edu.zum.easyapp.model;

import java.io.Serializable;

public class UserInfo implements Serializable {

	/**
	 * "checkID": "89751FD52FE67C3B8BCDEBA5CEFCAE98", "token":
	 * "d65a9657-c3fc-4a62-4e57-e380ae892765", "username": null, "password":
	 * "8bb5ba2d71b5fb08b7a347fb0c7f5eb7", "phone": "15176103907", "nickname":
	 * null, "person": null, "img_url": "default.jpg", "sex": "0", "age": null,
	 * "regtype": null, "regtime": "1452048765", "explain": null
	 */
	private String checkID;
	private String token;
	private String username;
	private String password;
	private String phone;
	private String nickname;
	private String person;
	private String img_url;
	private String sex;
	private String age;
	private String regtype;
	private String regtime;
	private String explain;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRegtype() {
		return regtype;
	}

	public void setRegtype(String regtype) {
		this.regtype = regtype;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getCheckID() {
		return checkID;
	}

	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserInfo [checkID=" + checkID + ", token=" + token
				+ ", username=" + username + ", password=" + password
				+ ", phone=" + phone + ", nickname=" + nickname + ", person="
				+ person + ", img_url=" + img_url + ", sex=" + sex + ", age="
				+ age + ", regtype=" + regtype + ", regtime=" + regtime
				+ ", explain=" + explain + "]";
	}

}
