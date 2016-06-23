package com.edu.zum.easyapp.model;

public class Status {
	/*
	 * "status": { "message": "SUCCESS", "systemTime":
	 * "2015-12-26 18:25:28.734", "code": 200 }
	 */

	private String msg;
	private String systemTime;
	private int code;

	public String getMessage() {
		return msg;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
