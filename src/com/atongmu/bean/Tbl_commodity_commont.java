package com.atongmu.bean;

import java.util.Date;

public class Tbl_commodity_commont {
	int	goods_id;       /*  */
	String	user_id;        /*  */
	Date	commont_date;   /*  */
	int	display_flag;   /*  */
	String	commont;        /*  */
	byte[]   commont_image; /*  */
	public int getGoods_id()
	{
		return(goods_id);
	}


	public void setGoods_id( int goods_id )
	{
		this.goods_id = goods_id;
	}


	public String getUser_id()
	{
		return(user_id);
	}


	public void setUser_id( String user_id )
	{
		this.user_id = user_id;
	}


	public Date getCommont_date()
	{
		return(commont_date);
	}


	public void setCommont_date( Date commont_date )
	{
		this.commont_date = commont_date;
	}


	public int getDisplay_flag()
	{
		return(display_flag);
	}


	public void setDisplay_flag( int display_flag )
	{
		this.display_flag = display_flag;
	}


	public String getCommont()
	{
		return(commont);
	}


	public void setCommont( String commont )
	{
		this.commont = commont;
	}


	public byte[] getCommont_image()
	{
		return(commont_image);
	}


	public void setCommont_image( byte[] commont_image )
	{
		this.commont_image = commont_image;
	}
}
