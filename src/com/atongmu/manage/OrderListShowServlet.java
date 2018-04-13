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

import com.atongmu.bean.Tbl_order;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class OrderListShowServlet
 * 订单一览表示
 */
@WebServlet("/OrderListShowServlet")
public class OrderListShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderListShowServlet() {
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
		String flag = request.getParameter("flag");
		String orderno = request.getParameter("orderno");
		String ordername = request.getParameter("ordername");
		String orderstatus = request.getParameter("status");
		List<Tbl_order> orderlist = new ArrayList<Tbl_order>();
		if(flag==null || flag.equals("")){
		orderlist = getOrderShowList(orderno, ordername, orderstatus);

		}else if(flag.equals("1")){
			String orderId = request.getParameter("orderId");
			boolean result = closeOrderList(orderId);
			if(result == true){
				orderlist = getOrderShowList(orderno, ordername, orderstatus);
			}
		}
		request.setAttribute("noflag",StringUtil.nvl(orderno));
		request.setAttribute("nameflag",StringUtil.nvl(ordername));
		request.setAttribute("statusflag",orderstatus );

		request.setAttribute("orders_list", orderlist);
		request.getRequestDispatcher("web_browser/orderListshow.jsp").forward(request,response);

	}
	@SuppressWarnings("null")
	private List<Tbl_order> getOrderShowList(String orderId, String orderPersonId,String orderStatus){
            List<Tbl_order> list = new ArrayList<Tbl_order>();
		    Connection con = null;
		    PreparedStatement ps = null;
	        Statement statement = null;
	        ResultSet rs  = null;
			try {
				con = MySQLUtil.getOnlineConnection();
				StringBuffer strSQL = new StringBuffer();
				strSQL.append("    SELECT order_id,                                    ");
				strSQL.append("       order_user,                                      ");
				strSQL.append("       tbl_user.user_name,                              ");
				strSQL.append("       tbl_user.vip_flag,                              ");
				strSQL.append("       tbl_saleman.saleman_name,                         ");
				strSQL.append("       order_date,                                      ");
				strSQL.append("       tbl_code_master.code_value as order_status,      ");
				strSQL.append("       order_price,payment_method,                      ");
				strSQL.append("       logistics_company,                               ");
				strSQL.append("       use_integral,                     	          ");
				strSQL.append("       logistics_number	                               ");
				strSQL.append("   from tbl_order INNER JOIN                            ");
				strSQL.append("       tbl_code_master on                               ");
				strSQL.append("       tbl_order.order_status	= tbl_code_master.code	");
				strSQL.append("   left JOIN tbl_user on user_id = order_user ");
				strSQL.append("   left JOIN tbl_saleman on tbl_saleman.saleman_id = order_user ");
				strSQL.append("    where 1=1");
				if(orderId!=null && (!orderId.equals("")))
				{
					strSQL.append("  and order_id=  "+orderId);
				}
				if(orderPersonId!=null && (!orderPersonId.equals("")) ){
					strSQL.append("  and order_user=  "+orderPersonId);
				}
				if(orderStatus!=null && (!orderStatus.equals(""))){
					strSQL.append("  and tbl_order.order_status =  "+"'"+orderStatus+"'");
				}
				strSQL.append("    order by  order_date desc");
				ps = con.prepareStatement(strSQL.toString());
				rs = ps.executeQuery();
				while(rs.next()){
					Tbl_order order = new Tbl_order();
					order.setOrder_id(StringUtil.nvl(rs.getString("order_id")) );
					order.setOrder_user(StringUtil.nvl(rs.getString("order_user")));
					order.setOrder_date(rs.getTimestamp("order_date"));
					order.setOrder_status(StringUtil.nvl(rs.getString("order_status")));
					order.setOrder_price(rs.getDouble("order_price"));
					order.setUse_integral(rs.getDouble("use_integral"));
					order.setPayment_method(StringUtil.nvl(rs.getString("payment_method")));
					order.setLogistics_company(StringUtil.nvl(rs.getString("logistics_company")));
                    order.setLogistics_number(StringUtil.nvl(rs.getString("logistics_number")));

                    if("S".equals(order.getOrder_user().substring(0, 1))){
                    	order.setUserType("销售员");
                    	order.setUser_name(StringUtil.nvl(rs.getString("saleman_name")));
                    }else if("U".equals(order.getOrder_user().substring(0, 1))){

                    	String userrole = StringUtil.nvl(rs.getString("vip_flag"));
                    	if("1".equals(userrole)){
                    		order.setUserType("会员");
                    	}else if("0".equals(userrole)){
                    		order.setUserType("游客");
                    	}
                    	order.setUser_name(StringUtil.nvl(rs.getString("user_name")));
                    }else{
                    	order.setUserType("其他用户");
                    	order.setUser_name(StringUtil.nvl(rs.getString("user_name")));
                    }
                    list.add(order);
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

	private boolean closeOrderList(String orderId){
		int updatecount = 0;
		 Connection con = null;
		 PreparedStatement ps = null;
	     Statement statement = null;
	     ResultSet rs  = null;
	     try {
	    	 con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("  update                  ");
			strSQL.append("  tbl_order  set          ");
			strSQL.append("  order_status='D004'     ");
			strSQL.append("  where order_id= '"+ orderId+"'");
			ps = con.prepareStatement(strSQL.toString());
			updatecount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return updatecount>0?true:false;
	}

}
