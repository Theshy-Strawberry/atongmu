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
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class VipInfoUpdateServlet
 */
@WebServlet("/VipInfoUpdateServlet")
public class VipInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VipInfoUpdateServlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String userId=StringUtil.nvl(request.getParameter("userid"));
		String userpwd=StringUtil.nvl(request.getParameter("userpwd"));
		Double userintegral=Double.parseDouble(request.getParameter("userintegral"));
		String saleman=StringUtil.nvl(request.getParameter("saleman"));
		String usertelnum=StringUtil.nvl(request.getParameter("usertelnum"));
		String username=StringUtil.nvl(request.getParameter("username"));
		String weinxin=StringUtil.nvl(request.getParameter("weinxin"));
		updateVipInfo(userId,userpwd,userintegral,saleman,usertelnum,username,weinxin);
		response.sendRedirect("VipInfoShowServlet?userId="+userId);
	}

	private boolean updateVipInfo(String userId, String userpwd,
			Double userintegral, String saleman, String usertelnum,
			String username, String weinxin) {
		int result=0;
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer(" ");
			sql.append("UPDATE tbl_user       ");
			sql.append("SET user_pwd =?,      ");
			sql.append(" user_integral =?,    ");
			sql.append(" saleman_id =?,       ");
			sql.append(" user_tel_num =?,     ");
			sql.append(" user_name =?,        ");
			sql.append(" weixin_id =?         ");
			sql.append("WHERE                 ");
			sql.append("	user_id =?        ");
		ps = con.prepareStatement(sql.toString());
		ps.setString(1, userpwd);
		ps.setDouble(2, userintegral);
		ps.setString(3, saleman);
		ps.setString(4, usertelnum);
		ps.setString(5, username);
		ps.setString(6, weinxin);
		ps.setString(7, userId);
		result = ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		MySQLUtil.closeAll(rs, sm, con, ps);
	}
		return result>0 ? true : false;
		
	}

}
