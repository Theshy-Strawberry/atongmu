package com.atongmu.bean;

public class Tbl_code_master {
	int	code_no;        /*	code no */
	String	code;           /*	code */
	String	code_value;     /*	code值 */
	int	group_order;    /*	组内排序用 */
	public int getCode_no()
	{
		return(code_no);
	}


	public void setCode_no( int code_no )
	{
		this.code_no = code_no;
	}


	public String getCode()
	{
		return(code);
	}


	public void setCode( String code )
	{
		this.code = code;
	}


	public String getCode_value()
	{
		return(code_value);
	}


	public void setCode_value( String code_value )
	{
		this.code_value = code_value;
	}


	public int getGroup_order()
	{
		return(group_order);
	}


	public void setGroup_order( int group_order )
	{
		this.group_order = group_order;
	}
}
