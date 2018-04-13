package com.atongmu.manage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.dao.SaleManDao;
import com.atongmu.dao.UserDao;
import com.atongmu.daoImpl.SaleManDaoImpl;
import com.atongmu.daoImpl.UpdateSomeInfo;
import com.atongmu.daoImpl.UserDaoImpl;

/**
 * Servlet implementation class SalemanTransfer
 */
@WebServlet("/SalemanTransfer")
public class SalemanTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalemanTransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UpdateSomeInfo updateSomeInfo = new UpdateSomeInfo();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");


	}

}
