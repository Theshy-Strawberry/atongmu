package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.daoImpl.BonusDao;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.DateUtil;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class GoodsDeleteServlet
 * 商品删除
 */
@WebServlet("/GoodsDeleteServlet")
public class GoodsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BonusDao bonusDao = new BonusDao();

	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsDeleteServlet() {
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
		String goodsId=request.getParameter("goods_id");
		
		try {
			StringBuffer sqlUpdateGoods = new StringBuffer("UPDATE tbl_commodity");
			sqlUpdateGoods.append(" SET delete_flag = '1' ");
			sqlUpdateGoods.append(" WHERE goods_id = ? ");
			con = MySQLUtil.getConnection();
			ps = con.prepareStatement(sqlUpdateGoods.toString());
			ps.setString(1,goodsId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		request.getRequestDispatcher("ShowGoodsListServlet").forward(request, response);
		
		
	}



}
