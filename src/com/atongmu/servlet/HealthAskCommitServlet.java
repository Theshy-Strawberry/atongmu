package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_health_investigation_answer;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.HibernateSessionFactory;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class HealthAskCommitServlet
 */
@WebServlet("/HealthAskCommitServlet")
public class HealthAskCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthAskCommitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		CommonUtil.logger.info("【mobile】"+"into HealthAskCommitServlet,doPost");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		int goodsid = 0;
		if(request.getParameter("asklist") != null){
			goodsid = Integer.parseInt(request.getParameter("asklist"));
		}
		String openid = (String)request.getSession().getAttribute("openid");
		java.util.Date date=new java.util.Date();
		Timestamp now=new Timestamp(date.getTime());
		Tbl_health_investigation_answer thi = new Tbl_health_investigation_answer();
		thi.setGoods_id(goodsid);
		thi.setReg_date(now);
		thi.setOpen_id(openid);
		String answer = "";
		for(int i = 1;i<=30;i++){
			answer = StringUtil.nvl(request.getParameter("ask"+i));
			switch(i){
			case 1:
				thi.setAnswer1(answer);
				break;
			case 2:
				thi.setAnswer2(answer);
				break;
			case 3:
				thi.setAnswer3(answer);
				break;
			case 4:
				thi.setAnswer4(answer);
				break;
			case 5:
				thi.setAnswer5(answer);
				break;
			case 6:
				thi.setAnswer6(answer);
				break;
			case 7:
				thi.setAnswer7(answer);
				break;
			case 8:
				thi.setAnswer8(answer);
				break;
			case 9:
				thi.setAnswer9(answer);
				break;
			case 10:
				thi.setAnswer10(answer);
				break;
			case 11:
				thi.setAnswer11(answer);
				break;
			case 12:
				thi.setAnswer12(answer);
				break;
			case 13:
				thi.setAnswer13(answer);
				break;
			case 14:
				thi.setAnswer14(answer);
				break;
			case 15:
				thi.setAnswer15(answer);
				break;
			case 16:
				thi.setAnswer16(answer);
				break;
			case 17:
				thi.setAnswer17(answer);
				break;
			case 18:
				thi.setAnswer18(answer);
				break;
			case 19:
				thi.setAnswer19(answer);
				break;
			case 20:
				thi.setAnswer20(answer);
				break;
			case 21:
				thi.setAnswer21(answer);
				break;
			case 22:
				thi.setAnswer22(answer);
				break;
			case 23:
				thi.setAnswer23(answer);
				break;
			case 24:
				thi.setAnswer24(answer);
				break;
			case 25:
				thi.setAnswer25(answer);
				break;
			case 26:
				thi.setAnswer26(answer);
				break;
			case 27:
				thi.setAnswer27(answer);
				break;
			case 28:
				thi.setAnswer28(answer);
				break;
			case 29:
				thi.setAnswer29(answer);
				break;
			case 30:
				thi.setAnswer30(answer);
				break;
			}
		}
		try{
			HibernateSessionFactory.add(thi);
		}catch(Exception e){
			CommonUtil.logger.info("【mobile】"+e.getStackTrace());
			response.sendRedirect("web_mobile/healthask.jsp?errorcode=1");
		}
		request.getSession().setAttribute("answerInfo", thi);
		response.sendRedirect("web_mobile/healthanswer.jsp?errorcode=0&hdngoodid="+goodsid);
	}

}
