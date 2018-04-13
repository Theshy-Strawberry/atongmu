package com.atongmu.bean;

import java.util.Date;

public class Tbl_user {
	String	user_id;        /* 用户ID */
	String	user_pwd;       /* 用户密码 */
	Date	reg_date;       /* 注册日期 */
	String	open_id;        /* 微信openid */
	Double	user_integral;  /* 用户积分 */
	String	user_grade;     /* 用户等级 */
	String	user_tel_num;   /* 用户手机号 */
	String	user_name;      /* 真实姓名 */
	String	weixin_id;      /* 微信号 */
	String	user_sex;       /* 用户性别 */
	Date	user_birthday;  /* 用户生日 */
	String	saleman_id;     /* 关联的销售员ID */
	int	transfer_flag;  /* 转移flag */
	String home_area;    /* 所属管辖区域 */
	String user_addr;     /* 收货地址 */
	String user_post;     /* 邮编 */
	Date order_date;      /* 订单时间 */
	Double	order_price;  /*订单总金额 */
	Double	occur_bonus;   /*发生金额 */
	String userfrom;      /* 用户来自*/
	int vip_flag;//是否是Vip
	String user_nation;
	String user_education;
	String user_occupation;
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


	public String getUserfrom() {
		return userfrom;
	}


	public void setUserfrom(String userfrom) {
		this.userfrom = userfrom;
	}


	String	name;      /* 姓 */
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getOrder_price() {
		return order_price;
	}


	public Double getOccur_bonus() {
		return occur_bonus;
	}


	public void setOccur_bonus(Double occur_bonus) {
		this.occur_bonus = occur_bonus;
	}


	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}


	public String getUser_addr() {
		return user_addr;
	}


	public Date getOrder_date() {
		return order_date;
	}


	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}


	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}


	public String getUser_post() {
		return user_post;
	}


	public void setUser_post(String user_post) {
		this.user_post = user_post;
	}


	public String getUser_id()
	{
		return(user_id);
	}


	public void setUser_id( String user_id )
	{
		this.user_id = user_id;
	}


	public String getUser_pwd()
	{
		return(user_pwd);
	}


	public void setUser_pwd( String user_pwd )
	{
		this.user_pwd = user_pwd;
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


	public String getUser_grade()
	{
		return(user_grade);
	}


	public void setUser_grade( String user_grade )
	{
		this.user_grade = user_grade;
	}


	public String getUser_tel_num()
	{
		return(user_tel_num);
	}


	public void setUser_tel_num( String user_tel_num )
	{
		this.user_tel_num = user_tel_num;
	}


	public String getUser_name()
	{
		return(user_name);
	}


	public void setUser_name( String user_name )
	{
		this.user_name = user_name;
	}


	public String getWeixin_id()
	{
		return(weixin_id);
	}


	public void setWeixin_id( String weixin_id )
	{
		this.weixin_id = weixin_id;
	}


	public String getUser_sex()
	{
		return(user_sex);
	}


	public void setUser_sex( String user_sex )
	{
		this.user_sex = user_sex;
	}


	public Date getUser_birthday()
	{
		return(user_birthday);
	}


	public void setUser_birthday( Date user_birthday )
	{
		this.user_birthday = user_birthday;
	}


	public String getSaleman_id()
	{
		return(saleman_id);
	}


	public void setSaleman_id( String saleman_id )
	{
		this.saleman_id = saleman_id;
	}


	public int getTransfer_flag()
	{
		return(transfer_flag);
	}


	public void setTransfer_flag( int transfer_flag )
	{
		this.transfer_flag = transfer_flag;
	}


	public String getHome_area() {
		return home_area;
	}


	public void setHome_area(String home_area) {
		this.home_area = home_area;
	}


	public int getVip_flag() {
		return vip_flag;
	}


	public void setVip_flag(int vip_flag) {
		this.vip_flag = vip_flag;
	}
}
