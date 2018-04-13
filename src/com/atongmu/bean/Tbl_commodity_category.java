package com.atongmu.bean;

import java.util.Date;

public class Tbl_commodity_category {
	int	category_id;    /*	分类ID */
	Date	add_date;       /*	添加时间 */
	String	category_name;  /*	分类名 */
	public int getCategory_id()
	{
		return(category_id);
	}


	public void setCategory_id( int category_id )
	{
		this.category_id = category_id;
	}


	public Date getAdd_date()
	{
		return(add_date);
	}


	public void setAdd_date( Date add_date )
	{
		this.add_date = add_date;
	}


	public String getCategory_name()
	{
		return(category_name);
	}


	public void setCategory_name( String category_name )
	{
		this.category_name = category_name;
	}
}
