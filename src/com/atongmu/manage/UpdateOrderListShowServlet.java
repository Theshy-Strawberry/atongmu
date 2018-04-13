package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.atongmu.bean.Tbl_order;
import com.atongmu.bean.Tbl_order_detail;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class OrderListShowServlet
 */
@WebServlet("/UpdateOrderListShowServlet")
public class UpdateOrderListShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateOrderListShowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String orderId = request.getParameter("orderId");
		String usertype = request.getParameter("user_type");
		usertype =  new String(usertype.getBytes("iso-8859-1"),"utf-8");

		List<List<Object>> ordergoodsdetail = new ArrayList<List<Object>>();
		List<Object> orderdetail = new ArrayList<Object>();


		if("销售员".equals(usertype)){
			orderdetail = getOrderDetailSale(orderId);
			ordergoodsdetail = getOrderGoodsDetail(orderId);
			usertype="1";
		}else if("会员".equals(usertype)){
    		orderdetail = getOrderDetail(orderId);
    		ordergoodsdetail = getOrderGoodsDetail(orderId);
    		usertype="2";
		}else if("游客".equals(usertype)){
    		orderdetail = getOrderDetail(orderId);
    		ordergoodsdetail = getOrderGoodsDetail(orderId);
    		usertype="3";
		}else{
    		orderdetail = getOrderDetail(orderId);
    		ordergoodsdetail = getOrderGoodsDetail(orderId);
    		usertype="4";
		}

     	request.setAttribute("orderdetail", orderdetail);
        request.setAttribute("ordergoodsdetail", ordergoodsdetail);
        request.setAttribute("userType", usertype);
		request.getRequestDispatcher("web_browser/updateorderlist.jsp").forward(request, response);
	}

	private List<Object> getOrderDetail(String orderId) {
		Tbl_order tbl_order = new Tbl_order();
		Tbl_user tbl_user = new Tbl_user();
		List<Object> list = new ArrayList<Object>();
		 Connection con = null;
		 PreparedStatement ps = null;
		 Statement statement = null;
		 ResultSet rs = null;

		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("   select                                                 ");
			strSQL.append("   order_id,                                              ");
			strSQL.append("   order_date,                                            ");
			strSQL.append("   order_user,                                            ");
			strSQL.append("   tbl_user.user_name,                                    ");
			strSQL.append("   tbl_user.user_tel_num, 							     ");
			strSQL.append("   tbl_user.user_addr,									 ");
			strSQL.append("   order_status, 										 ");
			strSQL.append("   sum(order_price) as order_price, 					     ");
			strSQL.append("   logistics_company,								     ");
			strSQL.append("   logistics_number									     ");
			strSQL.append("    FROM tbl_order left JOIN tbl_user 					 ");
			strSQL.append("   on tbl_order.order_user = tbl_user.user_id 			 ");
			strSQL.append("    where tbl_order.order_id= '"+orderId+"'               ");
			strSQL.append("    group by order_user						             ");


			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				tbl_order.setOrder_id(StringUtil.nvl(rs.getString("order_id")));
				tbl_order.setOrder_date(rs.getTimestamp("order_date"));
				tbl_order.setOrder_user(StringUtil.nvl(rs.getString("order_user")));
				tbl_order.setOrder_status(StringUtil.nvl(rs.getString("order_status")));
				tbl_order.setOrder_price(rs.getDouble("order_price"));
				tbl_order.setLogistics_company(StringUtil.nvl(rs.getString("logistics_company")));
				tbl_order.setLogistics_number(StringUtil.nvl(rs.getString("logistics_number")));
				tbl_user.setUser_name(StringUtil.nvl(rs.getString("user_name")));
				tbl_user.setUser_tel_num(StringUtil.nvl(rs.getString("user_tel_num")));
				tbl_user.setUser_addr(StringUtil.nvl(rs.getString("user_addr")));
				list.add(tbl_user);
				list.add(tbl_order);
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
	private List<Object> getOrderDetailSale(String orderId) {
		Tbl_order tbl_order = new Tbl_order();
		Tbl_saleman tbl_saleman = new Tbl_saleman();
		List<Object> list = new ArrayList<Object>();
		Connection con = null;
		PreparedStatement ps = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("   select                                                 ");
			strSQL.append("   order_id,                                              ");
			strSQL.append("   order_date,                                            ");
			strSQL.append("   order_user,                                            ");
			strSQL.append("   tbl_saleman.saleman_name,                                    ");
			strSQL.append("   tbl_saleman.saleman_tel_num, 							     ");
			strSQL.append("   tbl_saleman.saleman_addr,									 ");
			strSQL.append("   order_status, 										 ");
			strSQL.append("   sum(order_price) as order_price, 					     ");
			strSQL.append("   logistics_company,								     ");
			strSQL.append("   logistics_number									     ");
			strSQL.append("    FROM tbl_order left JOIN tbl_saleman 					 ");
			strSQL.append("   on tbl_order.order_user = tbl_saleman.saleman_id 			 ");
			strSQL.append("    where tbl_order.order_id= '"+orderId+"'               ");
			strSQL.append("    group by order_user						             ");


			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				tbl_order.setOrder_id(StringUtil.nvl(rs.getString("order_id")));
				tbl_order.setOrder_date(rs.getTimestamp("order_date"));
				tbl_order.setOrder_user(StringUtil.nvl(rs.getString("order_user")));
				tbl_order.setOrder_status(StringUtil.nvl(rs.getString("order_status")));
				tbl_order.setOrder_price(rs.getDouble("order_price"));
				tbl_order.setLogistics_company(StringUtil.nvl(rs.getString("logistics_company")));
				tbl_order.setLogistics_number(StringUtil.nvl(rs.getString("logistics_number")));
				tbl_saleman.setSaleman_name(StringUtil.nvl(rs.getString("saleman_name")));
				tbl_saleman.setSaleman_tel_num(StringUtil.nvl(rs.getString("saleman_tel_num")));
				tbl_saleman.setSaleman_addr(StringUtil.nvl(rs.getString("saleman_addr")));
				list.add(tbl_saleman);
				list.add(tbl_order);
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

	private List<List<Object>> getOrderGoodsDetail(String orderId) {
		List<List<Object>> list = new ArrayList<List<Object>>();

		 Connection con = null;
		 PreparedStatement ps = null;
		 Statement statement = null;
		 ResultSet rs = null;
		try {
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("   SELECT                                                                      ");
			strSQL.append("   tbl_order_detail.goods_id,                                                  ");
			strSQL.append("   tbl_commodity.goods_name,                                                   ");
			strSQL.append("   tbl_commodity.goods_price,                                                  ");
			strSQL.append("   tbl_order_detail.goods_number                                               ");
			strSQL.append("    from tbl_order_detail INNER JOIN tbl_commodity ON                          ");
			strSQL.append("    tbl_order_detail.goods_id = tbl_commodity.goods_id                         ");
			strSQL.append("   WHERE tbl_order_detail.order_id = '"+orderId+"'                             ");
			con = MySQLUtil.getOnlineConnection();
			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				List<Object> tempList= new  ArrayList<Object>();
				Tbl_commodity commodity = new Tbl_commodity();
				Tbl_order_detail detail = new Tbl_order_detail();
				detail.setGoods_id(rs.getString("goods_id"));
				commodity.setGoods_name(rs.getString("goods_name"));
				commodity.setGoods_price(rs.getDouble("goods_price"));
				detail.setGoods_number(rs.getInt("goods_number"));
				tempList.add(commodity);
				tempList.add(detail);
				list.add(tempList);
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
