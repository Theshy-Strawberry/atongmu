package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.util.DateUtil;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class OrderReturnServlet
 * 订单退换货
 */
@WebServlet("/OrderReturnServlet")
public class OrderReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderReturnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 设置接收和返回的编码格式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

		request.setAttribute("getReturnList", getReturnList());
		request.setAttribute("getReturnStatusList", getReturnStatusList());

		request.getRequestDispatcher("web_browser/orderReturnList.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 设置接收和返回的编码格式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

		if (!saveStatus(request)) {
			//报error
			System.out.println("error");
		}

		doGet(request,response);
	}

	protected boolean saveStatus(HttpServletRequest request) {
		boolean flag = false;
		//接收到的订单状态
		String statusIn = request.getParameter("statusIn");
		String orderId = request.getParameter("orderId");
		String returnDate = request.getParameter("returnDate");
		String toLogisticsCompany = request.getParameter("toLogisticsCompany");
		String toLogisticsNumber = request.getParameter("toLogisticsNumber");
		try {
			String updateOrderReturn = ""
					+ " UPDATE tbl_order_return "
					+ " SET return_status = " + statusIn
					+ " ,return_date = " + returnDate;
				if ("F006".equals(statusIn)) {
					updateOrderReturn += " , to_logistics_company = " + toLogisticsCompany
							+ " , to_logistics_number = " + toLogisticsNumber;
				}
					updateOrderReturn += " WHERE order_id = " + orderId;

			con = MySQLUtil.getOnlineConnection();
			con = MySQLUtil.getOnlineConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(updateOrderReturn);
			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLUtil.closeAll(rs, statement, con, null);
		}


		return flag;
	}

	protected List<Map<String, Object>> getReturnStatusList() {
		List<Map<String, Object>> returenStatusList = new ArrayList<Map<String,Object>>();

		try {
			String getStatusSql = ""
					+ " SELECT code, "
					+ " 	code_value "
					+ " FROM tbl_code_master "
					+ " WHERE code LIKE 'F%' ";
			con = MySQLUtil.getOnlineConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(getStatusSql);
			while (rs.next()) {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("code", rs.getString("code"));
				tempMap.put("codeValue", rs.getString("code_value"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLUtil.closeAll(rs, statement, con, null);
		}

		return returenStatusList;
	}

	protected List<Map<String, Object>> getReturnList() {
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		try {
			String orderReturnSql = ""
					+ " SELECT TOR.order_id, "
					+ " 	TCM.code_value, "
					+ " 	TOR.request_date, "
					+ " 	TOR.return_status, "
					+ " 	TOR.return_reason, "
					+ " 	TOR.from_logistics_company, "
					+ " 	TOR.from_logistics_number, "
					+ " 	TOR.to_logistics_company, "
					+ " 	TOR.to_logistics_number, "
					+ " 	TOR.return_date, "
					+ " 	TBU.u_name "
					+ " FROM tbl_order_return  TOR "
					+ " INNER JOIN tbl_order TBO "
					+ " ON TOR.order_id = TBO.order_id "
					+ " INNER JOIN ( "
					+ " 		SELECT user_id AS u_id, "
					+ " 		user_name AS u_name "
					+ " 		FROM tbl_user "
					+ " 	UNION "
					+ " 		SELECT saleman_id AS u_id, "
					+ " 		saleman_name AS u_name "
					+ " 		FROM tbl_saleman "
					+ " ) TBU "
					+ " ON TBU.u_id = TBO.order_user "
					+ " INNER JOIN tbl_code_master TCM "
					+ " ON TCM.code = TOR.return_status "
					+ " WHERE TOR.return_status NOT IN('F004','F007') ";
			con = MySQLUtil.getOnlineConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(orderReturnSql);
			while (rs.next()) {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("orderId", rs.getString("order_id"));
				tempMap.put("codeValue", rs.getString("code_value"));
				tempMap.put("requestDate", rs.getString("request_date"));
				tempMap.put("returnReason", rs.getString("return_reason"));
				tempMap.put("fromLogisticsCompany", rs.getString("from_logistics_company"));
				tempMap.put("fromLogisticsNumber", rs.getString("from_logistics_number"));
				tempMap.put("toLogisticsCompany", rs.getString("to_logistics_company"));
				tempMap.put("toLogisticsNumber", rs.getString("to_logistics_number"));
				tempMap.put("returnDate", rs.getString("return_date"));
				tempMap.put("userName", rs.getString("u_name"));
				returnList.add(tempMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLUtil.closeAll(rs, statement, con, null);
		}

		return returnList;
	}

}
