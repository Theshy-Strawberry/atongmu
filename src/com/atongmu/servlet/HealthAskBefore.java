package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.atongmu.bean.Tbl_health_investigation;
import com.atongmu.bean.Tbl_health_investigation_answer;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class HealthAskBefore
 */
@WebServlet("/HealthAskBefore")
public class HealthAskBefore extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthAskBefore() {
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
		CommonUtil.logger.info("【mobile】"+"into HealthAskBefore,doPost");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String[][]  askListArr = getAskList();
		int goodsid = 0;
		if(request.getParameter("goodsid") != null){
			goodsid = Integer.parseInt(request.getParameter("goodsid"));
		}
		String openid = (String)request.getSession().getAttribute("openid");
		if(askListArr.length ==0){
			//如果当前没有问卷的话，则要提示当前没有问卷，然后，跳到健康管理中心主页
			request.getRequestDispatcher("web_mobile/healthcenter.jsp?errorcode=-1").forward(request, response);
		}
		if(0 == goodsid){
				//加载第一条问卷
				//把问卷选项放到session先
				request.getSession().setAttribute("askListArr",  askListArr);
				List<Tbl_health_investigation> investigation = getAskByGoodsID( Integer.parseInt(askListArr[0][0]));
				request.getSession().setAttribute("investigationList", investigation);
				Tbl_health_investigation_answer answerInfo = getAskAnswerFlag(openid,Integer.parseInt(askListArr[0][0]));
				if(answerInfo == null){
					//如果当日没有填写过该问卷，则跳转到问卷填写页面
					request.getRequestDispatcher("web_mobile/healthask.jsp?errorcode=1").forward(request, response);
				}else{

					//如果当日填写过该问卷，则跳转到问卷显示页面
					request.getSession().setAttribute("answerInfo", answerInfo);
					response.sendRedirect("web_mobile/healthanswer.jsp?errorcode=0&hdngoodid="+goodsid);
				}

		}else{
			//加载用户选择的问卷
			request.getSession().setAttribute("askListArr",  askListArr);
			List<Tbl_health_investigation> investigation = getAskByGoodsID(goodsid);
			request.getSession().setAttribute("hdngoodid", goodsid);
			request.getSession().setAttribute("investigationList", investigation);
			Tbl_health_investigation_answer answerInfo = getAskAnswerFlag(openid,goodsid);
			if(answerInfo == null){
				//如果当日没有填写过该问卷，则跳转到问卷填写页面
				request.getRequestDispatcher("web_mobile/healthask.jsp?errorcode=1").forward(request, response);
			}else{
				//如果当日填写过该问卷，则跳转到问卷显示页面
				request.getSession().setAttribute("answerInfo", answerInfo);
				response.sendRedirect("web_mobile/healthanswer.jsp?errorcode=0&hdngoodid="+goodsid);
			}
		}


	}
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;

    private String[][] getAskList() {
    	String[][] askMap = null;
		CommonUtil.logger.info("【mobile】"+"into HealthAskBefore,getAskList");
        try {
            con = MySQLUtil.getConnection();
            StringBuffer sql = new StringBuffer("");
            sql.append(" SELECT");
            sql.append(" 	tc.goods_id,");
            sql.append(" 	tc.goods_name");
            sql.append(" FROM");
            sql.append(" 	tbl_commodity tc");
            sql.append(" INNER JOIN tbl_health_investigation thi ON thi.goods_id = tc.goods_id");
            sql.append(" GROUP BY");
            sql.append(" 	tc.goods_id order by tc.goods_id ");
            ps = con.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            askMap = new String[20][2];
            int i=0;
            while(rs.next()){
            	askMap[i][0] = rs.getString("goods_id");
            	askMap[i][1] =  rs.getString("goods_name").replace("】","").replace("【", "-");
            	i++;
            }
            } catch (Exception e) {
            	CommonUtil.logger.error(e.getMessage());
            }finally{
                MySQLUtil.closeAll(rs, sm, con, ps);
            }
        return askMap;
    }

	private List<Tbl_health_investigation> getAskByGoodsID(int goods_id){
		Configuration conf = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		// 读取配置文件
		conf = new Configuration().configure();
		// 创建SessionFactory
		sessionFactory = conf.buildSessionFactory();
		// 打开session
		session = sessionFactory.openSession();

		@SuppressWarnings("unchecked")
		List<Tbl_health_investigation> investigationInfoList = (List<Tbl_health_investigation>)session
												.createQuery("select investigationInfo from Tbl_health_investigation investigationInfo where goods_id = "
															+ " :goods_id order by question_id")
				 								.setParameter("goods_id", goods_id)
				 								.list();

		session.close();
		return investigationInfoList;
	}

	private Tbl_health_investigation_answer getAskAnswerFlag(String openid,int goodsid){
		Tbl_health_investigation_answer answerInfo = null;
		Configuration conf = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		// 读取配置文件
		conf = new Configuration().configure();
		// 创建SessionFactory
		sessionFactory = conf.buildSessionFactory();
		// 打开session
		session = sessionFactory.openSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDay = sdf.format(new Date());
		List<Tbl_health_investigation_answer> answerList  = (List<Tbl_health_investigation_answer>)session
															.createQuery(" from Tbl_health_investigation_answer where open_id = "
																		+ " :open_id and goods_id = :goods_id and date_format(reg_date,'%Y-%m-%d')  = :reg_date")
							 								.setParameter("open_id", openid).setParameter("goods_id",goodsid).setParameter("reg_date", currentDay)
							 								.list();
		if(answerList.size()>0){
			//说明当日已经填写过该问卷
			answerInfo = answerList.get(0);
		}
		session.close();
		return answerInfo;
	}
}
