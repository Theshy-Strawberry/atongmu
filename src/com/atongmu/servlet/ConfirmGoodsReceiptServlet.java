package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class AddShoppingCartServlet
 */
@WebServlet("/ConfirmGoodsReceiptServlet")
public class ConfirmGoodsReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmGoodsReceiptServlet() {
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
		CommonUtil.logger.info("【mobile】"+"into ConfirmGoodsReceiptServlet,doPost");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String orderId=StringUtil.nvl(request.getParameter("order_id"));
		confirmGoodsReceiptServlet(orderId);
		CommonUtil.logger.info("【mobile】"+"订单ID:+"+orderId+"已确认收货。");
		response.sendRedirect("GoodsShowServlet");
	}
	private void  confirmGoodsReceiptServlet(String orderId) {
		CommonUtil.logger.info("【mobile】"+"into ConfirmGoodsReceiptServlet,confirmGoodsReceiptServlet");
		// TODO Auto-generated method stub
	    Connection con = null;
	     PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("UPDATE tbl_order SET ");
			sql.append("(order_status=4");
			sql.append(" WHERE order_id = ?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1,orderId);
			ps.executeUpdate();

		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
	}

}
