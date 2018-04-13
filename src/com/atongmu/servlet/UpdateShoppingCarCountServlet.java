package com.atongmu.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_shopping_cart;
import com.atongmu.dao.GoodsDao;
import com.atongmu.daoImpl.GoodsDaoImpl;

/**
 * Servlet implementation class UpdateShoppingCarCountServlet
 */
@WebServlet("/UpdateShoppingCarCountServlet")
public class UpdateShoppingCarCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShoppingCarCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		Tbl_shopping_cart cart = new Tbl_shopping_cart();
		int shopping_cart_id = Integer.parseInt(request.getParameter("shopping_cart_id"));
	    int goods_count = Integer.parseInt(request.getParameter("goods_count")) ;
	    if(goods_count==0){
	    	 cart.setGoods_count(1);
	    	response.sendRedirect("ShowShoppingCarListServlet");
	    }else{


	    String add_user_id= request.getParameter("add_user_id");
	    cart.setShopping_cart_id(shopping_cart_id);
	    cart.setGoods_count(goods_count);
	    cart.setAdd_user_id(add_user_id);
	   GoodsDao dao = new GoodsDaoImpl();
	   try {
		   dao.updateShoppingCarListCount(cart);
	} catch (Exception e) {
		e.printStackTrace();
	}
	   response.sendRedirect("ShowShoppingCarListServlet");

	    }
	}

}
