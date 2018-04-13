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

import com.atongmu.bean.Tbl_commodity_category;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class GetGoodsClassifiedListServlet
 */
@WebServlet("/GetGoodsClassifiedListServlet")
public class GetGoodsClassifiedListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGoodsClassifiedListServlet() {
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
		String flag = request.getParameter("hiddenflag");
		String categoryid = "";
		String categoryname = "";
		List<Tbl_commodity_category> list = new ArrayList<Tbl_commodity_category>();
		if(flag==null || flag.equals("")){
			list = getGoodsClassifiedList();
			request.setAttribute("goodsclassifiedlist", list);
		}else if(flag.equals("delete")){
			categoryid = request.getParameter("hiddenid");
			boolean result= deleteGoodsClassified(categoryid);
			if(result==true){
				list = getGoodsClassifiedList();
				String deleteflag = "delete";
				request.setAttribute("deleteflag", deleteflag);
				request.setAttribute("goodsclassifiedlist", list);
			}
		}else if(flag.equals("update")){
			categoryid = request.getParameter("hiddenid");
			categoryname = request.getParameter("categoryname");
			categoryname = new String(categoryname.getBytes("iso8859-1"),"UTF-8");
			boolean result= updateGoodsClassified(categoryid, categoryname);
			if(result==true){
				String updateflag = "update";
				request.setAttribute("updateflag", updateflag);
				list = getGoodsClassifiedList();
				request.setAttribute("goodsclassifiedlist", list);

			}
		}else if(flag.equals("added")){
			categoryname = request.getParameter("newadded");
			boolean result= addedGoodsClassified(categoryname);
			if(result==true){
				String addflag = "add";
				request.setAttribute("addflag", addflag);
				list = getGoodsClassifiedList();
				request.setAttribute("goodsclassifiedlist", list);
			}
		}
		request.getRequestDispatcher("web_browser/goodsclassifiedmanagement.jsp").forward(request,response);

	}
	private List<Tbl_commodity_category> getGoodsClassifiedList(){
            List<Tbl_commodity_category> list = new ArrayList<Tbl_commodity_category>();
		    Connection con = null;
		    PreparedStatement ps = null;
	        Statement statement = null;
	        ResultSet rs  = null;
			try {
				con = MySQLUtil.getOnlineConnection();
				StringBuffer strSQL = new StringBuffer();
				strSQL.append("   SELECT category_id,category_name from ");
				strSQL.append("   tbl_commodity_category                ");
				ps = con.prepareStatement(strSQL.toString());
				rs = ps.executeQuery();
				while(rs.next()){
					Tbl_commodity_category commodity_category = new Tbl_commodity_category();
					commodity_category.setCategory_id(rs.getInt("category_id"));
                    commodity_category.setCategory_name(rs.getString("category_name"));
					list.add(commodity_category);
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

	private boolean deleteGoodsClassified(String categoryid){
        int deletecount = 0;
		Connection con = null;
	    PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("  DELETE FROM tbl_commodity_category   ");
			strSQL.append("    WHERE category_id= '"+ categoryid+"'");
			ps = con.prepareStatement(strSQL.toString());
			deletecount = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}

	return deletecount>0?true:false;
}

	private boolean updateGoodsClassified(String categoryid,String categoryname){
        int updatecount = 0;
		Connection con = null;
	    PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("  UPDATE tbl_commodity_category SET category_name='"+categoryname+"'");
			strSQL.append("     WHERE category_id='"+ categoryid+"'");
			ps = con.prepareStatement(strSQL.toString());
			updatecount = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}

	return updatecount>=0?true:false;
}
	private boolean addedGoodsClassified(String categoryname){
        int addedcount = 0;
		Connection con = null;
	    PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs  = null;
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("  INSERT INTO tbl_commodity_category(category_name,add_date)");
			strSQL.append("   VALUES('"+categoryname+"',now())");
			ps = con.prepareStatement(strSQL.toString());
			addedcount = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}

	return addedcount>0?true:false;
}

}
