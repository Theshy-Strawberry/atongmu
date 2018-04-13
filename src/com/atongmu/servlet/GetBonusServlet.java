package com.atongmu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Bonus_bean;
import com.atongmu.bean.Tbl_bonus_audit;
import com.atongmu.daoImpl.BonusDao;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class GetBonusServlet
 */
@WebServlet("/GetBonusServlet")
public class GetBonusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBonusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		CommonUtil.logger.info("【mobile】"+"into GetBonusServlet,doPost");
		try{
			int userType = Integer.parseInt(request.getSession().getAttribute("role").toString());
			if(userType != 2){
				//非销售无权限进行该项操作
				response.sendRedirect("web_mobile/error.jsp");
				return;
			}
			int shoriType = Integer.parseInt((String)request.getParameter("shoriType"));
			BonusDao bonusDao = new BonusDao();
			if(shoriType == 1){
				//提取佣金的场合
				Tbl_bonus_audit bonus_audit = new Tbl_bonus_audit();
				bonus_audit.setReq_user_id((String)request.getSession().getAttribute("userid"));
				bonus_audit.setReq_bouns(Double.parseDouble((String)request.getParameter("regBonus")));
				CommonUtil.logger.info("【mobile】"+"用户+"+(String)request.getSession().getAttribute("userid")+"选择了提取佣金");
				bonus_audit.setReq_status("J003");//审核中
				int resultCount = bonusDao.get_bonus(bonus_audit);
				if(resultCount > 0){
					//更新成功后页面跳转
					response.sendRedirect("ShowBonusServlet?updateResult=1");
				}
			}else if(shoriType == 2){
				//查询佣金的场合
				Bonus_bean bonus_bean = null;
				String month = StringUtil.nvl(request.getParameter("month"));
				bonus_bean = bonusDao.select_bouns((String)request.getSession().getAttribute("userid"), month);
				CommonUtil.logger.info("【mobile】"+"用户+"+(String)request.getSession().getAttribute("userid")+"选择了查询"+month+"月份的佣金");
				request.setAttribute("currentMonth", month);
				request.setAttribute("bonus_bean", bonus_bean);
				request.getRequestDispatcher("web_mobile/registerbonus.jsp").forward(request,response);
			}
		}catch(Exception e){
			e.printStackTrace();
			//出错就跳error jsp
			response.sendRedirect("web_mobile/error.jsp");
			return;
		}
	}

}
