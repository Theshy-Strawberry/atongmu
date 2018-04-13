package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class OrderCancelServlet
 */
@WebServlet("/OrderCancelServlet")
public class OrderCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCancelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommonUtil.logger.info("【mobile】"+"into OrderCancelServlet,doGet");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

		Connection con = null;
		PreparedStatement ps = null;
		Statement statement = null;
		ResultSet rs  = null;
		// ��ȡ�û�id
		String orderId = request.getParameter("order_id");

		try {
			StringBuffer sqlUpdateOrder = new StringBuffer("UPDATE tbl_order");
			sqlUpdateOrder.append(" SET order_status = ? ");
			sqlUpdateOrder.append(" WHERE order_id = ? ");
			con = MySQLUtil.getConnection();
			ps = con.prepareStatement(sqlUpdateOrder.toString());
			ps.setString(1,"D004");
			ps.setString(2,orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			CommonUtil.logger.info("【mobile】"+e);
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		request.getRequestDispatcher("OrderShowServlet").forward(request, response);
	}

}
