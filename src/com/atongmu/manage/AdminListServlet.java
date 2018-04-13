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

import com.atongmu.bean.Tbl_manager;
import com.atongmu.dao.ManagerDao;
import com.atongmu.daoImpl.BonusDao;
import com.atongmu.daoImpl.ManagerDaoImpl;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class AdminListServlet
 * 管理员一览
 */
@WebServlet("/AdminListServlet")
public class AdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//private BonusDao bonusDao = new BonusDao();

	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 设置接收和返回的编码格式
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		response.setHeader("Content-Type", "text/html;charset=UTF-8");
//
//		List<Map<String, Object>> saleInfoList = getSaleInfo("","");
//
//		request.setAttribute("saleInfoList", saleInfoList);
//
//		request.getRequestDispatcher("web_browser/saleAuditList.jsp").forward(
//				request, response);
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
		ManagerDao mangerdao = new ManagerDaoImpl();

		List<Tbl_manager> adminList = mangerdao.selectAdminManager();

		request.setAttribute("adminList", adminList);

		request.getRequestDispatcher("web_browser/adminList.jsp").forward(
				request, response);

	}


}
