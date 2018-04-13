package com.atongmu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.dao.GoodsDao;
import com.atongmu.daoImpl.GoodsDaoImpl;

/**
 * Servlet implementation class GoodsDetailServlet
 */
@WebServlet("/ShowShoppingCarListServlet")
public class ShowShoppingCarListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowShoppingCarListServlet() {
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
		// TODO Auto-generated method stub
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
		        	Tbl_user tbl_user = (Tbl_user)userObj;
		        	   userId = tbl_user.getUser_id();
		        }
		    }
		GoodsDao goodsdao=new GoodsDaoImpl();
		List<Tbl_commodity> list =goodsdao.showShoppingCarList(userId);
		double totalprice =0.0;
		for(int i=0;i<list.size();i++){
		 double price= list.get(i).getGoods_price()*list.get(i).getGoods_count();
			  totalprice = totalprice + price;
		}
        request.setAttribute("totalprice", totalprice);
		request.setAttribute("shoppingcarlist", list);
		request.getRequestDispatcher("web_mobile/shopcart.jsp").forward(request,response);
	}


}
