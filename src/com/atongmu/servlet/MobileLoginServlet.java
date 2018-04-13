package com.atongmu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.dao.GetRoleDao;
import com.atongmu.dao.SaleManDao;
import com.atongmu.dao.UserDao;
import com.atongmu.daoImpl.BonusDao;
import com.atongmu.daoImpl.GetRoleDaoImpl;
import com.atongmu.daoImpl.SaleManDaoImpl;
import com.atongmu.daoImpl.UserDaoImpl;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.EmojiFilter;
import com.atongmu.util.WechatCommonUtil;

/**
 * 手机登录servlet
 */
@WebServlet("/MobileLoginServlet")
public class MobileLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MobileLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String[] openIDArr = null;
		String[] userinfoArr = null;
		String user_sex = "";
		String user_nickname = "";
		String user_province = "";
		String user_city = "";
		String user_country = "";
		String user_headimgurl = "";
		String code = "";
		String openID = "";
		String access_token = "";
		String refresh_token = "";

		//********************
		//微信登录用
		//********************
		code = request.getParameter("code");
		if(code == null){
			response.sendRedirect("web_mobile/error.jsp");
			return;
		}
		CommonUtil.logger.info("【mobile】"+"WeChat Login,Request Code: "+code);
		try{
			openIDArr = WechatCommonUtil.getOpenID(code);
			openID = openIDArr[0];
			access_token = openIDArr[1];
			refresh_token = openIDArr[2];
			//此处刷新access_token
			openIDArr = WechatCommonUtil.getRefushUser(refresh_token);
			openID = openIDArr[0];
			access_token = openIDArr[1];
			refresh_token = openIDArr[2];

		}catch(Exception e){
			e.printStackTrace();
		}

//		if("omZ3-s3gWVXtbOU_U1YD0_XsKNS0".equals(openID) ||
//		   "omZ3-sxWdOwVwIlBCdXBAKxIISso".equals(openID) ||
//		   "omZ3-s1PmyasblFz0O1tZvZ7ZfMI".equals(openID) ||
//		   "omZ3-syCnR0_50awT6DUF4XQ-jpw".equals(openID) ||
//		   "omZ3-s-EKpeYXJGw-qnoK39BETUY".equals(openID)){
//			//只有开发人员才可以进入系统
//		}else{
//			System.out.println("***************************");
//			System.out.println("外部人员试图进入系统，被魔法结界弹回");
///			System.out.println("***************************");
//			System.out.println("看看是谁擅自闯入");
//			userinfoArr = WechatCommonUtil.getWechatUserInfo(access_token, openID);
//			System.out.println("就是ta，系统已拒绝他进入");
//			response.sendRedirect("web_mobile/error.jsp");
//			return;
//		}
		try{
			System.out.println("即将获取用户信息");
			userinfoArr = WechatCommonUtil.getWechatUserInfo(access_token, openID);
			System.out.println("用户信息获取完毕");

		}catch(Exception e){
			e.printStackTrace();
		}

		user_nickname = EmojiFilter.filterEmoji(userinfoArr[0]);
		if("".equals(user_nickname) || user_nickname == null){
			user_nickname = "会员";
		}
		user_sex = userinfoArr[1];
		user_province =userinfoArr[2];
		user_city = userinfoArr[3];
		user_country = userinfoArr[4];
		user_headimgurl = userinfoArr[5];

		if(openID == null){
			response.sendRedirect("web_mobile/error.jsp");
			return;
		}

		//********************************************************
		//本地调试用
		//********************************************************
//		openID = (String)request.getParameter("openid");
		try{
			GetRoleDao roleDao = new GetRoleDaoImpl();
			SaleManDao saleManDao = new SaleManDaoImpl();
			UserDao userDao = new UserDaoImpl();
			Tbl_user selectUser = new Tbl_user();
			Tbl_saleman selectSaleMan = new Tbl_saleman();

			String userID = roleDao.getUserIDByOpenID(openID);
			request.getSession().setAttribute("userhead", user_headimgurl);
			CommonUtil.logger.info("【mobile】"+"**********************用户请求进入系统**********************");
			CommonUtil.logger.info("【mobile】"+"==========================用户信息==========================");
			CommonUtil.logger.info("【mobile】"+"userID: "+userID);
			CommonUtil.logger.info("【mobile】"+"openID: "+openID);
			CommonUtil.logger.info("【mobile】"+"============================================================");
			if(userID != null){
				//当前用户权限获取（会员/销售员)
				int userRole = roleDao.getUserType(userID);
				CommonUtil.logger.info("【mobile】"+"权限: 会员");
				//对属性进行设置
				selectUser.setOpen_id(openID);
				selectUser.setUser_id(userID);
				selectSaleMan.setOpen_id(openID);
				selectSaleMan.setSaleman_id(userID);
				//权限封装至session
				request.getSession().setAttribute("role", userRole);
				//open封装到session
				request.getSession().setAttribute("openid", openID);
				if(userRole == 1){
					//当前登录用户对象存至session
					//会员的场合
					Tbl_user loginuserinfo = new Tbl_user();
					loginuserinfo = userDao.selectUser(selectUser);
					request.getSession().setAttribute("loginuser", loginuserinfo);
					request.getSession().setAttribute("userid", loginuserinfo.getUser_id());
					response.sendRedirect("GoodsShowServlet");

				}else if(userRole == 2){
					//当前登录用户对象存至session
					//销售员的场合
					CommonUtil.logger.info("【mobile】"+"权限: 销售员");
					Tbl_saleman salemanInfo = null;
					salemanInfo = saleManDao.selectSaleMan(selectSaleMan);
					request.getSession().setAttribute("loginuser", salemanInfo);
					request.getSession().setAttribute("userid", salemanInfo.getSaleman_id());
					String saleman_level = saleManDao.selectSalemanLevel(salemanInfo.getSaleman_id());
					//查询该销售当月的销售额
					BonusDao bonusDao = new BonusDao();
					Object salemanSumObj= bonusDao.selectSaleSum(salemanInfo.getSaleman_id());
					double saleSum = 0.00;
					if(salemanSumObj != null){
						saleSum = Double.parseDouble(salemanSumObj.toString());
					}
					request.getSession().setAttribute("saleSum", saleSum);
					if(!saleman_level.equals(salemanInfo.getSaleman_level())){
						//销售员等级和上次登录时的等级不一致时，更新销售员等级
						saleManDao.updateSalemanLevel(saleman_level, salemanInfo.getSaleman_id());
						salemanInfo.setSaleman_level(saleman_level);
					}
					response.sendRedirect("GoodsShowServlet");
				}
			}
			else{
				CommonUtil.logger.info("【mobile】"+"该用户第一次进入系统，用户数据准备作成");
				Tbl_user insertuserinfo = new Tbl_user();
				insertuserinfo.setOpen_id(openID);
				insertuserinfo.setUser_name(EmojiFilter.filterEmoji( user_nickname));
				insertuserinfo.setUser_sex(user_sex);
				insertuserinfo.setUserfrom(user_country + user_province + user_city);
				userDao.addUser(insertuserinfo);
				//权限封装至session，默认权限：会员
				request.getSession().setAttribute("role", 1);
				//open封装到session
				request.getSession().setAttribute("openid", openID);
				//查询user信息
				userID = roleDao.getUserIDByOpenID(openID);
				//对属性进行设置
				selectUser.setOpen_id(openID);
				selectUser.setUser_id(userID);
				//当前登录用户对象存至session
				Tbl_user loginuserinfo = new Tbl_user();
				loginuserinfo = userDao.selectUser(selectUser);
				request.getSession().setAttribute("loginuser", loginuserinfo);
				request.getSession().setAttribute("userid", loginuserinfo.getUser_id());
				CommonUtil.logger.info("【mobile】"+"用户数据作成成功，用户昵称："+user_nickname);
				response.sendRedirect("GoodsShowServlet");

			}
		}catch(Exception e){
			CommonUtil.logger.info("【mobile】"+e.getStackTrace());
			response.sendRedirect("web_mobile/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
