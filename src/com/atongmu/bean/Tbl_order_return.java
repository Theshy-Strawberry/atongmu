package com.atongmu.bean;

import java.util.Date;

public class Tbl_order_return {
	String	order_id;               /*	����ID */
	String	goods_id;               /*	��ƷID */
	Date	request_date;           /*	�������� */
	Date	return_status;          /*	�˻���״̬ */
	String	return_reason;          /*	�˻������� */
	String	from_logistics_company; /*	������˾ */
	String	from_logistics_number;  /*	������� */
	String	return_date;            /*	��������ʱ�� */
	String	to_logistics_company;   /*	������˾ */
	String	to_logistics_number;    /*	������� */
	public String getOrder_id()
	{
		return(order_id);
	}


	public void setOrder_id( String order_id )
	{
		this.order_id = order_id;
	}


	public String getGoods_id()
	{
		return(goods_id);
	}


	public void setGoods_id( String goods_id )
	{
		this.goods_id = goods_id;
	}


	public Date getRequest_date()
	{
		return(request_date);
	}


	public void setRequest_date( Date request_date )
	{
		this.request_date = request_date;
	}


	public Date getReturn_status()
	{
		return(return_status);
	}


	public void setReturn_status( Date return_status )
	{
		this.return_status = return_status;
	}


	public String getReturn_reason()
	{
		return(return_reason);
	}


	public void setReturn_reason( String return_reason )
	{
		this.return_reason = return_reason;
	}


	public String getFrom_logistics_company()
	{
		return(from_logistics_company);
	}


	public void setFrom_logistics_company( String from_logistics_company )
	{
		this.from_logistics_company = from_logistics_company;
	}


	public String getFrom_logistics_number()
	{
		return(from_logistics_number);
	}


	public void setFrom_logistics_number( String from_logistics_number )
	{
		this.from_logistics_number = from_logistics_number;
	}


	public String getReturn_date()
	{
		return(return_date);
	}


	public void setReturn_date( String return_date )
	{
		this.return_date = return_date;
	}


	public String getTo_logistics_company()
	{
		return(to_logistics_company);
	}


	public void setTo_logistics_company( String to_logistics_company )
	{
		this.to_logistics_company = to_logistics_company;
	}


	public String getTo_logistics_number()
	{
		return(to_logistics_number);
	}


	public void setTo_logistics_number( String to_logistics_number )
	{
		this.to_logistics_number = to_logistics_number;
	}
}
