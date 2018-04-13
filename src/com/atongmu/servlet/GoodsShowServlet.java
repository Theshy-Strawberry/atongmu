package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_commodity;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class GoodsShowServlet
 */
@WebServlet("/GoodsShowServlet")
public class GoodsShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommonUtil.logger.info("【mobile】"+"into GoodsShowServlet,doGet");
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		request.setAttribute("goodsList", selectGoods());
		request.getRequestDispatcher("web_mobile/index.jsp").forward(request,response);
	}

	private List<Tbl_commodity> selectGoods() {
		CommonUtil.logger.info("【mobile】"+"into GoodsShowServlet,selectGoods");
		// TODO Auto-generated method stub
	    Connection con = null;
        Statement statement = null;
        ResultSet rs  = null;
        List<Tbl_commodity>  goodsList = null;

		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("select goods_id, goods_name,goods_price,goods_sales_volume from tbl_commodity where goods_integral_sale is null and delete_flag = 0");
			statement = con.createStatement();
			rs = statement.executeQuery(sql.toString());
			goodsList = new ArrayList<Tbl_commodity>();
			while (rs.next()) {
			    Tbl_commodity goodsinfo = new Tbl_commodity();
				goodsinfo.setGoods_name(null);
				goodsinfo.setGoods_price(null);
				goodsinfo.setGoods_sales_volume(0);
				goodsinfo.setGoods_id(0);
				if(rs.getString("goods_name")!=null){
						goodsinfo.setGoods_name(rs.getString("goods_name"));
			     }
				if(rs.getString("goods_price")!=null){
					goodsinfo.setGoods_price(Double.valueOf(rs.getString("goods_price")));
				}
				if(rs.getString("goods_sales_volume")!=null){
					goodsinfo.setGoods_sales_volume(Integer.parseInt(rs.getString("goods_sales_volume")));
				}
				if(rs.getString("goods_id")!=null){
					goodsinfo.setGoods_id(Integer.parseInt(rs.getString("goods_id")));
				}
			    goodsList.add(goodsinfo);
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return goodsList;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
