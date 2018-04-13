package com.atongmu.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_manager;
import com.atongmu.dao.ManagerDao;
import com.atongmu.daoImpl.ManagerDaoImpl;

/**
 * Servlet implementation class ManagerLoginServlet
 */
@WebServlet("/ManagerLoginServlet")
public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerLoginServlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		Tbl_manager select_parm = new Tbl_manager();
		select_parm.setManager_username(username);
		select_parm.setManager_password(password);
		Tbl_manager manager = new Tbl_manager();
		ManagerDao managerDao = new ManagerDaoImpl();
		manager = managerDao.selectManager(select_parm);
		if(manager == null){
			request.setAttribute("loginFail", "1");
			request.setAttribute("username", username);
			request.getRequestDispatcher("web_browser/managerlogin.jsp").forward(request,response);
		}else{
			request.getSession().setAttribute("loginuser", manager);
			response.sendRedirect("web_browser/manager.jsp");
		}

	}

}
