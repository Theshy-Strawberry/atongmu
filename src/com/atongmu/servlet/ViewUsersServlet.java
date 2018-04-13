package com.atongmu.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.dao.GoodsDao;
import com.atongmu.dao.SaleManDao;
import com.atongmu.daoImpl.GoodsDaoImpl;
import com.atongmu.daoImpl.SaleManDaoImpl;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class ViewUsersServlet
 */
@WebServlet("/ViewUsersServlet")
public class ViewUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUsersServlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		 Object roleObj = request.getSession().getAttribute("role");
		  Object userObj = (Object)request.getSession().getAttribute("loginuser");
		  String salemanId = null;
		    int role = -1;
		    if(null != roleObj){
		    	role = (Integer)roleObj;
		        if(role == 2){
		            Tbl_saleman userinfo = (Tbl_saleman)userObj;
		            salemanId = userinfo.getSaleman_id();
		        }
		    }
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String ym = dateFormat.format(now);
		String orderDate = request.getParameter("order_date");
		if(orderDate==null){
			orderDate = ym;
		}
		SaleManDao dao  = new SaleManDaoImpl();
		List<Tbl_user> list = dao.getViewusers(orderDate, salemanId);
		request.setAttribute("viewusers", list);
		request.setAttribute("orderdate", StringUtil.nvl(orderDate));
		request.getRequestDispatcher("web_mobile/viewusers.jsp").forward(request, response);
	}

}
