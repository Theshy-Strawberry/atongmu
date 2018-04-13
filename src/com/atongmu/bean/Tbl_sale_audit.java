package com.atongmu.bean;

import java.io.FileInputStream;
import java.util.Date;

import com.jspsmart.upload.File;

public class Tbl_sale_audit {
	int	req_no;                 /*	������ */
	Date	req_date;               /*	�������� */
	String	req_user_id;            /*	�����û�ID */
	FileInputStream   id_card_front;         /*	���֤��Ƭǰ�� */
	File id_card_front_file;
	FileInputStream   id_card_back;          /*	���֤��Ƭ���� */
	File id_card_back_file;
	String	req_status;             /*	����״̬ */
	String	req_current_grade;      /*	��ǰ�ȼ� */
	String	req_grade;              /*	��������ȼ� */
	public int getReq_no()
	{
		return(req_no);
	}


	public void setReq_no( int req_no )
	{
		this.req_no = req_no;
	}


	public Date getReq_date()
	{
		return(req_date);
	}


	public void setReq_date( Date req_date )
	{
		this.req_date = req_date;
	}


	public String getReq_user_id()
	{
		return(req_user_id);
	}


	public void setReq_user_id( String req_user_id )
	{
		this.req_user_id = req_user_id;
	}


	public String getReq_status()
	{
		return(req_status);
	}


	public void setReq_status( String req_status )
	{
		this.req_status = req_status;
	}


	public String getReq_current_grade()
	{
		return(req_current_grade);
	}


	public void setReq_current_grade( String req_current_grade )
	{
		this.req_current_grade = req_current_grade;
	}


	public String getReq_grade()
	{
		return(req_grade);
	}


	public void setReq_grade( String req_grade )
	{
		this.req_grade = req_grade;
	}


	public FileInputStream getId_card_front() {
		return id_card_front;
	}


	public void setId_card_front(FileInputStream id_card_front) {
		this.id_card_front = id_card_front;
	}


	public File getId_card_front_file() {
		return id_card_front_file;
	}


	public void setId_card_front_file(File id_card_front_file) {
		this.id_card_front_file = id_card_front_file;
	}


	public FileInputStream getId_card_back() {
		return id_card_back;
	}


	public void setId_card_back(FileInputStream id_card_back) {
		this.id_card_back = id_card_back;
	}


	public File getId_card_back_file() {
		return id_card_back_file;
	}


	public void setId_card_back_file(File id_card_back_file) {
		this.id_card_back_file = id_card_back_file;
	}


}
