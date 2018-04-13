package com.atongmu.bean;

import java.util.Date;

public class Tbl_commodity {
	int	goods_id;               /*	��ƷID */
	Date	add_date;               /*	������� */
	String	goods_name;             /*	��Ʒ�� */
	int	goods_category_id;      /*	��Ʒ����ID */
	String	goods_status;           /*	��Ʒ״̬ */
	Double	goods_integral;         /*	��Ʒ���� */
	Double	goods_integral_sale;    /*	��Ʒ�����ۼ� */
	Double	goods_price;            /*	��Ʒ�۸� */
	int	goods_stock;            /*	��Ʒ��� */
	int	goods_sales_volume;     /*	��Ʒ���� */
	byte[]   goods_image1;          /*	��Ʒ����ͼƬ1 */
	byte[]   goods_image2;          /*	��Ʒ����ͼƬ2 */
	byte[]   goods_image3;          /*	��Ʒ����ͼƬ3 */
	byte[]   goods_image4;          /*	��Ʒ����ͼƬ4 */
	byte[]   goods_image5;          /*	��Ʒ����ͼƬ5 */
	String	goods_spec;             /*	��Ʒ��� */
	Double	goods_discount;         /*	��Ʒ�ۿ� */
	String	goods_describe;         /*	��Ʒ���� */
	int	recommended_order;          /*	�Ƽ�˳�� */
	String	delete_flag;            /*	ɾ��flag */
	String	goods_keys;             /*	��Ʒ�ؼ��� */
	String add_user_id;             /*  �û�ID */
	int	goods_count;            /*	��Ʒ���� */
	int shopping_cart_id;            /*	���ﳵid */
	String goods_url;
	public String getGoods_url() {
		return goods_url;
	}


	public void setGoods_url(String goods_url) {
		this.goods_url = goods_url;
	}


	public int getShopping_cart_id() {
		return shopping_cart_id;
	}


	public void setShopping_cart_id(int shopping_cart_id) {
		this.shopping_cart_id = shopping_cart_id;
	}


	public int getGoods_count() {
		return goods_count;
	}


	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}


	public String getAdd_user_id() {
		return add_user_id;
	}


	public void setAdd_user_id(String add_user_id) {
		this.add_user_id = add_user_id;
	}


	public int getGoods_id()
	{
		return(goods_id);
	}


	public void setGoods_id( int goods_id )
	{
		this.goods_id = goods_id;
	}


	public Date getAdd_date()
	{
		return(add_date);
	}


	public void setAdd_date( Date add_date )
	{
		this.add_date = add_date;
	}


	public String getGoods_name()
	{
		return(goods_name);
	}


	public void setGoods_name( String goods_name )
	{
		this.goods_name = goods_name;
	}


	public int getGoods_category_id()
	{
		return(goods_category_id);
	}


	public void setGoods_category_id( int goods_category_id )
	{
		this.goods_category_id = goods_category_id;
	}


	public String getGoods_status()
	{
		return(goods_status);
	}


	public void setGoods_status( String goods_status )
	{
		this.goods_status = goods_status;
	}


	public Double getGoods_integral()
	{
		return(goods_integral);
	}


	public void setGoods_integral( Double goods_integral )
	{
		this.goods_integral = goods_integral;
	}


	public Double getGoods_integral_sale()
	{
		return(goods_integral_sale);
	}


	public void setGoods_integral_sale( Double goods_integral_sale )
	{
		this.goods_integral_sale = goods_integral_sale;
	}


	public Double getGoods_price()
	{
		return(goods_price);
	}


	public void setGoods_price( Double goods_price )
	{
		this.goods_price = goods_price;
	}


	public int getGoods_stock()
	{
		return(goods_stock);
	}


	public void setGoods_stock( int goods_stock )
	{
		this.goods_stock = goods_stock;
	}


	public int getGoods_sales_volume()
	{
		return(goods_sales_volume);
	}


	public void setGoods_sales_volume( int goods_sales_volume )
	{
		this.goods_sales_volume = goods_sales_volume;
	}


	public byte[] getGoods_image1()
	{
		return(goods_image1);
	}


	public void setGoods_image1( byte[] goods_image1 )
	{
		this.goods_image1 = goods_image1;
	}


	public byte[] getGoods_image2()
	{
		return(goods_image2);
	}


	public void setGoods_image2( byte[] goods_image2 )
	{
		this.goods_image2 = goods_image2;
	}


	public byte[] getGoods_image3()
	{
		return(goods_image3);
	}


	public void setGoods_image3( byte[] goods_image3 )
	{
		this.goods_image3 = goods_image3;
	}


	public byte[] getGoods_image4()
	{
		return(goods_image4);
	}


	public void setGoods_image4( byte[] goods_image4 )
	{
		this.goods_image4 = goods_image4;
	}


	public byte[] getGoods_image5()
	{
		return(goods_image5);
	}


	public void setGoods_image5( byte[] goods_image5 )
	{
		this.goods_image5 = goods_image5;
	}


	public String getGoods_spec()
	{
		return(goods_spec);
	}


	public void setGoods_spec( String goods_spec )
	{
		this.goods_spec = goods_spec;
	}


	public Double getGoods_discount()
	{
		return(goods_discount);
	}


	public void setGoods_discount( Double goods_discount )
	{
		this.goods_discount = goods_discount;
	}


	public String getGoods_describe()
	{
		return(goods_describe);
	}


	public void setGoods_describe( String goods_describe )
	{
		this.goods_describe = goods_describe;
	}


	public int getRecommended_order()
	{
		return(recommended_order);
	}


	public void setRecommended_order( int recommended_order )
	{
		this.recommended_order = recommended_order;
	}


	public String getDelete_flag()
	{
		return(delete_flag);
	}


	public void setDelete_flag( String delete_flag )
	{
		this.delete_flag = delete_flag;
	}


	public String getGoods_keys()
	{
		return(goods_keys);
	}


	public void setGoods_keys( String goods_keys )
	{
		this.goods_keys = goods_keys;
	}
}
