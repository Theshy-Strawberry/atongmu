package com.atongmu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_commodity_category;
import com.atongmu.bean.Tbl_commodity_commont;
import com.atongmu.bean.Tbl_shopping_cart;
import com.atongmu.dao.GoodsDao;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

public class GoodsDaoImpl implements GoodsDao {
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;

	@Override
	public Tbl_commodity getGoodsDetail(int goodsId) {
		CommonUtil.logger.info("【mobile】"+"into GoodsDaoImpl,getGoodsDetail");
		Tbl_commodity commodity = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append(" 	tc.goods_name,");
			sql.append(" 	tc.goods_price,");
			sql.append(" 	tc.goods_integral,");
			sql.append(" 	tc.goods_stock,tc.goods_sales_volume,goods_spec,goods_describe,tc.goods_url");
			sql.append(" FROM");
			sql.append(" 	tbl_commodity tc");
			sql.append(" WHERE tc.goods_id = ?");
			ps = con.prepareStatement(sql.toString());
            ps.setInt(1, goodsId);
            rs = ps.executeQuery();
            if(rs.next()){
            	commodity=new Tbl_commodity();
            	String goods_name=StringUtil.nvl(rs.getString("goods_name"));
            	double goods_price=rs.getDouble("goods_price");
            	int good_stock= rs.getInt("goods_stock");
            	double goods_integral=rs.getDouble("goods_integral");
            	int goods_sales_volume = rs.getInt("goods_sales_volume");
            	String goods_spec=StringUtil.nvl(rs.getString("goods_spec"));
            	String goods_describe=StringUtil.nvl(rs.getString("goods_describe"));
            	int goods_count = 1;
	            commodity.setGoods_name(goods_name);
	            commodity.setGoods_price(goods_price);
	            commodity.setGoods_stock(good_stock);
	            commodity.setGoods_integral(goods_integral);
	            commodity.setGoods_sales_volume(goods_sales_volume);
	            commodity.setGoods_count(goods_count);
	            commodity.setGoods_spec(goods_spec);
	            commodity.setGoods_describe(goods_describe);
	            commodity.setGoods_url(StringUtil.nvl(rs.getString("goods_url")));

            }
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
			
		}
		finally{
			MySQLUtil.closeAll(rs, sm, con, ps);
		}


		return commodity;
	}


	@Override
	public List<Tbl_commodity> getGoodsImage(Tbl_commodity tbl_commodity) {
		CommonUtil.logger.info("【mobile】"+"into GoodsDaoImpl,getGoodsImage");
		Tbl_commodity commodity = new Tbl_commodity();
		List<Tbl_commodity> commoditylist = new ArrayList<Tbl_commodity>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append(" 	tc.goods_image1,");
			sql.append(" 	tc.goods_image2,");
			sql.append(" 	tc.goods_image3,");
			sql.append(" 	tc.goods_image4,");
			sql.append(" 	tc.goods_image5,");
			sql.append(" FROM");
			sql.append(" 	tbl_commodity tc");
			sql.append(" WHERE tc.goods_id = ?");
			ps = con.prepareStatement(sql.toString());
			int goods_id = tbl_commodity.getGoods_id();
			String goods_id_string = Integer.toString(goods_id);
            ps.setString(1,goods_id_string );
            rs = ps.executeQuery();
            while(rs.next()){
            	commodity.setGoods_name(rs.getString("goods_name"));
            	commodity.setGoods_price(rs.getDouble("goods_price"));
            	commodity.setGoods_integral(rs.getDouble("goods_integral"));
            	commodity.setGoods_name(rs.getString("goods_name"));
            	commoditylist.add(commodity);
            }
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		}
		finally{
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return commoditylist;
	}

	@Override
	public List<Tbl_commodity_commont> getGoodsComment(
			Tbl_commodity_commont tbl_commodity_commont) {
		CommonUtil.logger.info("【mobile】"+"into GoodsDaoImpl,getGoodsComment");
		Tbl_commodity_commont commodity_commont = new Tbl_commodity_commont();
		List<Tbl_commodity_commont> commodity_commont_list = new ArrayList<Tbl_commodity_commont>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append(" 	top(5) *");
			sql.append(" FROM");
			sql.append(" 	tbl_commodity_commont tcc");
			sql.append(" WHERE tcc.goods_id = ? and");
			sql.append(" WHERE tcc.user_id = ? and");
			sql.append(" WHERE tcc.commont_date	 = ?");
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1, tbl_commodity_commont.getGoods_id());
			ps.setString(2, tbl_commodity_commont.getUser_id());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(tbl_commodity_commont.getCommont_date());
			ps.setString(3, date);
			rs = ps.executeQuery();
			while(rs.next()){
				commodity_commont.setGoods_id(rs.getInt("goods_id"));
				commodity_commont.setUser_id(rs.getString("user_id"));
				commodity_commont.setCommont_date(rs.getDate("commont_date"));
				commodity_commont.setCommont(rs.getString("commont"));
				commodity_commont.setCommont_image(rs.getBytes("commont_image"));
				commodity_commont_list.add(commodity_commont);
			}
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		}finally{
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return commodity_commont_list;
	}


	public List<Tbl_commodity> showShoppingCarList(String uid) {
		List<Tbl_commodity> list = new ArrayList<Tbl_commodity>();
		try {
			CommonUtil.logger.info("【mobile】"+"into GoodsDaoImpl,showShoppingCarList");
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append("SELECT tc.goods_name,tc.goods_price,ts.goods_count,tc.goods_id,");
			sql.append("ts.shopping_cart_id,ts.add_date,ts.add_user_id");
			sql.append(" FROM tbl_commodity tc INNER JOIN tbl_shopping_cart ts");
			sql.append(" ON tc.goods_id=ts.goods_id");
			sql.append(" where ts.add_user_id=?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, uid);
			rs = ps.executeQuery();
			while(rs.next()){
				Tbl_commodity commodity =new Tbl_commodity();
				commodity.setGoods_name(rs.getString("goods_name"));
				commodity.setGoods_price(rs.getDouble("goods_price"));
                commodity.setGoods_count(rs.getInt("goods_count"));
                commodity.setGoods_id(rs.getInt("goods_id"));
                commodity.setAdd_date(rs.getDate("ts.add_date"));
                commodity.setAdd_user_id(rs.getString("ts.add_user_id"));
                commodity.setShopping_cart_id(rs.getInt("ts.shopping_cart_id"));

                list.add(commodity);
			}

		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		}finally{
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return list;
	}

	@Override
	public int updateShoppingCarListCount(Tbl_shopping_cart tbl_shopping_cart) {
		CommonUtil.logger.info("【mobile】"+"into GoodsDaoImpl,updateShoppingCarListCount");
		int rs = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("update tbl_shopping_cart set goods_count =? "
					+ "   where shopping_cart_id=? and add_user_id=?");
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1, tbl_shopping_cart.getGoods_count());
			ps.setInt(2, tbl_shopping_cart.getShopping_cart_id());
			ps.setString(3, tbl_shopping_cart.getAdd_user_id());
			rs = ps.executeUpdate();

		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		}
		return rs;
	}

	@Override
	public int DeleteShoppingCarListGoods(Tbl_shopping_cart tbl_shopping_cart) {
		CommonUtil.logger.info("【mobile】"+"into GoodsDaoImpl,DeleteShoppingCarListGoods");
		int rs = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("delete from tbl_shopping_cart  WHERE shopping_cart_id = ? and add_user_id	=? and goods_id = ?");
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1, tbl_shopping_cart.getShopping_cart_id());
			ps.setString(2, tbl_shopping_cart.getAdd_user_id());
			ps.setInt(3, tbl_shopping_cart.getGoods_id());
			rs = ps.executeUpdate();
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		}
		return rs;
	}

	@Override
	public int getShoppingCarCount(String userId) {
		CommonUtil.logger.info("【mobile】"+"into GoodsDaoImpl,getShoppingCarCount");
		int count = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("select SUM(goods_count)as goods_count from tbl_shopping_cart WHERE add_user_id=? GROUP BY add_user_id ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if(rs.next()){
                count = rs.getInt("goods_count");
			}
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());

		}finally{
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return count;
	}
	public List<Tbl_commodity_category> getClassifiedgoods() {
		List<Tbl_commodity_category> list = new ArrayList<Tbl_commodity_category>();
		 Connection con = null;
		 PreparedStatement ps = null;
	     Statement statement = null;
	     ResultSet rs  = null;
	  	try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("    SELECT category_id ");
			strSQL.append("    ,category_name ");
			strSQL.append("    FROM tbl_commodity_category ");

			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				Tbl_commodity_category category = new Tbl_commodity_category();
				category.setCategory_id(rs.getInt("category_id"));
				category.setCategory_name(rs.getString("category_name"));
				list.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
           return list;
	}
}
