package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_shopping_cart;
import com.atongmu.bean.Tbl_user;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class AddShoppingCartServlet
 */
@WebServlet("/AddShoppingCartServlet")
public class AddShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommonUtil.logger.info("【mobile】"+"into AddShoppingCartServlet,doPost");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		int goodsId=Integer.parseInt((request.getParameter("goodsid")));
		int count=Integer.parseInt((request.getParameter("count")));
		Object sessionUserid = request.getSession().getAttribute("userid");
		String id= String.valueOf(sessionUserid).toString();
		CommonUtil.logger.info("【mobile】"+"用户+"+id+"把商品"+goodsId+"加入到了购物车中。");
        Tbl_shopping_cart tbl_shopping_cart = isShoppingcarExisted(id,goodsId);
		if(tbl_shopping_cart.getGoods_id()==0 && tbl_shopping_cart.getAdd_user_id()==null){
			addShoppingCar(id,goodsId,count);
		}else{
			int oldcount = tbl_shopping_cart.getGoods_count();
			int newcount = oldcount + count;
			updateShoppingcar(id,goodsId,newcount);
		}
		response.sendRedirect("GoodsDetailServlet?id="+goodsId+"&shopcartflag=1");
	}
	private void  addShoppingCar(String userId,int goodsId,int count) {
		CommonUtil.logger.info("【mobile】"+"into AddShoppingCartServlet,addShoppingCar");
	    Connection con = null;
	     PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("INSERT INTO tbl_shopping_cart ");
			sql.append("(add_user_id, ");
			sql.append(" add_date, ");
			sql.append(" goods_id, ");
			sql.append(" goods_count) VALUES (" );
			sql.append("?,now(),?,?)");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1,userId );
			ps.setLong(2,goodsId);
			ps.setInt(3, count);
			ps.executeUpdate();

		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
	}

	private Tbl_shopping_cart isShoppingcarExisted(String userId,int goodsId){
		CommonUtil.logger.info("【mobile】"+"into AddShoppingCartServlet,isShoppingcarExisted");
		 Connection con = null;
	     PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
        Tbl_shopping_cart cart = new Tbl_shopping_cart();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("select * from tbl_shopping_cart ");
			sql.append("where goods_id = ? and add_user_id = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1,goodsId );
			ps.setString(2,userId);
			rs =  ps.executeQuery();
			if(rs.next()){
				cart.setAdd_user_id(rs.getString("add_user_id"));
				cart.setGoods_id(rs.getInt("goods_id"));
				cart.setGoods_count(rs.getInt("goods_count"));
			}

		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return cart;
	}

	private int updateShoppingcar(String userId,int goodsId,int count){
		CommonUtil.logger.info("【mobile】"+"into AddShoppingCartServlet,updateShoppingcar");
		 Connection con = null;
	     PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
        int resultcount = 0;
        try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("update tbl_shopping_cart set ");
			sql.append("add_date=now(), ");
			sql.append(" goods_count =? where add_user_id=? and goods_id=? ");
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1, count);
			ps.setString(2,userId );
			ps.setLong(3,goodsId);
			resultcount= ps.executeUpdate();
		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		   return resultcount;
	}

	private int updateCommodity(int goodsId,int goodsstock){
		CommonUtil.logger.info("【mobile】"+"into AddShoppingCartServlet,updateCommodity");
		Connection con = null;
	    PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
        int resultcount = 0;
        try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("update tbl_commodity set ");
			sql.append(" goods_stock =? where goods_id=? ");
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1, goodsstock);
			ps.setInt(2, goodsId );
			resultcount= ps.executeUpdate();
		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		   return resultcount;
	}

}
