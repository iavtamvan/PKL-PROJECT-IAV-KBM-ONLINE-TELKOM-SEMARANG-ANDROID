package com.iavariav.kbmonline.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel{

	@SerializedName("error_msg")
	private String errorMsg;

	@SerializedName("rule")
	private String rule;

	@SerializedName("id")
	private String id;

	@SerializedName("error")
	private boolean error;

	@SerializedName("username")
	private String username;

	@SerializedName("key")
	private String key;


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setErrorMsg(String errorMsg){
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	public void setRule(String rule){
		this.rule = rule;
	}

	public String getRule(){
		return rule;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}