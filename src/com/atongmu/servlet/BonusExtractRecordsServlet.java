package com.atongmu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_bonus_audit;
import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.dao.GoodsDao;
import com.atongmu.daoImpl.BonusDao;
import com.atongmu.daoImpl.GoodsDaoImpl;
import com.atongmu.util.CommonUtil;

/**
 * Servlet implementation class BonusExtractRecordsServlet
 */
@WebServlet("/BonusExtractRecordsServlet")
public class BonusExtractRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BonusExtractRecordsServlet() {
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
		CommonUtil.logger.info("【mobile】"+"into BonusExtractRecordsServlet,doPost");
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
                   response.sendRedirect("web_mobile/error.jsp");
                   return;
               }
           }
       CommonUtil.logger.info("【mobile】"+"用户+"+userId+"查看了佣金记录。");
       BonusDao bonusDao = new BonusDao();
       List<Tbl_bonus_audit> auditslist = bonusDao.showBonusExtractRecords(userId);
       request.setAttribute("auditslist", auditslist);
       request.getRequestDispatcher("web_mobile/bonusextractrecords.jsp").forward(request,response);

	}

}
