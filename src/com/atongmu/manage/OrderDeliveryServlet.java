package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class OrderDeliveryServlet
 * 订单发货管理
 */
@WebServlet("/OrderDeliveryServlet")
public class OrderDeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderDeliveryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 设置接收和返回的编码格式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

		request.getSession().setAttribute("orderNotPayList", getOrderNotPayList("null"));
		request.getRequestDispatcher("web_browser/orderDeliverList.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 设置接收和返回的编码格式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

		String getOrderIdSearch = request.getParameter("orderIdSearch");
		request.getSession().setAttribute("orderNotPayList", getOrderNotPayList(getOrderIdSearch));
		request.setAttribute("ordrIdSearch", getOrderIdSearch);
		request.getRequestDispatcher("web_browser/orderDeliverList.jsp").forward(
				request, response);
	}

	protected List<Map<String, Object>> getOrderNotPayList(String orderIdSeach) {
		List<Map<String, Object>> orderNotPayList = new ArrayList<Map<String, Object>>();
		try {
			String sqlGetOrderNotPay = " SELECT  "
					+ " 	orderId,  "
					+ " 	orderUser,  "
					+ " 	orderDate,  "
					+ " 	userName,  "
					+ " 	userTel,  "
					+ " 	userAddr,  "
					+ " 	orderStatus  "
					+ " FROM  "
					+ " 	(  "
					+ " 		SELECT  "
					+ " 			order_id AS orderId,  "
					+ " 			order_user AS orderUser,  "
					+ " 			order_date AS orderDate,  "
					+ " 			user_name AS userName,  "
					+ " 			user_tel_num AS userTel,  "
					+ " 			user_addr AS userAddr,  "
					+ " 			order_status AS orderStatus  "
					+ " 		FROM  "
					+ " 			tbl_order TBO  "
					+ " 		INNER JOIN tbl_user TBU  "
					+ " 		ON TBO.order_user = TBU.user_id  "
					+ " 		WHERE TBU.transfer_flag = '0'  "
					+ " 	UNION  "
					+ " 			SELECT  "
					+ " 			order_id AS orderId,  "
					+ " 			order_user AS orderUser,  "
					+ " 			order_date AS orderDate,  "
					+ " 			saleman_name AS userName,  "
					+ " 			saleman_tel_num AS userTel,  "
					+ " 			saleman_addr AS userAddr,  "
					+ " 			order_status AS orderStatus  "
					+ " 		FROM  "
					+ " 			tbl_order TBO  "
					+ " 		INNER JOIN tbl_saleman TBS  "
					+ " 		ON TBO.order_user = TBS.saleman_id  "
					+ " 	) orderInfo  "
					+ " WHERE orderStatus = 'D002'  ";
			if(!"null".equals(orderIdSeach) && !"".equals(orderIdSeach)){
				sqlGetOrderNotPay += " AND orderId = '" + orderIdSeach + "'";
			}
			sqlGetOrderNotPay += " ORDER BY orderId  ";
			con = MySQLUtil.getOnlineConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(sqlGetOrderNotPay);
			while (rs.next()) {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("orderId", rs.getString("orderId"));
				tempMap.put("orderUser", rs.getString("orderUser"));
				tempMap.put("orderDate", rs.getString("orderDate"));
				tempMap.put("userName", rs.getString("userName"));
				tempMap.put("userTel", rs.getString("userTel"));
				tempMap.put("userAddr", rs.getString("userAddr"));
				orderNotPayList.add(tempMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return orderNotPayList;
	}

}
