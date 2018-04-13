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

import com.atongmu.bean.Tbl_code_master;
import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_commodity_category;
import com.atongmu.bean.Tbl_order;
import com.atongmu.bean.Tbl_user;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class OrderListShowServlet
 */
@WebServlet("/ShowUsersListServlet")
public class ShowUsersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUsersListServlet() {
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
		String userid = request.getParameter("usersid");
		String weixinid = request.getParameter("weixinid");
        List<List<Object>> userslist = new ArrayList<List<Object>>();
        String selectType = request.getParameter("type");
        userslist = getUsersList(userid, weixinid,selectType);
        request.removeAttribute("userslist");
        request.setAttribute("userslist",userslist );
        request.setAttribute("pagetype", selectType);
        if("1".equals(selectType)){
        	request.getRequestDispatcher("web_browser/touristlistshow.jsp").forward(request,response);
        }else if("2".equals(selectType)){
        	request.getRequestDispatcher("web_browser/userslistshow.jsp").forward(request,response);
        }

	}
	protected List<List<Object>> getUsersList(String userid,String weixinid,String selectType) {
		List<List<Object>> list = new ArrayList<List<Object>>();
	    Connection con = null;
		PreparedStatement ps = null;
	    Statement statement = null;
	    ResultSet rs  = null;
	  	try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("        SELECT                                      ");
			strSQL.append("       user_id,                                     ");
			strSQL.append("       reg_date,                                    ");
			strSQL.append("       user_integral,                               ");
			strSQL.append("       user_name,                                   ");
			strSQL.append("       user_tel_num,                                ");
			strSQL.append("       weixin_id,                                   ");
			strSQL.append("       saleman_id,                                  ");
			strSQL.append("       SUM(order_price) as sumprice                 ");
			strSQL.append("         from tbl_user                              ");
			strSQL.append("       Left JOIN tbl_order                          ");
			strSQL.append("       ON tbl_order.order_user = tbl_user.user_id   ");
			strSQL.append("       and order_status not in ('D001','D004')      ");
			strSQL.append("       where 1=1 and transfer_flag = 0              ");
			if(userid!=null && (!userid.equals("")))
			{
				strSQL.append("  and user_id  like '%"+userid+"%'              ");
			}
			if(weixinid!=null && (!weixinid.equals("")))
			{
				strSQL.append("  and weixin_id like '"+weixinid+"'             ");
			}
			if("1".equals(selectType)){
				strSQL.append("  and vip_flag = 0");
			}else if("2".equals(selectType)){
				strSQL.append("  and vip_flag = 1");
			}
			strSQL.append("       GROUP BY user_id order by reg_date desc                             ");
			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				List<Object> objlist = new ArrayList<Object>();
				Tbl_user tbl_user = new Tbl_user();
				Tbl_order order = new Tbl_order();
				tbl_user.setUser_id(StringUtil.nvl(rs.getString("user_id")));
				tbl_user.setReg_date(rs.getTimestamp("reg_date"));
				tbl_user.setUser_integral(rs.getDouble("user_integral"));
				tbl_user.setUser_name(StringUtil.nvl(rs.getString("user_name")));
				tbl_user.setUser_tel_num(StringUtil.nvl(rs.getString("user_tel_num")));
				tbl_user.setWeixin_id(StringUtil.nvl(rs.getString("weixin_id")));
				tbl_user.setSaleman_id(StringUtil.nvl(rs.getString("saleman_id")));
				order.setOrder_price(rs.getDouble("sumprice"));
				objlist.add(tbl_user);
				objlist.add(order);
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
