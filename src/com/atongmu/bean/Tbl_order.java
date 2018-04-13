package com.atongmu.bean;

import java.util.Date;

public class Tbl_order {
	String	order_id;               /*	订单ID */
	String	order_user;             /*	用户或销售员ID */
	Date	order_date;             /*	订单时间 */
	String	order_status;           /*	订单状态 */
	Double	order_price;            /*	订单总金额 */
	String	payment_method;         /*	支付方式 */
	String	discount_method;        /*	折扣方式 */
	Double	use_integral;           /*	使用积分 */
	Double	discount_amount;        /*	折扣金额 */
	String	logistics_company;      /*	物流公司 */
	String	logistics_number;       /*	物流编号 */
	Double	logistics_price;        /*	物流价格 */
	String	invoice_flag;           /*	是否需要发票 */
	String	delete_flag;            /*	删除flag */
	String	invoice_type;           /*	发票类型 */
	String	invoice_company;        /*	发票抬头 */
	String	order_context;          /*	订单备注 */
	String user_name ;
	String userType;
	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getOrder_id()
	{
		return(order_id);
	}


	public void setOrder_id( String order_id )
	{
		this.order_id = order_id;
	}


	public String getOrder_user()
	{
		return(order_user);
	}


	public void setOrder_user( String order_user )
	{
		this.order_user = order_user;
	}


	public Date getOrder_date()
	{
		return(order_date);
	}


	public void setOrder_date( Date order_date )
	{
		this.order_date = order_date;
	}


	public String getOrder_status()
	{
		return(order_status);
	}


	public void setOrder_status( String order_status )
	{
		this.order_status = order_status;
	}


	public Double getOrder_price()
	{
		return(order_price);
	}


	public void setOrder_price( Double order_price )
	{
		this.order_price = order_price;
	}


	public String getPayment_method()
	{
		return(payment_method);
	}


	public void setPayment_method( String payment_method )
	{
		this.payment_method = payment_method;
	}


	public String getDiscount_method()
	{
		return(discount_method);
	}


	public void setDiscount_method( String discount_method )
	{
		this.discount_method = discount_method;
	}


	public Double getUse_integral()
	{
		return(use_integral);
	}


	public void setUse_integral( Double use_integral )
	{
		this.use_integral = use_integral;
	}


	public Double getDiscount_amount()
	{
		return(discount_amount);
	}


	public void setDiscount_amount( Double discount_amount )
	{
		this.discount_amount = discount_amount;
	}


	public String getLogistics_company()
	{
		return(logistics_company);
	}


	public void setLogistics_company( String logistics_company )
	{
		this.logistics_company = logistics_company;
	}


	public String getLogistics_number()
	{
		return(logistics_number);
	}


	public void setLogistics_number( String logistics_number )
	{
		this.logistics_number = logistics_number;
	}


	public Double getLogistics_price()
	{
		return(logistics_price);
	}


	public void setLogistics_price( Double logistics_price )
	{
		this.logistics_price = logistics_price;
	}


	public String getInvoice_flag()
	{
		return(invoice_flag);
	}


	public void setInvoice_flag( String invoice_flag )
	{
		this.invoice_flag = invoice_flag;
	}


	public String getDelete_flag()
	{
		return(delete_flag);
	}


	public void setDelete_flag( String delete_flag )
	{
		this.delete_flag = delete_flag;
	}


	public String getInvoice_type()
	{
		return(invoice_type);
	}


	public void setInvoice_type( String invoice_type )
	{
		this.invoice_type = invoice_type;
	}


	public String getInvoice_company()
	{
		return(invoice_company);
	}


	public void setInvoice_company( String invoice_company )
	{
		this.invoice_company = invoice_company;
	}


	public String getOrder_context()
	{
		return(order_context);
	}


	public void setOrder_context( String order_context )
	{
		this.order_context = order_context;
	}
}
