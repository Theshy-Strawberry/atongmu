package com.atongmu.dao;

import java.util.List;

import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_commodity_commont;
import com.atongmu.bean.Tbl_shopping_cart;

public interface GoodsDao {
	//��ȡ��Ʒ����
    public Tbl_commodity getGoodsDetail(int goodsId);
	//��ȡ��Ʒ��ϸͼƬ
    public List<Tbl_commodity>  getGoodsImage(Tbl_commodity tbl_commodity);
    //��ȡ����
    public List<Tbl_commodity_commont>  getGoodsComment(Tbl_commodity_commont tbl_commodity_commont);
    //���ﳵһ��
    public List<Tbl_commodity> showShoppingCarList(String uid);
    //����/���ٹ�����Ʒ����
    public int updateShoppingCarListCount(Tbl_shopping_cart tbl_shopping_cart);
    //ɾ�����ﳵ��Ʒ
    public int DeleteShoppingCarListGoods(Tbl_shopping_cart tbl_shopping_cart);
    //��ȡ�û��Ĺ��ﳵ��Ʒ����
    public int getShoppingCarCount(String userId);
}
