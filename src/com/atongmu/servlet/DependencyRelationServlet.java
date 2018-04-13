package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class DependencyRelationServlet
 */
@WebServlet("/DependencyRelationServlet")
public class DependencyRelationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement statement = null;
	private ResultSet rs = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DependencyRelationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommonUtil.logger.info("【mobile】"+"into DependencyRelationServlet,doPost");
		response.setCharacterEncoding("UTF-8");
 		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//从session中取出用户信息
		 Object roleObj = request.getSession().getAttribute("role");
		 Object userObj = (Object)request.getSession().getAttribute("loginuser");
		  String userId = null;
		  String salemanevel=null;
		    int role = -1;
		    if(null != roleObj){
		    	role = (Integer)roleObj;
		        if(role == 2){
		            Tbl_saleman userinfo = (Tbl_saleman)userObj;
		               userId = userinfo.getSaleman_id();
		               salemanevel=userinfo.getSaleman_level();
		        }else if(role == 1){
		        	Tbl_user tbl_user = (Tbl_user)userObj;
		        	   userId = tbl_user.getUser_id();
		        }
		    }

		    //主管经理
		   // List<List<String>> supervisorManager=new ArrayList<List<String>>();
		    //高级销售经理
		    List<List<String>> seniorSalesManager = new ArrayList<List<String>>();
		    //销售经理
		    List<List<String>> salesManager = new ArrayList<List<String>>();
		    //销售员
		    List<List<String>> salesPerson = new ArrayList<List<String>>();
		    //xiaji销售员
		    List<List<String>> salesPerson2 = new ArrayList<List<String>>();
		    if(userId!=null||!"".equals(userId)){
		 // 开始判断登录用户等级  分开操作
		    //销售员
		    if("I002".equals(salemanevel)){
		    	List<String> idList=new ArrayList<String>();
		    	List<String> nameList=new ArrayList<String>();
		    	List<String> mouthretailList=new ArrayList<String>();
		    		//上级
		    	String leaderId=upSaleMan(userId);
		    	String leaderName=getName(userId);
		    	request.setAttribute("leaderId", leaderId);
				request.setAttribute("leaderName", leaderName);
				 List<List<String>> tempList=downIdName(userId);
				 for ( List<String> list:tempList){
					 idList.add(list.get(0));
					 nameList.add(list.get(1));
				 }
				 mouthretailList=getMonthProfitPerson(userId);
				 salesPerson2.add(idList);
				 salesPerson2.add(nameList);
				 salesPerson2.add(mouthretailList);
				 request.setAttribute("salesPerson2", salesPerson2);
		    }
		    //销售经理
		     else if("I003".equals(salemanevel)){

			    	List<String> idList=new ArrayList<String>();
			    	List<String> nameList=new ArrayList<String>();
			    	List<String> mouthretailList=new ArrayList<String>();
			    		//上级
			    	String leaderId=upSaleMan(userId);
			    	String leaderName=getName(userId);
			    	request.setAttribute("leaderId", leaderId);
					request.setAttribute("leaderName", leaderName);
					 List<List<String>> tempList=downIdName(userId);
					 for ( List<String> list:tempList){
						 idList.add(list.get(0));
						 nameList.add(list.get(1));
					 }
					 mouthretailList=getMonthProfitPerson(userId);
					 salesPerson.add(idList);
					 salesPerson.add(nameList);
					 salesPerson.add(mouthretailList);
					 request.setAttribute("salesPerson", salesPerson);
		    }
		     //高级销售经理
		     else if("I004".equals(salemanevel)){
		    		List<String> idList=new ArrayList<String>();
			    	List<String> nameList=new ArrayList<String>();
			    	List<String> mouthretailList=new ArrayList<String>();
			    	List<String> teamCountList=new ArrayList<String>();
			    	List<String> teamProfitList=new ArrayList<String>();
			    		//上级
			    	String leaderId=upSaleMan(userId);
			    	String leaderName=getName(userId);
			    	request.setAttribute("leaderId", leaderId);
					request.setAttribute("leaderName", leaderName);
					//我的下级  销售经理
					 List<List<String>> jingLiList=downIdName2(userId);
					 for ( List<String> list:jingLiList){
						 idList.add(list.get(0));
						 nameList.add(list.get(1));
					 }
					 //我的下属团队人数
					 teamCountList= teamCount(userId);
					 //收益哟
					 teamProfitList=  teamProfit(userId);
					 salesManager.add(idList);
					 salesManager.add(nameList);
					 salesManager.add(teamCountList);
					 salesManager.add(teamProfitList);

					 idList.clear();
					 nameList.clear();
					 List<List<String>> xiaoShouYuanLiList=downIdName(userId);
					 for ( List<String> list:xiaoShouYuanLiList){
						 idList.add(list.get(0));
						 nameList.add(list.get(1));
					 }
					 mouthretailList=getMonthProfitPerson(userId);
					 salesPerson.add(idList);
					 salesPerson.add(nameList);
					 salesPerson.add(mouthretailList);
					    request.setAttribute("salesManager", salesManager);
					    request.setAttribute("salesPerson", salesPerson);
		     }
		     //主管经理
		     else if("I005".equals(salemanevel)){

		    		List<String> idList=new ArrayList<String>();
			    	List<String> nameList=new ArrayList<String>();
			    	List<String> mouthretailList=new ArrayList<String>();
			    	List<String> teamCountList=new ArrayList<String>();
			    	List<String> idList2=new ArrayList<String>();
			    	List<String> nameList2=new ArrayList<String>();
			    	List<String> mouthretailList2=new ArrayList<String>();
			    	List<String> teamCountList2=new ArrayList<String>();
			    	List<String> teamProfitList=new ArrayList<String>();
			    	List<String> teamProfitList2=new ArrayList<String>();
			    	//   	 //高级销售经理层
		    	 List<List<String>> gaoJiJingLiList=downIdName3(userId);
		    	 for ( List<String> list:gaoJiJingLiList){
					 idList.add(list.get(0));
					 nameList.add(list.get(1));
				 }
		    	 //我的下属团队人数
				 teamCountList= teamCount2(userId);
				 //收益哟
				 teamProfitList=  teamProfit2(userId);
				 seniorSalesManager.add(idList);
				 seniorSalesManager.add(nameList);
				 seniorSalesManager.add(teamCountList);
				 seniorSalesManager.add(teamProfitList);
				 // 销售经理层
				 List<List<String>> xiaoShouJingLiList=downIdName2(userId);
				 for ( List<String> list:xiaoShouJingLiList){
					 idList2.add(list.get(0));
					 nameList2.add(list.get(1));
				 }
				 teamCountList2= teamCount(userId);
				 teamProfitList2=  teamProfit(userId);
				 salesManager.add(idList2);
				 salesManager.add(nameList2);
				 salesManager.add(teamCountList2);
				 salesManager.add(teamProfitList2);
				 //销售员层
				 idList.clear();
				 nameList.clear();
			   	 List<List<String>> xiaoShouYuanLiList=downIdName3(userId);
				 for ( List<String> list:xiaoShouYuanLiList){
					 idList.add(list.get(0));
					 nameList.add(list.get(1));
				 }
				 mouthretailList=getMonthProfitPerson(userId);
				 salesPerson.add(idList);
				 salesPerson.add(nameList);
				 salesPerson.add(mouthretailList);
				    request.setAttribute("seniorSalesManager", seniorSalesManager);
				    request.setAttribute("salesManager", salesManager);
				    request.setAttribute("salesPerson", salesPerson);
		    }

}
//		    request.setAttribute("seniorSalesManager", seniorSalesManager);
//		    request.setAttribute("salesManager", salesManager);
//		    request.setAttribute("salesPerson", salesPerson);
	//	    request.setAttribute("salesPerson2", salesPerson2);
			request.getRequestDispatcher("web_mobile/dependencyrelation.jsp").forward(request, response);
	}

//销售经理的佣金
	private List<String> teamProfit(String userId) {
		 List<String> teamProfit = new  ArrayList<String>();
			try {
				con = MySQLUtil.getConnection();
				StringBuffer sql = new StringBuffer("");
                sql.append("SELECT                                                 ");
                sql.append("	sum(order_price),                                     ");
                sql.append("	temp.id                                               ");
                sql.append("FROM                                                   ");
                sql.append("	tbl_order                                             ");
                sql.append("INNER JOIN (                                           ");
                sql.append("	SELECT                                                ");
                sql.append("		tbl_user.user_id,                                 ");
                sql.append("		user_info.user_id AS id                           ");
                sql.append("	FROM                                                  ");
                sql.append("		(                                                 ");
                sql.append("			SELECT                                        ");
                sql.append("				saleman_id AS user_id                     ");
                sql.append("			FROM                                          ");
                sql.append("				tbl_saleman                               ");
                sql.append("			WHERE                                         ");
                sql.append("				up_saleman_id = ?              ");
                sql.append("			AND tbl_saleman.revoke_date IS NULL       and  tbl_saleman.saleman_level='I003'      ");
                sql.append("		) user_info,                                      ");
                sql.append("		tbl_user                                          ");
                sql.append("	WHERE                                                 ");
                sql.append("		tbl_user.saleman_id = user_info.user_id           ");
                sql.append(") AS temp ON tbl_order.order_user = temp.user_id       ");
                sql.append("WHERE                                                  ");
                sql.append("	order_status = 'D005'                                 ");
                sql.append("GROUP BY                                               ");
                sql.append("	temp.id                                               ");
				ps = con.prepareStatement(sql.toString());
				ps.setString(1, userId);
				rs = ps.executeQuery();
				while (rs.next()) {
					teamProfit.add(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				MySQLUtil.closeAll(rs, statement, con, null);
			}

		return teamProfit;
	}
//高级销售经理的佣金
	private List<String> teamProfit2(String userId) {
		List<String> teamProfit = new  ArrayList<String>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append("SELECT                                                 ");
			sql.append("	sum(order_price),                                     ");
			sql.append("	temp.id                                               ");
			sql.append("FROM                                                   ");
			sql.append("	tbl_order                                             ");
			sql.append("INNER JOIN (                                           ");
			sql.append("	SELECT                                                ");
			sql.append("		tbl_user.user_id,                                 ");
			sql.append("		user_info.user_id AS id                           ");
			sql.append("	FROM                                                  ");
			sql.append("		(                                                 ");
			sql.append("			SELECT                                        ");
			sql.append("				saleman_id AS user_id                     ");
			sql.append("			FROM                                          ");
			sql.append("				tbl_saleman                               ");
			sql.append("			WHERE                                         ");
			sql.append("				up_saleman_id = ?              ");
			sql.append("			AND tbl_saleman.revoke_date IS NULL       and  tbl_saleman.saleman_level='I004'      ");
			sql.append("		) user_info,                                      ");
			sql.append("		tbl_user                                          ");
			sql.append("	WHERE                                                 ");
			sql.append("		tbl_user.saleman_id = user_info.user_id           ");
			sql.append(") AS temp ON tbl_order.order_user = temp.user_id       ");
			sql.append("WHERE                                                  ");
			sql.append("	order_status = 'D005'                                 ");
			sql.append("GROUP BY                                               ");
			sql.append("	temp.id                                               ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				teamProfit.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}

		return teamProfit;
	}

	private List<String> teamCount(String userId) {
		 List<String> teamCount = new  ArrayList<String>();
			try {
				con = MySQLUtil.getConnection();
				StringBuffer sql = new StringBuffer("");
                sql.append(" SELECT                                              ");
                sql.append(" 	count(tbl_saleman.saleman_id)        as count                 ");
                sql.append(" FROM                                                ");
                sql.append(" 	(                                                ");
                sql.append(" 		SELECT                                       ");
                sql.append(" 			saleman_id AS user_id                    ");
                sql.append(" 		FROM                                         ");
                sql.append(" 			tbl_saleman                              ");
                sql.append(" 		WHERE                                        ");
                sql.append(" 			up_saleman_id = ?               ");
                sql.append(" 		AND tbl_saleman.revoke_date IS NULL   and tbl_saleman.saleman_level='I002'       ");
                sql.append(" 	) user_info,                                     ");
                sql.append(" 	tbl_saleman                                      ");
                sql.append(" WHERE                                               ");
                sql.append(" 	user_info.user_id = tbl_saleman.up_saleman_id    ");
                sql.append(" 	GROUP BY	up_saleman_id   ");
				ps = con.prepareStatement(sql.toString());
				ps.setString(1, userId);
				rs = ps.executeQuery();
				while (rs.next()) {
					teamCount.add(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				MySQLUtil.closeAll(rs, statement, con, null);
			}
		return teamCount;
	}
	private List<String> teamCount2(String userId) {
		List<String> teamCount = new  ArrayList<String>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT                                              ");
			sql.append(" 	count(tbl_saleman.saleman_id)                         ");
			sql.append(" FROM                                                ");
			sql.append(" 	(                                                ");
			sql.append(" 		SELECT                                       ");
			sql.append(" 			saleman_id AS user_id                    ");
			sql.append(" 		FROM                                         ");
			sql.append(" 			tbl_saleman                              ");
			sql.append(" 		WHERE                                        ");
			sql.append(" 			up_saleman_id = ?               ");
			sql.append(" 		AND tbl_saleman.revoke_date IS NULL   and tbl_saleman.saleman_level='I003'     ");
			sql.append(" 	) user_info,                                     ");
			sql.append(" 	tbl_saleman                                      ");
			sql.append(" WHERE                                               ");
			sql.append(" 	user_info.user_id = tbl_saleman.up_saleman_id    ");
			sql.append(" 	GROUP BY	up_saleman_id   ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				teamCount.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return teamCount;
	}

	private String getName(String salemanId) {

		String name = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("select saleman_name from tbl_saleman where saleman_id=?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, salemanId);
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("saleman_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return name;
	}
	// 上级
	private String upSaleMan(String salemanId) {
		String  upid = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("select up_saleman_id from tbl_saleman where saleman_id = ?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, salemanId);
			rs = ps.executeQuery();
			while (rs.next()) {
				upid = rs.getString("up_saleman_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return upid;
	}

	//获取下级的id和姓名
	private 	List<List<String>> downIdName(String salemanId) {
		List<List<String>> tempList = new ArrayList<List<String>>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("select saleman_id ,saleman_name from tbl_saleman where up_saleman_id=? and saleman_level='I002' ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, salemanId);
			rs = ps.executeQuery();
			while (rs.next()) {
				List<String> temp=new  ArrayList<String>();
				tempList.add(temp);
				temp.add(rs.getString(1));
				temp.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return tempList;
	}
	//获取销售经理级别 的id和姓名
	private 	List<List<String>> downIdName2(String salemanId) {
		List<List<String>> tempList = new ArrayList<List<String>>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("select saleman_id ,saleman_name from tbl_saleman where up_saleman_id=? and saleman_level='I003' ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, salemanId);
			rs = ps.executeQuery();
			while (rs.next()) {
				List<String> temp=new  ArrayList<String>();
				tempList.add(temp);
				temp.add(rs.getString(1));
				temp.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return tempList;
	}
	//获取下xia下级的id和姓名
	private 	List<List<String>> downIdName3(String salemanId) {
		List<List<String>> tempList = new ArrayList<List<String>>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("select saleman_id ,saleman_name from tbl_saleman where up_saleman_id=? and saleman_level='I004'");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, salemanId);
			rs = ps.executeQuery();
			while (rs.next()) {
				List<String> temp=new  ArrayList<String>();
				tempList.add(temp);
				temp.add(rs.getString(1));
				temp.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return tempList;
	}

	// 会员按月获取收益
		//one
	private List<String> getMonthProfitPerson(String userId) {
		List<String> price =new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM");
		String ym = simpleFormate.format(calendar.getTime());
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append("SELECT                                           ");
			sql.append("	sum(order_price),                            ");
			sql.append("	user_info.user_id,                           ");
			sql.append("	user_info.user_name                          ");
			sql.append("FROM                                             ");
			sql.append("	tbl_order,                                   ");
			sql.append("	(                                            ");
			sql.append("		SELECT                                   ");
			sql.append("			saleman_id AS user_id,               ");
			sql.append("			saleman_name AS user_name            ");
			sql.append("		FROM                                     ");
			sql.append("			tbl_saleman                          ");
			sql.append("		WHERE                                    ");
			sql.append("			up_saleman_id =?  and   saleman_level='I002'   ");
			sql.append("		AND tbl_saleman.revoke_date IS NULL      ");
			sql.append("	) user_info                                  ");
			sql.append("WHERE                                            ");
			sql.append("	order_status = 'D005'                        ");
			sql.append("AND date_format(order_date, '%Y-%m') = ? ");
			sql.append("AND tbl_order.order_user IN (                    ");
			sql.append("	SELECT                                       ");
			sql.append("		user_id                                  ");
			sql.append("	FROM                                         ");
			sql.append("		tbl_user                                 ");
			sql.append("	WHERE                                        ");
			sql.append("		saleman_id = user_info.user_id           ");
			sql.append("	UNION ALL                                    ");
			sql.append("		SELECT                                   ");
			sql.append("			saleman_id                           ");
			sql.append("		FROM                                     ");
			sql.append("			tbl_saleman                          ");
			sql.append("		WHERE                                    ");
			sql.append("			up_saleman_id = user_info.user_id    ");
			sql.append(")                                                ");
			sql.append("GROUP BY                                         ");
			sql.append("	user_info.user_id                            ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, userId);
			ps.setString(2, ym);
			rs = ps.executeQuery();
			while (rs.next()) {
				price .add(rs.getString("sum(order_price)"));
			}

		} catch (SQLException e) {
 			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}

		return price;
	}
}