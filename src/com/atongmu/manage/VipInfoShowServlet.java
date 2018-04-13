package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;

import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class VipInfoShowServlet
 */
@WebServlet("/VipInfoShowServlet")
public class VipInfoShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VipInfoShowServlet() {
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
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

		String userId=request.getParameter("userId");

		//会员信息
		List<String> vipInfo = getvipInfo(userId);
		//所属区域
		String area= getArea(userId);
		//
		area = trans(area);
//		if("M001".equals(area)){
//			area="总部";
//		}else if("M002".equals(area)){
//			area="贵州地区";
//		}else if("M003".equals(area)){
//			area="浙江地区";
//		}else if("M004".equals(area)){
//			area="新疆地区";
//		}else if("M001".equals(area)){
//			area="沈阳地区";
//		}
		vipInfo.add(StringUtil.nvl(area));
		//上级销售员的信息
		List<String> upInfo = getSaleman(userId);
		//月销售额
		if(null != upInfo && upInfo.size() !=0 ){
		Double pay= getPurchaseAmountMonth(upInfo.get(3));
		upInfo.add(String.valueOf(pay));
		request.setAttribute("upInfo", upInfo);
		}
		request.setAttribute("vipInfo", vipInfo);
		//request.setAttribute("monthPurchaseAmount", monthPurchaseAmount);
		//request.setAttribute("goodsList", goodsList);
		request.getRequestDispatcher("web_browser/vipinfoshow.jsp?type="+StringUtil.nvl(request.getParameter("type"))).forward(request,response);
	}

	private String trans(String area) {
		// TODO Auto-generated method stub
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer(" ");
			sql.append("SELECT                                     ");
			sql.append("	code_value                              ");
			sql.append("FROM                                       ");
			sql.append("	tbl_code_master                        ");
			sql.append("WHERE                                      ");
			sql.append("	code = ?                       ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, area);
			rs = ps.executeQuery();
		  	while (rs.next()) {
		  		area=rs.getString(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		MySQLUtil.closeAll(rs, sm, con, ps);
	}
		return area;
	}

	private String getArea(String userId) {
      String area=null;
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer(" ");
			sql.append("SELECT                                     ");
			sql.append("	home_area                              ");
			sql.append("FROM                                       ");
			sql.append("	tbl_saleman                            ");
			sql.append("WHERE                                      ");
			sql.append("	saleman_id = (                       ");
			sql.append("		SELECT                             ");
			sql.append("			saleman_id                     ");
			sql.append("		FROM                               ");
			sql.append("			tbl_user                       ");
			sql.append("		WHERE                              ");
			sql.append("			user_id = ?        ");
			sql.append("	)                                      ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, userId);
			rs = ps.executeQuery();
		  	while (rs.next()) {
		  		area=rs.getString(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		MySQLUtil.closeAll(rs, sm, con, ps);
	}
		return area;
	}

	private List<String> getSaleman(String userId) {
		List<String> yoyo= new ArrayList<String>();
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer(" ");
		sql.append("SELECT                                ");
		sql.append("	saleman_name,                     ");
		sql.append("	saleman_level,                    ");
		sql.append("	weixin_id,                         ");
		sql.append("	saleman_id                         ");
		sql.append("FROM                                  ");
		sql.append("	tbl_saleman                       ");
		sql.append("WHERE                                 ");
		sql.append("	saleman_id = (                    ");
		sql.append("		SELECT                        ");
		sql.append("			saleman_id                ");
		sql.append("		FROM                          ");
		sql.append("			tbl_user                  ");
		sql.append("		WHERE                         ");
		sql.append("			user_id = ?     ");
		sql.append("	)                                 ");
		ps = con.prepareStatement(sql.toString());
		ps.setString(1, userId);
		rs = ps.executeQuery();
		while (rs.next()) {
			yoyo.add(rs.getString(1));
			yoyo.add(rs.getString(2));
			yoyo.add(rs.getString(3));
			yoyo.add(rs.getString(4));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		MySQLUtil.closeAll(rs, sm, con, ps);
	}
		return yoyo;
	}

	private List<String> getvipInfo(String userId) {
		List<String> yoyo= new ArrayList<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer(" ");
			sql.append("SELECT                                                     ");
			sql.append("	a.user_id,                                             ");
			sql.append("	a.reg_date,                                            ");
			sql.append("	a.user_pwd,                                            ");
			sql.append("	a.user_integral,                                       ");
			sql.append("	a.saleman_id,                                          ");
			sql.append("	a.user_tel_num,                                        ");
			sql.append("	a.user_name,                                           ");
			sql.append("	a.weixin_id,                                         ");
			sql.append("	sum(b.order_price),                                     ");
			sql.append("	a.vip_flag                                         ");
			sql.append("FROM                                                       ");
			sql.append("	tbl_user a                                             ");
			sql.append("LEFT JOIN tbl_order b ON a.user_id = b.order_user         ");
			sql.append("WHERE                                                      ");
			sql.append("	a.user_id = ?          AND order_status NOT IN ('D001', 'D004')                       ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				yoyo.add(rs.getString(1));
				Date date= rs.getDate(2);
				String str=sdf.format(date);
				yoyo.add(str);
				yoyo.add(StringUtil.nvl(rs.getString(3)));
				yoyo.add(String.valueOf(rs.getDouble(4)));
				yoyo.add(StringUtil.nvl(rs.getString(5)));
				yoyo.add(StringUtil.nvl(rs.getString(6)));
				yoyo.add(StringUtil.nvl(rs.getString(7)));
				yoyo.add(StringUtil.nvl(rs.getString(8)));
				yoyo.add(StringUtil.nvl(rs.getString(9)));
				yoyo.add(StringUtil.nvl(rs.getString(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return yoyo;
	}

//	private List<List<String>>  getGoodsList(String userId) {
//		List<List<String>>  tempList = new  ArrayList<List<String>>();
//		try {
//			con = MySQLUtil.getOnlineConnection();
//			StringBuffer sql = new StringBuffer(" ");
//			sql.append("SELECT                                                                                ");
//			sql.append("	tbl_commodity.goods_name,                                                         ");
//			sql.append("	tbl_order_detail.goods_number                                                     ");
//			sql.append("FROM                                                                                  ");
//			sql.append("	tbl_order_detail                                                                  ");
//			sql.append("INNER JOIN tbl_commodity ON tbl_order_detail.goods_id = tbl_commodity.goods_id        ");
//			sql.append("WHERE                                                                                 ");
//			sql.append("	order_id IN (                                                                     ");
//			sql.append("		SELECT                                                                        ");
//			sql.append("			order_id                                                                  ");
//			sql.append("		FROM                                                                          ");
//			sql.append("			tbl_order                                                                 ");
//			sql.append("		WHERE                                                                         ");
//			sql.append("			order_user = 'T1000011'                                                   ");
//			sql.append("	)                                                                                 ");
//			ps.setString(1, userId);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				List<String> yoyo=new ArrayList<String>();
//				yoyo.add(rs.getString(1));
//				yoyo.add(rs.getString(2));
//				tempList.add(yoyo);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			MySQLUtil.closeAll(rs, sm, con, ps);
//		}
//		return tempList;
//	}
//
	private double getPurchaseAmountMonth(String userId) {
		String temp="0";
		Calendar calendar=  Calendar.getInstance();
		String year= String.valueOf(calendar.get(Calendar.YEAR));
		String month= String.valueOf(calendar.get(Calendar.MONTH));
		month=String.format("%02d",Integer.valueOf(month));
		String ym= year+month;
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer(" ");
			sql.append("  SELECT                      ");
			sql.append("  	IFNULL(sum(order_price),0)     ");
			sql.append("  FROM                        ");
			sql.append("  	tbl_order                 ");
			sql.append("  WHERE                       ");
			sql.append("  	order_user =?  ");
			sql.append("  AND date_format(order_date, '%Y%m') = ?   ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, userId);
			ps.setString(2, ym);
			rs = ps.executeQuery();
			while (rs.next()) {
				temp=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return Double.parseDouble(temp);
	}


}
