package com.atongmu.bean;

import java.util.Date;

public class Tbl_bonus_audit {
	int	req_no;         /*	请求编号 */
	Date	req_date;       /*	请求日期 */
	String	req_user_id;    /*	请求用户ID */
	Double	req_bouns;      /*	请求提取佣金 */
	String	req_status;     /*	申请状态 */
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


	public Double getReq_bouns()
	{
		return(req_bouns);
	}


	public void setReq_bouns( Double req_bouns )
	{
		this.req_bouns = req_bouns;
	}


	public String getReq_status()
	{
		return(req_status);
	}


	public void setReq_status( String req_status )
	{
		this.req_status = req_status;
	}
}
