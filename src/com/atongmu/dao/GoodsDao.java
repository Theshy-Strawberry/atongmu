package com.atongmu.dao;

import java.util.List;

import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_commodity_commont;
import com.atongmu.bean.Tbl_shopping_cart;

public interface GoodsDao {
	//获取商品详情
    public Tbl_commodity getGoodsDetail(int goodsId);
	//获取商品详细图片
    public List<Tbl_commodity>  getGoodsImage(Tbl_commodity tbl_commodity);
    //获取评论
    public List<Tbl_commodity_commont>  getGoodsComment(Tbl_commodity_commont tbl_commodity_commont);
    //购物车一览
    public List<Tbl_commodity> showShoppingCarList(String uid);
    //增加/减少购买商品数量
    public int updateShoppingCarListCount(Tbl_shopping_cart tbl_shopping_cart);
    //删除购物车商品
    public int DeleteShoppingCarListGoods(Tbl_shopping_cart tbl_shopping_cart);
    //获取用户的购物车商品数量
    public int getShoppingCarCount(String userId);
}
