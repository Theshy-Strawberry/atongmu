package com.atongmu.manage;

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

import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_shopping_cart;
import com.atongmu.bean.Tbl_user;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class AddAdminServlet
 */
@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommonUtil.logger.info("【mobile】"+"into AddAdminServlet,doPost");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		
		String username = request.getParameter("weixinid");
		String password = request.getParameter("password");
		String name = request.getParameter("username");
		String teleno = request.getParameter("telphone");
		String role = request.getParameter("role");
		String homearea = request.getParameter("homearea");
		
		
        Connection con = null;
	    PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
        
        int count = isweixinidExisted(username);
        if(count != 0){
        	request.setAttribute("weixinid", username);
        	request.setAttribute("password", password);
        	request.setAttribute("username", name);
        	request.setAttribute("telphone", teleno);
        	request.setAttribute("role", role);
        	request.setAttribute("homearea", homearea);
        	request.setAttribute("flag", "exist");
        	request.getRequestDispatcher("web_browser/addadmin.jsp").forward(request,response);;
        	
        }else{
        	try {
        		con = MySQLUtil.getConnection();
        		StringBuffer sql = new StringBuffer();
        		sql.append("    INSERT INTO tbl_manager (    ");
        		sql.append("    	manager_username,         ");
        		sql.append("    	manager_password,         ");
        		sql.append("    	manager_name,             ");
        		sql.append("    	manager_telno,            ");
        		sql.append("    	manager_role,             ");
        		sql.append("    	manager_home_area,        ");
        		sql.append("    	join_date                 ");
        		sql.append("    )                            ");
        		sql.append("    VALUES                       ");
        		sql.append("    	(?,?,?,?,?,?,?)           ");
        		ps = con.prepareStatement(sql.toString());
        		ps.setString(1, username );
        		ps.setString(2, password);
        		ps.setString(3, name);
        		ps.setString(4, teleno);
        		ps.setString(5, role);
        		ps.setString(6, homearea);
        		ps.setTimestamp(7, new Timestamp(new Date().getTime()));
        		ps.executeUpdate();
        		
        	} catch (SQLException e) {
        		CommonUtil.logger.error(e.getMessage());
        	} catch (Exception e) {
        		CommonUtil.logger.error(e.getMessage());
        	} finally {
        		MySQLUtil.closeAll(rs, statement, con, null);
        	}
        	request.setAttribute("flag", "notexist");
        	response.sendRedirect("AdminListServlet");
        }
        
	}

	private int isweixinidExisted(String weixinid){
		Connection con = null;
	    PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
        int count = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("select count(*) as count from tbl_manager ");
			sql.append(" where tbl_manager.manager_username= ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1,weixinid );
			rs =  ps.executeQuery();
			
			if(rs.next()){
			   count = rs.getInt("count");
			}

		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return count;
	}



}
