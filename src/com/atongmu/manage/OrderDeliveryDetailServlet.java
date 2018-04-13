package com.atongmu.manage;

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

import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class OrderDeliveryDetailServlet
 * 订单发货详情
 */
@WebServlet("/OrderDeliveryDetailServlet")
public class OrderDeliveryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDeliveryDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
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
		// 订单ID
		String orderId = request.getParameter("orderId");
		// 物流公司
		String logisticeCom = request.getParameter("logisticsCom");
		// 物流单号
		String logisticsNum = request.getParameter("logisticsNum");
		// 物流价格
		String logisticsPrice = request.getParameter("logisticsPrice");

		try {
			StringBuffer sql = new StringBuffer(" UPDATE  tbl_order ");
			sql.append(" SET logistics_company = ? ");
			sql.append(" , logistics_number = ? ");
			sql.append(" , logistics_price = ? ");
			sql.append(" , order_status = ? ");
			sql.append(" WHERE order_id = ? ");
			con = MySQLUtil.getOnlineConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, logisticeCom);
			ps.setString(2, logisticsNum);
			ps.setString(3, logisticsPrice);
			ps.setString(4, "D003");
			ps.setString(5, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		request.getSession().removeAttribute("orderNotPayList");
		request.getRequestDispatcher("OrderDeliveryServlet").forward(request, response);
	}

}
