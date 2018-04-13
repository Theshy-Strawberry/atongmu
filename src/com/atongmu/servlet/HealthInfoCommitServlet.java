package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_health_info;
import com.atongmu.bean.Tbl_user;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.HibernateSessionFactory;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class HealthInfoCommitServlet
 */
@WebServlet("/HealthInfoCommitServlet")
public class HealthInfoCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthInfoCommitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		CommonUtil.logger.info("【mobile】"+"into HealthInfoCommitServlet.doPost");
		Tbl_health_info thi = new Tbl_health_info();
		thi.setOpen_id((String)request.getSession().getAttribute("openid"));
		thi.setReg_date(new Timestamp(new Date().getTime()));
		thi.setHeight(StringUtil.nvl(request.getParameter("height")));
		thi.setWeight(StringUtil.nvl(request.getParameter("weight")));
		double height = Double.parseDouble(StringUtil.nvl(request.getParameter("weight")));
		double weight = Double.parseDouble(StringUtil.nvl(request.getParameter("height"))) / 10;
		double bmi = height / (weight*2);
		thi.setBMI(bmi);
		thi.setWaist(StringUtil.nvl(request.getParameter("waist")));
		thi.setHipline(StringUtil.nvl(request.getParameter("hipline")));
		double waist = Double.parseDouble(StringUtil.nvl(request.getParameter("waist")));
		double hipline = Double.parseDouble(StringUtil.nvl(request.getParameter("hipline")));
		thi.setWaist_to_hip_ratio(waist/hipline);
		thi.setLow_blood_pressure(StringUtil.nvl(request.getParameter("low_blood_pressure")));
		thi.setHigh_blood_pressure(StringUtil.nvl(request.getParameter("low_blood_pressure")));
		thi.setFasting_blood_glucose(StringUtil.nvl(request.getParameter("fasting_blood_glucose")));
		thi.setTriglyceride(StringUtil.nvl(request.getParameter("triglyceride")));
		thi.setTotal_cholesterol(StringUtil.nvl(request.getParameter("total_cholesterol")));
		thi.setLdl(StringUtil.nvl(request.getParameter("ldl")));
		thi.setHdl(StringUtil.nvl(request.getParameter("hdl")));
		thi.setOther(StringUtil.nvl(request.getParameter("other")));
		//是否有糖尿病
		thi.setAsk1(request.getParameter("ask1"));
		//是否有高血压
		thi.setAsk30(request.getParameter("ask30"));
		//是否有高血脂
		thi.setAsk31(request.getParameter("ask31"));
		//是否有高尿酸血症
		thi.setAsk32(request.getParameter("ask32"));
		//总的来说，您认为您的健康状况
		thi.setAsk2(request.getParameter("ask2"));
		//最近一个月，您有健康问题吗？
		thi.setAsk3(request.getParameter("ask3"));
		//您是否开心？
		thi.setAsk4(request.getParameter("ask4"));
		//您的心态怎么样？是否遇到什么事都能想得开？
		thi.setAsk5(request.getParameter("ask5"));
		//您吃处方药吗？
		String ask6 = request.getParameter("ask6");
		if("0".equals(StringUtil.nvl(ask6))){
			ask6 = request.getParameter("ask6_other");
		}
		thi.setAsk6(ask6);
		//为了保持健康或防治疾病，您有以下哪些消费？
		String[] ask43Arr = request.getParameterValues("ask43");
		String ask43 = "";
		for(int i=0;i<ask43Arr.length;i++){
			if("0".equals(ask43Arr[i])){
				thi.setAsk44(StringUtil.nvl(request.getParameter("ask44")));
				continue;
			}
			ask43 = ask43 + ask43Arr[i] + ",";
		}
		thi.setAsk43(StringUtil.nvl(ask43));
		//您服用保健品吗？
		String ask7 = request.getParameter("ask7");
		if("0".equals(StringUtil.nvl(ask7))){
			ask7 = request.getParameter("ask7_other");
		}
		thi.setAsk7(ask7);
		//粮谷类（主食馒头、米饭、包子、饼、面条、窝头等）干重摄入量：
		thi.setAsk8(StringUtil.nvl(request.getParameter("ask8")));
		//其中粗杂粮（大米白面之外的粮谷类包括绿豆等杂豆类）摄入量：
		thi.setAsk9(StringUtil.nvl(request.getParameter("ask9")));
		//薯类摄入量：
		thi.setAsk10(StringUtil.nvl(request.getParameter("ask10")));
		//蔬菜摄入量：
		thi.setAsk11(StringUtil.nvl(request.getParameter("ask11")));
		//水果摄入量：
		thi.setAsk12(StringUtil.nvl(request.getParameter("ask12")));
		//畜禽肉、鱼虾贝类摄入量：
		thi.setAsk13(StringUtil.nvl(request.getParameter("ask13")));
		//蛋类摄入量：
		thi.setAsk14(StringUtil.nvl(request.getParameter("ask14")));
		//豆腐等豆制品摄入量：
		thi.setAsk15(StringUtil.nvl(request.getParameter("ask15")));
		//奶和奶制品摄入量：
		thi.setAsk16(StringUtil.nvl(request.getParameter("ask16")));
		//坚果摄入量：
		thi.setAsk17(StringUtil.nvl(request.getParameter("ask17")));
		//饮水量：
		thi.setAsk18(StringUtil.nvl(request.getParameter("ask18")));
		//每天吃几餐：
		thi.setAsk19(StringUtil.nvl(request.getParameter("ask19")));
		//您每天或每周食用食物的种类：
		thi.setAsk20(StringUtil.nvl(request.getParameter("ask20")));
		//你的饮食口味（咸）：
		thi.setAsk21(StringUtil.nvl(request.getParameter("ask21")));
		//你的饮食口味（油）：
		thi.setAsk22(StringUtil.nvl(request.getParameter("ask22")));
		//您近半年是否有被动吸烟（二手烟）的情况？
		thi.setAsk23(StringUtil.nvl(request.getParameter("ask23")));
		//您是否吸烟？
		thi.setAsk24(StringUtil.nvl(request.getParameter("ask24")));
		//您是否喝酒？
		thi.setAsk25(StringUtil.nvl(request.getParameter("ask25")));
		//您每周参加几次体育锻炼？
		thi.setAsk26(StringUtil.nvl(request.getParameter("ask26")));
		//您平均每次体育锻炼的时间？
		thi.setAsk27(StringUtil.nvl(request.getParameter("ask27")));
		//您的体育锻炼形式？
		thi.setAsk28(StringUtil.nvl(request.getParameter("ask28")));
		//肩颈臂疼痛：疼痛不固定，会转移
		thi.setAsk33(StringUtil.nvl(request.getParameter("ask33")));
		//胃疼恶心：从没有胃病却胃疼
		thi.setAsk34(StringUtil.nvl(request.getParameter("ask34")));
		//焦虑失眠：无症状惊醒
		thi.setAsk35(StringUtil.nvl(request.getParameter("ask35")));
		//心跳加剧：不规则且无缘由
		thi.setAsk36(StringUtil.nvl(request.getParameter("ask36")));
		//过度出汗：长期反复
		thi.setAsk37(StringUtil.nvl(request.getParameter("ask37")));
		//呼吸短促：头晕，很难深呼吸
		thi.setAsk38(StringUtil.nvl(request.getParameter("ask38")));
		//极度疲劳：没做什么就特别累，哈欠不断
		thi.setAsk39(StringUtil.nvl(request.getParameter("ask39")));
		//四肢尤其双手突然冰冷
		thi.setAsk40(StringUtil.nvl(request.getParameter("ask40")));
		//血压突然升高和降低，且有鼻出血
		thi.setAsk41(StringUtil.nvl(request.getParameter("ask41")));
		//走路步态异常，腿部无力
		thi.setAsk42(StringUtil.nvl(request.getParameter("ask42")));
		//突然剧烈头痛
		thi.setAsk43(StringUtil.nvl(request.getParameter("ask43")));
		try{
			HibernateSessionFactory.add(thi);
		}catch(Exception e){
			CommonUtil.logger.info("【mobile】"+e.getStackTrace());
		}
		response.sendRedirect("web_mobile/showhealthinfo.jsp?errorcode=0");
	}

}
