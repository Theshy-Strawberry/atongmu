package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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
import com.atongmu.bean.Tbl_user;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class OrderListShowServlet
 */
@WebServlet("/ConfirmUpdateOrderListShowServlet")
public class ConfirmUpdateOrderListShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmUpdateOrderListShowServlet() {
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
		String orderId = request.getParameter("hiddenorderid");
		String orderUserId = request.getParameter("hiddenorderuserid");
		String orderstatus = request.getParameter("status");
	    String initorderprice = request.getParameter("totalmoney");
//	    DecimalFormat df = new DecimalFormat("######0.00");
//	    String stringorderprice = df.format(initorderprice);
	    Double orderprice = Double.parseDouble(initorderprice);
	    String logisticscompany = request.getParameter("logisticscompany");
	    String logisticsno =  request.getParameter("logisticsno");
		String consignee = request.getParameter("consignee");
		String tel = request.getParameter("tel");
		String address= request.getParameter("address");
		Tbl_order order = new Tbl_order();
		order.setOrder_id(orderId);
		order.setOrder_user(orderUserId);
		order.setOrder_status(orderstatus);
		order.setOrder_price(orderprice);
		order.setLogistics_company(logisticscompany);
		order.setLogistics_number(logisticsno);
		confirmUpdateOrder(order);
		Tbl_user tbl_user = new Tbl_user();
		tbl_user.setUser_id(orderUserId);
		tbl_user.setUser_name(consignee);
		tbl_user.setUser_tel_num(tel);
		tbl_user.setUser_addr(address);
		confirmUpdateUser(tbl_user);

		request.getRequestDispatcher("OrderListShowServlet").forward(request, response);
	}

	private int confirmUpdateOrder(Tbl_order tblorder) {
		int updatecount = 0;
		 Connection con = null;
		 PreparedStatement ps = null;
		 Statement statement = null;
		 ResultSet rs = null;
         String orderid =  tblorder.getOrder_id();
         String orderuser= tblorder.getOrder_user();
         String orderstatus= tblorder.getOrder_status();
         String logisticscompany=  tblorder.getLogistics_company();
         String logisticsnumber = tblorder.getLogistics_number();
         double orderprice =  tblorder.getOrder_price();
         try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("      UPDATE tbl_order set"                                                  );
			strSQL.append("      order_status=   '"+orderstatus+"'"                                     );
			strSQL.append("      ,order_price='"+orderprice+"'"                                         );
			strSQL.append("      ,logistics_company='"+logisticscompany+"'"                             );
			strSQL.append("      ,logistics_number='"+logisticsnumber+"'"                               );
			strSQL.append("      WHERE order_id = '"+orderid+"'"                                        );
			strSQL.append("       and order_user='"+orderuser+"'"                                       );
			ps = con.prepareStatement(strSQL.toString());
			updatecount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return updatecount;

	}

private int confirmUpdateUser(Tbl_user tbl_user) {
	int updatecount = 0;
	 Connection con = null;
	 PreparedStatement ps = null;
	 Statement statement = null;
	 ResultSet rs = null;
	 String userid = tbl_user.getUser_id();
	 String username = tbl_user.getUser_name();
	 String useraddr = tbl_user.getUser_addr();
	 String usertel = tbl_user.getUser_tel_num();
	try {
		con = MySQLUtil.getOnlineConnection();
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("      UPDATE tbl_user SET user_name = '"+username+"'"     );
		strSQL.append("      ,user_tel_num= '"+usertel+"'"                       );
		strSQL.append("      ,user_addr= '"+useraddr+"'"                         );
		strSQL.append("      WHERE user_id = '"+userid+"'"                       );
		ps = con.prepareStatement(strSQL.toString());
		updatecount = ps.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		MySQLUtil.closeAll(rs, statement, con, null);
	}
	return updatecount;
    }
}

