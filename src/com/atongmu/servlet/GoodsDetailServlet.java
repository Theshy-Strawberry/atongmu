package com.atongmu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_shopping_cart;
import com.atongmu.bean.Tbl_user;
import com.atongmu.dao.GoodsDao;
import com.atongmu.daoImpl.GoodsDaoImpl;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class GoodsDetailServlet
 */
@WebServlet("/GoodsDetailServlet")
public class GoodsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsDetailServlet() {
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
		CommonUtil.logger.info("【mobile】"+"into GoodsDetailServlet,doPost");
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		int goodsid= Integer.parseInt(request.getParameter("id").toString());
		int shopcartflag = Integer.parseInt(StringUtil.nvl(request.getParameter("shopcartflag")));
//		int id=1;
		GoodsDao goodsdao=new GoodsDaoImpl();
		Tbl_commodity tbl_commodity=new Tbl_commodity();
		tbl_commodity.setGoods_id(goodsid);
		tbl_commodity=goodsdao.getGoodsDetail(goodsid);


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
		int usercount = goodsdao.getShoppingCarCount(userId);
		request.setAttribute("goods_name",StringUtil.nvl(tbl_commodity.getGoods_name()));
		CommonUtil.logger.info("【mobile】"+"用户"+userId+"查看商品"+tbl_commodity.getGoods_name());
		request.setAttribute("goods_price", tbl_commodity.getGoods_price());
		request.setAttribute("goods_integral", tbl_commodity.getGoods_integral());
		request.setAttribute("goods_stock",tbl_commodity.getGoods_stock());
		request.setAttribute("goods_sales_volume", tbl_commodity.getGoods_sales_volume());
		request.setAttribute("goods_count", tbl_commodity.getGoods_count());
		request.setAttribute("user_goods_count", usercount);
		request.setAttribute("goodsid", goodsid);
		request.setAttribute("goods_spec", tbl_commodity.getGoods_spec());
		request.setAttribute("goods_describe", tbl_commodity.getGoods_describe());
		request.setAttribute("goods_url", tbl_commodity.getGoods_url());
		if(shopcartflag == 0){
			//没有添加到购物车事件则跳到普通的商品显示
			request.getRequestDispatcher("web_mobile/viewgood.jsp?status=0").forward(request,response);
		}else if(shopcartflag == 1){
			//有添加到购物车事件则提示message，告诉用户添加购物车成功
			request.getRequestDispatcher("web_mobile/viewgood.jsp?status=1").forward(request,response);
		}
	}
	}
