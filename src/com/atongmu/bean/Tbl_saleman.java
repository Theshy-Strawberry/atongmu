package com.atongmu.bean;

import java.util.Date;

public class Tbl_saleman {
	String	saleman_id;             /* ����ԱID */
	String	saleman_pwd;            /* ����Ա���� */
	Date	reg_date;               /* �������� */
	String	open_id;                /* ΢��openid */
	Double	user_integral;          /* �û����� */
	String	up_saleman_id;          /* �ϼ�����ԱID */
	String	saleman_tel_num;        /* �û��ֻ��� */
	String	saleman_addr;           /* ����Ա�ջ���ַ */
	String	saleman_post;           /* ����Ա�ʱ� */
	String	saleman_name;           /* ��ʵ���� */
	String	weixin_id;              /* ΢�ź� */
	String	saleman_sex;            /* ����Ա�Ա� */
	Date	saleman_birthday;       /* ����Ա���� */
	String	alipay_id;              /* ֧�����˺� */
	String	tenpay_id;              /* �Ƹ�ͨ�˺� */
	String	card_bank;              /* ���п������� */
	String	card_name;              /* ���п������� */
	String	card_number;            /* ���п��� */
	Double	wages;                  /* ��ǰ������ */
	Date	revoke_date;            /* ����ʱ�� */
	String	revoke_reason;          /* �������� */
	String home_area;                 /* ������Ͻ���� */
	Tbl_code_master home_area_info;   /*codemaster�����鱨*/
	String saleman_level;             /*����Ա�ȼ�*/
	String user_nation;
	String userfrom;
	public String getUserfrom() {
		return userfrom;
	}


	public void setUserfrom(String userfrom) {
		this.userfrom = userfrom;
	}


	public String getUser_nation() {
		return user_nation;
	}


	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}


	public String getUser_education() {
		return user_education;
	}


	public void setUser_education(String user_education) {
		this.user_education = user_education;
	}


	public String getUser_occupation() {
		return user_occupation;
	}


	public void setUser_occupation(String user_occupation) {
		this.user_occupation = user_occupation;
	}


	String user_education;
	String user_occupation;
	public String getSaleman_id()
	{
		return(saleman_id);
	}


	public void setSaleman_id( String saleman_id )
	{
		this.saleman_id = saleman_id;
	}


	public String getSaleman_pwd()
	{
		return(saleman_pwd);
	}


	public void setSaleman_pwd( String saleman_pwd )
	{
		this.saleman_pwd = saleman_pwd;
	}


	public Date getReg_date()
	{
		return(reg_date);
	}


	public void setReg_date( Date reg_date )
	{
		this.reg_date = reg_date;
	}


	public String getOpen_id()
	{
		return(open_id);
	}


	public void setOpen_id( String open_id )
	{
		this.open_id = open_id;
	}


	public Double getUser_integral()
	{
		return(user_integral);
	}


	public void setUser_integral( Double user_integral )
	{
		this.user_integral = user_integral;
	}


	public String getUp_saleman_id()
	{
		return(up_saleman_id);
	}


	public void setUp_saleman_id( String up_saleman_id )
	{
		this.up_saleman_id = up_saleman_id;
	}


	public String getSaleman_tel_num()
	{
		return(saleman_tel_num);
	}


	public void setSaleman_tel_num( String saleman_tel_num )
	{
		this.saleman_tel_num = saleman_tel_num;
	}


	public String getSaleman_addr()
	{
		return(saleman_addr);
	}


	public void setSaleman_addr( String saleman_addr )
	{
		this.saleman_addr = saleman_addr;
	}


	public String getSaleman_post()
	{
		return(saleman_post);
	}


	public void setSaleman_post( String saleman_post )
	{
		this.saleman_post = saleman_post;
	}


	public String getSaleman_name()
	{
		return(saleman_name);
	}


	public void setSaleman_name( String saleman_name )
	{
		this.saleman_name = saleman_name;
	}


	public String getWeixin_id()
	{
		return(weixin_id);
	}


	public void setWeixin_id( String weixin_id )
	{
		this.weixin_id = weixin_id;
	}


	public String getSaleman_sex()
	{
		return(saleman_sex);
	}


	public void setSaleman_sex( String saleman_sex )
	{
		this.saleman_sex = saleman_sex;
	}


	public Date getSaleman_birthday()
	{
		return(saleman_birthday);
	}


	public void setSaleman_birthday( Date saleman_birthday )
	{
		this.saleman_birthday = saleman_birthday;
	}


	public String getAlipay_id()
	{
		return(alipay_id);
	}


	public void setAlipay_id( String alipay_id )
	{
		this.alipay_id = alipay_id;
	}


	public String getTenpay_id()
	{
		return(tenpay_id);
	}


	public void setTenpay_id( String tenpay_id )
	{
		this.tenpay_id = tenpay_id;
	}


	public String getCard_bank()
	{
		return(card_bank);
	}


	public void setCard_bank( String card_bank )
	{
		this.card_bank = card_bank;
	}


	public String getCard_name()
	{
		return(card_name);
	}


	public void setCard_name( String card_name )
	{
		this.card_name = card_name;
	}


	public String getCard_number()
	{
		return(card_number);
	}


	public void setCard_number( String card_number )
	{
		this.card_number = card_number;
	}


	public Double getWages()
	{
		return(wages);
	}


	public void setWages( Double wages )
	{
		this.wages = wages;
	}


	public Date getRevoke_date()
	{
		return(revoke_date);
	}


	public void setRevoke_date( Date revoke_date )
	{
		this.revoke_date = revoke_date;
	}


	public String getRevoke_reason()
	{
		return(revoke_reason);
	}


	public void setRevoke_reason( String revoke_reason )
	{
		this.revoke_reason = revoke_reason;
	}


	public String getHome_area() {
		return home_area;
	}


	public void setHome_area(String home_area) {
		this.home_area = home_area;
	}


	public Tbl_code_master getHome_area_info() {
		return home_area_info;
	}


	public void setHome_area_info(Tbl_code_master home_area_info) {
		this.home_area_info = home_area_info;
	}


	public String getSaleman_level() {
		return saleman_level;
	}


	public void setSaleman_level(String saleman_level) {
		this.saleman_level = saleman_level;
	}
}
