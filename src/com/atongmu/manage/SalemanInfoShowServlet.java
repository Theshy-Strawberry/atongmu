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
import com.atongmu.bean.Tbl_order;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class SalemanInfoShowServlet
 * 销售员信息一览
 */
@WebServlet("/SalemanInfoShowServlet")
public class SalemanInfoShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalemanInfoShowServlet() {
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
		String saleman = StringUtil.nvl(request.getParameter("salemanid"));
		String weixin = StringUtil.nvl(request.getParameter("weixin"));
		String level = StringUtil.nvl(request.getParameter("sallevel")) ;
		List<List<Object>> salemanList = new ArrayList<List<Object>>();
		//除了销售额之外的
		salemanList= getSalemanInfo(saleman,weixin,level);
		request.setAttribute("saleman",saleman );
		request.setAttribute("weixin",weixin );
		request.setAttribute("level",level );
		request.setAttribute("salemanList", salemanList);
		request.getRequestDispatcher("web_browser/salemaninfo.jsp").forward(request,response);
	}


	private List<List<Object>> getSalemanInfo(String salemanid,String weixin,String level) {
		// TODO Auto-generated method stub
		List<List<Object>>  yoyo= new ArrayList<List<Object>>();
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer(" ");

        sql.append("       SELECT                                                                     ");
        sql.append("                     saleman_id,                                                  ");
        sql.append("                     reg_date,                                                    ");
        sql.append("                     weixin_id,                                                   ");
        sql.append("                     saleman_name,                                                ");
        sql.append("                     saleman_sex,                                                 ");
        sql.append("                     saleman_tel_num,                                             ");
        sql.append("                     tc1.code_value AS home_area,                                 ");
        sql.append("                     tc2.code_value AS saleman_level,                             ");
        sql.append("                     IFNULL(                                                      ");
        sql.append("                           sum(tbl_order.order_price),                            ");
        sql.append("                                   0                                              ");
        sql.append("                     ) as sum_price                                               ");
        sql.append("       FROM                                                                       ");
        sql.append("                     tbl_saleman                                                  ");
        sql.append("       LEFT JOIN tbl_order ON tbl_order.order_user IN (                           ");
        sql.append("                     SELECT                                                       ");
        sql.append("                                   user_id                                        ");
        sql.append("                     FROM                                                         ");
        sql.append("                                   tbl_user                                       ");
        sql.append("                     WHERE                                                        ");
        sql.append("                                   tbl_user.saleman_id = tbl_saleman.saleman_id   ");
        sql.append("                     UNION ALL                                                    ");
        sql.append("                                   SELECT                                         ");
        sql.append("                                                 saleman_id                       ");
        sql.append("                                   FROM                                           ");
        sql.append("                                                 tbl_saleman sub_tbl_saleman      ");
        sql.append("                                   WHERE                                          ");
        sql.append("                sub_tbl_saleman.saleman_id = tbl_saleman.saleman_id               ");
        sql.append("       ),                                                                         ");
        sql.append("       tbl_code_master AS tc1,                                                    ");
        sql.append("       tbl_code_master AS tc2                                                     ");
        sql.append("       WHERE                                                                      ");
        sql.append("                     tbl_saleman.home_area = tc1. CODE                            ");
        sql.append("       AND tbl_saleman.saleman_level = tc2. CODE                                  ");
			if(salemanid!=null && (!salemanid.equals(""))){
		       sql.append("	and saleman_id=   '"+salemanid+"'");
			}
			if(weixin!=null && (!weixin.equals(""))){
			   sql.append("	and weixin_id=   '"+weixin+"'");
			}
			if(level!=null && (!level.equals(""))){
			   sql.append(" and saleman_level=   '"+level+"'");
			}
			sql.append("       GROUP BY                                                          ");
			sql.append("                     saleman_id                                          ");
		ps = con.prepareStatement(sql.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			List<Object> sublist = new ArrayList<Object>();
			Tbl_saleman yuki = new Tbl_saleman();
			yuki.setSaleman_id(rs.getString(1));
			yuki.setReg_date(rs.getDate(2));
			yuki.setWeixin_id(rs.getString(3));
			yuki.setSaleman_name(rs.getString(4));
			yuki.setSaleman_sex(rs.getString(5));
			yuki.setSaleman_tel_num(rs.getString(6));
			Tbl_code_master code_master = new Tbl_code_master();
			code_master.setCode_value(rs.getString(7));
			Tbl_code_master code_master2 = new Tbl_code_master();
			code_master2.setCode_value(rs.getString(8));
			Tbl_order tbl_order =  new Tbl_order();
			tbl_order.setOrder_price(rs.getDouble(9));
			sublist.add(yuki);
			sublist.add(code_master);
			sublist.add(code_master2);
			sublist.add(tbl_order);
			yoyo.add(sublist);
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

}
