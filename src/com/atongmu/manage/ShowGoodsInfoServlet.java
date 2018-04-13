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
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class ShowGoodsInfoServlet
 */
@WebServlet("/ShowGoodsInfoServlet")
public class ShowGoodsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement statement = null;
	private ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowGoodsInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String goodsId= request.getParameter("goodsId");
		List<Tbl_commodity_category> commodity_categories = new ArrayList<Tbl_commodity_category>();
		GoodsDaoImpl gdi = new GoodsDaoImpl();
		commodity_categories = gdi.getClassifiedgoods();
		request.setAttribute("commodity_categories", commodity_categories);
		
		Tbl_commodity  goodInfo = new Tbl_commodity();
		goodInfo= getGoodInfo(goodsId);
		request.setAttribute("goodInfo", goodInfo);
		List<String> pathList= new ArrayList<String>();
//		String path1="C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_1";
//		String path2="C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_2";
//		String path3="C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_3";
//		String path4="C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_4";
//		String path5="C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_5";
//		pathList.add(path1);
//		pathList.add(path2);
//		pathList.add(path3);
//		pathList.add(path4);
//		pathList.add(path5);
		request.setAttribute("pathList", pathList);
		request.getRequestDispatcher("web_browser/updategoodsinfo.jsp").forward(request,response);
	}

	private Tbl_commodity getGoodInfo(String goodsId) {
		Tbl_commodity goodInfo = new Tbl_commodity();
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer(" ");
			sql.append("SELECT                  ");
			sql.append("	goods_id,         ");
			sql.append("	goods_name,         ");
			sql.append("	goods_category_id,  ");
			sql.append("	goods_price,        ");
			sql.append("	goods_stock,        ");
			sql.append("	goods_discount,     ");
			sql.append("	goods_spec,         ");
			sql.append("	goods_keys,         ");
			sql.append("	goods_describe      ");
			sql.append("FROM                    ");
			sql.append("	tbl_commodity       ");
			sql.append("WHERE                   ");
			sql.append("	goods_id="+goodsId+"          ");
			statement = con.createStatement();
			rs = statement.executeQuery(sql.toString());
			while(rs.next()){
				goodInfo.setGoods_id(rs.getInt("goods_id"));
				goodInfo.setGoods_name(StringUtil.nvl(rs.getString(2)));
				goodInfo.setGoods_category_id(rs.getInt(3));
				goodInfo.setGoods_price(rs.getDouble(4));
				goodInfo.setGoods_stock(rs.getInt(5));
				goodInfo.setGoods_discount(rs.getDouble(6));
				goodInfo.setGoods_spec(StringUtil.nvl(rs.getString(7)));
				goodInfo.setGoods_keys(StringUtil.nvl(rs.getString(8)));
				goodInfo.setGoods_describe(StringUtil.nvl(rs.getString(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, ps);
		}
		
		return goodInfo;
	}

}
