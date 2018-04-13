package com.atongmu.bean;

public class Tbl_order_detail {
	String	order_id;       /*	订单ID */
	String	goods_id;       /*	商品ID */
	int	goods_number;   /*	购买商品数量 */
	int	return_number;  /*	退换货数量 */
	String	return_type;    /*	退换货属性 */
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


	public int getGoods_number()
	{
		return(goods_number);
	}


	public void setGoods_number( int goods_number )
	{
		this.goods_number = goods_number;
	}


	public int getReturn_number()
	{
		return(return_number);
	}


	public void setReturn_number( int return_number )
	{
		this.return_number = return_number;
	}


	public String getReturn_type()
	{
		return(return_type);
	}


	public void setReturn_type( String return_type )
	{
		this.return_type = return_type;
	}
}
