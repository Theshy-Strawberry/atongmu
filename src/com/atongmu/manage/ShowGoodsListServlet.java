package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_commodity_category;
import com.atongmu.daoImpl.GoodsDaoImpl;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class OrderListShowServlet
 */
@WebServlet("/ShowGoodsListServlet")
public class ShowGoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowGoodsListServlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String goodsname = request.getParameter("goodsname");
		String goodsclassified = request.getParameter("classified");
		List<List<Object>> tbl_commodities = new ArrayList<List<Object>>();
		tbl_commodities = getGoodsList(goodsname, goodsclassified);
		List<Tbl_commodity_category> commodity_categories = new ArrayList<Tbl_commodity_category>();
		GoodsDaoImpl gdi = new GoodsDaoImpl();
		commodity_categories = gdi.getClassifiedgoods();
		request.setAttribute("commodity_categories", commodity_categories);
		request.setAttribute("comoditieslist", tbl_commodities);
		request.setAttribute("initgoodsname", goodsname);
		request.setAttribute("initgoodsclassified", goodsclassified);
		request.getRequestDispatcher("web_browser/goodslistshow.jsp").forward(request,response);

	}
	protected List<List<Object>> getGoodsList(String goodsname,String goodsclassified) {
		List<List<Object>> list = new ArrayList<List<Object>>();
		  Connection con = null;
		  PreparedStatement ps = null;
	      Statement statement = null;
	      ResultSet rs  = null;
	  	try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("    SELECT                                         ");
			strSQL.append("     goods_id                                      ");
			strSQL.append("    ,goods_name                                      ");
			strSQL.append("    ,tbl_commodity_category.category_name          ");
			strSQL.append("    ,tbl_code_master.code_value as goods_status    ");
			strSQL.append("    ,goods_stock                                   ");
			strSQL.append("    ,goods_price                                   ");
			strSQL.append("    ,goods_sales_volume                            ");
			strSQL.append("    FROM tbl_commodity left JOIN                  ");
			strSQL.append("    tbl_commodity_category ON                      ");
			strSQL.append("    category_id = goods_category_id                ");
			strSQL.append("    left JOIN tbl_code_master ON                  ");
			strSQL.append("    goods_status = tbl_code_master.code            ");
			if(goodsname!=null && (!goodsname.equals("")))
			{
				strSQL.append("  and goods_name like '%"+goodsname+"%'");
			}
			if(goodsclassified!=null && (!goodsclassified.equals("")))
			{
				strSQL.append("  and goods_category_id=  '"+goodsclassified+"'");
			}
			strSQL.append("   where tbl_commodity.delete_flag = '0'           ");
			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				List<Object> objlist = new ArrayList<Object>();
				Tbl_commodity commodity = new Tbl_commodity();
				Tbl_commodity_category commodity_category = new Tbl_commodity_category();
				commodity.setGoods_id(rs.getInt("goods_id"));
				commodity.setGoods_name(rs.getString("goods_name"));
				commodity.setGoods_status(rs.getString("goods_status"));
				commodity.setGoods_stock(rs.getInt("goods_stock"));
				commodity.setGoods_price(rs.getDouble("goods_price"));
				commodity.setGoods_sales_volume(rs.getInt("goods_sales_volume"));
				commodity_category.setCategory_name(rs.getString("category_name"));
				objlist.add(commodity);
				objlist.add(commodity_category);
				list.add(objlist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}

		return list;

	}

}
