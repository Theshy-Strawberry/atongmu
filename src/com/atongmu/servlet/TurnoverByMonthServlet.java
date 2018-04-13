package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class TurnoverByMonth
 */
@WebServlet("/TurnoverByMonthServlet")
public class TurnoverByMonthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement statement = null;
	private ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TurnoverByMonthServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		Object roleObj = request.getSession().getAttribute("role");
		 Object userObj = (Object)request.getSession().getAttribute("loginuser");
		  String userId = null;
		    int role = -1;
		    if(null != roleObj){
		    	role = (Integer)roleObj;
		        if(role == 2){
		            Tbl_saleman userinfo = (Tbl_saleman)userObj;
		               userId = userinfo.getSaleman_id();
		        }else if(role == 1){
		        	Tbl_user tbl_user = (Tbl_user)userObj;
		        	   userId = tbl_user.getUser_id();
		        }
		    }
		String ym= request.getParameter("yearmonth");
		double profit=	getByMonthProfit(ym,userId);
		String profits=String.valueOf(profit);
		request.setAttribute("profit", profits);
		request.getRequestDispatcher("web_mobile/profitByMonth.jsp").forward(request,response);
	}
	// 获取当月收益
		private double getByMonthProfit(String yearmonth,String id) {
			double price = 0;
			try {

				con = MySQLUtil.getConnection();
				StringBuffer sql = new StringBuffer("");
			       sql.append("                                                   ");
			       sql.append("  SELECT                                           ");
			       sql.append("  	sum(order_price)                               ");
			       sql.append("  FROM                                             ");
			       sql.append("  	tbl_order                                      ");
			       sql.append("  WHERE                                            ");
			       sql.append("  	order_status = 'D005'                          ");
			       sql.append("  AND date_format(order_date, '%Y%m') =?           ");
			       sql.append("  AND order_id IN (                                ");
			       sql.append("  	SELECT                                         ");
			       sql.append("  		tbl_user.user_id                           ");
			       sql.append("  	FROM                                           ");
			       sql.append("  		tbl_user                                   ");
			       sql.append("  	WHERE                                          ");
			       sql.append("  		tbl_user.saleman_id = ?         ");
			       sql.append("  	UNION ALL                                      ");
			       sql.append("  		SELECT                                     ");
			       sql.append("  			sub_saleman.saleman_id                 ");
			       sql.append("  		FROM                                       ");
			       sql.append("  			tbl_saleman sub_saleman                ");
			       sql.append("  		WHERE                                      ");
			       sql.append("  			sub_saleman.up_saleman_id = ? ");
			       sql.append("  )                                            ");
				ps = con.prepareStatement(sql.toString());
				ps.setString(1, yearmonth);
				ps.setString(2, id);
				ps.setString(3, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					price = rs.getDouble(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				MySQLUtil.closeAll(rs, statement, con, null);
			}

			return price;
		}
}
