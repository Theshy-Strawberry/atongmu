package com.atongmu.bean;

import java.util.Date;

public class Tbl_manager {
	int	manager_ID;             /* ����ԱID */
	String	manager_username;       /* ����Ա��¼�� */
	String	manager_password;       /* ����Ա���� */
	String	manager_name;           /* ����Ա���� */
	String	manager_telno;          /* ����Ա�ֻ��� */
	String	manager_role;           /* ����ԱȨ�� */
	String	manager_home_area;      /* ����Ա��Ͻ���� */
	Date	join_date;              /* �������� */
	Tbl_code_master manager_role_info;
	Tbl_code_master manager_home_area_info;
	public int getManager_ID() {
		return manager_ID;
	}
	public void setManager_ID(int manager_ID) {
		this.manager_ID = manager_ID;
	}
	public String getManager_username() {
		return manager_username;
	}
	public void setManager_username(String manager_username) {
		this.manager_username = manager_username;
	}
	public String getManager_password() {
		return manager_password;
	}
	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getManager_telno() {
		return manager_telno;
	}
	public void setManager_telno(String manager_telno) {
		this.manager_telno = manager_telno;
	}
	public String getManager_role() {
		return manager_role;
	}
	public void setManager_role(String manager_role) {
		this.manager_role = manager_role;
	}
	public String getManager_home_area() {
		return manager_home_area;
	}
	public void setManager_home_area(String manager_home_area) {
		this.manager_home_area = manager_home_area;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public Tbl_code_master getManager_role_info() {
		return manager_role_info;
	}
	public void setManager_role_info(Tbl_code_master manager_role_info) {
		this.manager_role_info = manager_role_info;
	}
	public Tbl_code_master getManager_home_area_info() {
		return manager_home_area_info;
	}
	public void setManager_home_area_info(Tbl_code_master manager_home_area_info) {
		this.manager_home_area_info = manager_home_area_info;
	}

}
