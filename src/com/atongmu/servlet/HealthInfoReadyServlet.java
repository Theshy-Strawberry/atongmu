package com.atongmu.servlet;

import java.io.IOException;
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

import com.atongmu.bean.Tbl_health_info;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.HibernateSessionFactory;

/**
 * Servlet implementation class HealthInfoReadyServlet
 */
@WebServlet("/HealthInfoReadyServlet")
public class HealthInfoReadyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthInfoReadyServlet() {
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
		CommonUtil.logger.info("【mobile】"+"into HealthInfoReadyServlet.doPost");
		String openid = (String)request.getSession().getAttribute("openid");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String currentMonth = sdf.format(new Date());
		System.out.println("currentMonth"+currentMonth);
		Tbl_health_info health_info = getHealthInfo(openid,currentMonth);
		System.out.println(health_info);
		if(health_info == null){
			response.sendRedirect("web_mobile/healthinfo.jsp?errorcode=1");
		}else{
			request.getSession().setAttribute("health_info",health_info);
			response.sendRedirect("web_mobile/showhealthinfo.jsp?errorcode=0");
		}

	}
	private Tbl_health_info getHealthInfo(String openid,String dateYM){
		Tbl_health_info health_info = null;
		Configuration conf = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		// 读取配置文件
		conf = new Configuration().configure();
		// 创建SessionFactory
		sessionFactory = conf.buildSessionFactory();
		// 打开session
		session = sessionFactory.openSession();

		List<Tbl_health_info> healthInfoList = (List<Tbl_health_info>)session
												.createQuery("select healthinfo from Tbl_health_info healthinfo where open_id = "
															+ " :open_id and date_format(reg_date,'%Y-%m')  = :reg_date")
				 								.setParameter("open_id", openid).setParameter("reg_date", dateYM)
				 								.list();
		if(healthInfoList.size()>0){
			health_info =  healthInfoList.get(0);
		}
		session.close();
		return health_info;
	}
}
