package com.lk.model;

public class User {
	int id;
	String name;
	Long cound_id;
	String avatar_url;
	String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount_id() {
		return cound_id;
	}
	public void setCount_id(Long count_id) {
		this.cound_id = count_id;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
}
