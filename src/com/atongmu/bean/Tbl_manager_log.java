package com.atongmu.bean;

public class Tbl_manager_log {
	int log_id; // 日志ID
	String log_date; // 日志时间
	String log_manager_ID; // 操作管理员ID
	String log_type; // 日志类型
	String log_info; // 日志详细

	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public String getLog_date() {
		return log_date;
	}

	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}

	public String getLog_manager_ID() {
		return log_manager_ID;
	}

	public void setLog_manager_ID(String log_manager_ID) {
		this.log_manager_ID = log_manager_ID;
	}

	public String getLog_type() {
		return log_type;
	}

	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}

	public String getLog_info() {
		return log_info;
	}

	public void setLog_info(String log_info) {
		this.log_info = log_info;
	}

}
