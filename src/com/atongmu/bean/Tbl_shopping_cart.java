package com.atongmu.bean;

import java.util.Date;

public class Tbl_shopping_cart {
	int	shopping_cart_id;       /*	���ﳵID */
	public String getAdd_user_id() {
		return add_user_id;
	}


	public void setAdd_user_id(String add_user_id) {
		this.add_user_id = add_user_id;
	}


	String add_user_id;         /*  �û�ID */
	Date	add_date;               /*	����ʱ�� */
	int	goods_id;               /*	��ƷID */
	int	goods_count;            /*	��Ʒ���� */
	public int getShopping_cart_id()
	{
		return(shopping_cart_id);
	}


	public void setShopping_cart_id( int shopping_cart_id )
	{
		this.shopping_cart_id = shopping_cart_id;
	}


	public Date getAdd_date()
	{
		return(add_date);
	}


	public void setAdd_date( Date add_date )
	{
		this.add_date = add_date;
	}


	public int getGoods_id()
	{
		return(goods_id);
	}


	public void setGoods_id( int goods_id )
	{
		this.goods_id = goods_id;
	}


	public int getGoods_count()
	{
		return(goods_count);
	}


	public void setGoods_count( int goods_count )
	{
		this.goods_count = goods_count;
	}
}
