package com.atongmu.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Bonus_bean;
import com.atongmu.daoImpl.BonusDao;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class ShowBonusServlet
 */
@WebServlet("/ShowBonusServlet")
public class ShowBonusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBonusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("this is ShowBonusServlet");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String saleman_ID = (String)request.getSession().getAttribute("userid");
		int userType = Integer.parseInt(request.getSession().getAttribute("role").toString());
		if(userType != 2){
			//非销售无权限进行该项操作
			response.sendRedirect("web_mobile/error.jsp");
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String currentMonth = sdf.format(new Date());
		BonusDao bonusDao = new BonusDao();
		Bonus_bean bonus_bean = null;
		bonus_bean = bonusDao.select_bouns(saleman_ID, currentMonth);
		request.setAttribute("currentMonth", currentMonth);
		request.setAttribute("bonus_bean", bonus_bean);
		if(request.getParameter("updateResult") != null){
			int updateResult = Integer.parseInt(StringUtil.nvl(request.getParameter("updateResult")));
			if(updateResult == 1){
				request.getRequestDispatcher("web_mobile/registerbonus.jsp?updateResult=1").forward(request,response);
				return;
			}else if(updateResult == -1){
				request.getRequestDispatcher("web_mobile/registerbonus.jsp?updateResult=-1").forward(request,response);
				return;
			}
		}
		request.getRequestDispatcher("web_mobile/registerbonus.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("this is ShowBonusServlet post method");
	}

}
