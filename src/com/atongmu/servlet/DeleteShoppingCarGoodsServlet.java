package com.atongmu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_shopping_cart;
import com.atongmu.dao.GoodsDao;
import com.atongmu.daoImpl.GoodsDaoImpl;
import com.atongmu.util.CommonUtil;

/**
 * Servlet implementation class DeleteShoppingCarGoodsServlet
 */
@WebServlet("/DeleteShoppingCarGoodsServlet")
public class DeleteShoppingCarGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteShoppingCarGoodsServlet() {
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
		CommonUtil.logger.info("【mobile】"+"into DeleteShoppingCarGoodsServlet,doPost");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		Tbl_shopping_cart cart = new Tbl_shopping_cart();
		int shopping_cart_id = Integer.parseInt(request.getParameter("shopping_cart_id"));
	    int goods_id = Integer.parseInt(request.getParameter("goods_id"));
	    String add_user_id	=	request.getParameter("add_user_id");
        cart.setShopping_cart_id(shopping_cart_id);
        cart.setGoods_id(goods_id);
        cart.setAdd_user_id(add_user_id);
        GoodsDao goodsDao = new GoodsDaoImpl();
        try {
        	goodsDao.DeleteShoppingCarListGoods(cart);
        	CommonUtil.logger.info("【mobile】"+"用户+"+add_user_id+"从购物车中删除了商品，商品ID:"+goods_id+"。");
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
}
        response.sendRedirect("ShowShoppingCarListServlet");

	}

}
