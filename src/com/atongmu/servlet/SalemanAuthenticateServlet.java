package com.atongmu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.dao.SaleManDao;
import com.atongmu.daoImpl.SaleManDaoImpl;

/**
 * Servlet implementation class SalemanAuthenticateServlet
 */
@WebServlet("/SalemanAuthenticateServlet")
public class SalemanAuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalemanAuthenticateServlet() {
        super();
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
	    response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        int userType = Integer.parseInt(request.getSession().getAttribute("role").toString());
		if(userType != 1){
			//非会员无权限进行该项操作
			response.sendRedirect("web_mobile/error.jsp");
			return;
		}
        Object sessionOpenid = request.getSession().getAttribute("openid");
        String openId= String.valueOf(sessionOpenid).toString();
        String userName = request.getParameter("user_name");
        String telNum = request.getParameter("telnumber");
        String weChatNo = request.getParameter("wechat");
        SaleManDao saleManDao = new SaleManDaoImpl();
        String salesmanId = saleManDao.getSalemanAuthenticateId(userName,telNum,weChatNo);
        if(salesmanId==null || salesmanId.equals("")){
          //更新失败
            response.sendRedirect("web_mobile/salemanAuthenticate.jsp?errorcode=-1");
            return;
        }else{
            boolean  firstflag =  saleManDao.updateSalemanOpenId(openId,salesmanId);
          if(firstflag == true){
             boolean secondflag =  saleManDao.updateSalemanTransferflag(openId);
             if(secondflag == true){
                 response.sendRedirect("web_mobile/salemanAuthenticate.jsp?errorcode=0");
                 return;
             }
          }
        }
	}
}
