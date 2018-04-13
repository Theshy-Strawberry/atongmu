package com.atongmu.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.atongmu.bean.Tbl_sale_audit;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.dao.SaleManDao;
import com.atongmu.dao.UserDao;
import com.atongmu.daoImpl.SaleManDaoImpl;
import com.atongmu.daoImpl.UpdateSomeInfo;
import com.atongmu.daoImpl.UserDaoImpl;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.ImageUtil;
import com.atongmu.util.MakeUnqID;
import com.atongmu.util.StringUtil;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class UpdateUserInfoServlet
 */
@WebServlet("/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}
	protected void doDelete(){

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		try{
			int userType = Integer.parseInt(request.getSession().getAttribute("role").toString());
			int updateKubun = Integer.parseInt((String)request.getParameter("updateKubun"));
			UpdateSomeInfo updateSomeInfo = new UpdateSomeInfo();

			if(updateKubun == 1){
				//用户修改资料的场合
				if(userType != 1){
					//非会员无权限进行该项操作
					response.sendRedirect("web_mobile/error.jsp");
					return;
				}
				//封装更新前的用户信息
				Tbl_user ongilUser = (Tbl_user)request.getSession().getAttribute("loginuser");
				CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"选择了更新用户资料");
				boolean result_count = true;
				if( !"".equals(StringUtil.nvl(request.getParameter("saleman_id")))){
					//如果输入了上级销售员ID的话
					SaleManDao salemandao = new SaleManDaoImpl();
					Tbl_saleman salemaninfo = salemandao.selectSaleManByID((String)request.getParameter("saleman_id"));
					if(salemaninfo == null){
						result_count = false;
						CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"输入的上级销售员ID不存在，页面即将返回");
						response.sendRedirect("web_mobile/updateUserInfo.jsp?errorcode=-4");
						return;
					}
					Tbl_user userinfo = new Tbl_user();
					userinfo.setUser_id((String)request.getSession().getAttribute("userid"));
					userinfo.setOpen_id((String)request.getSession().getAttribute("openid"));
					userinfo.setSaleman_id((String)request.getParameter("saleman_id"));
					CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"开始绑定上级信息，他填入的上级ID为："+userinfo.getSaleman_id());
					result_count = updateSomeInfo.buildUpSaleman(userinfo);
				}
				Tbl_user userinfo = new Tbl_user();
				userinfo.setUser_id((String)request.getSession().getAttribute("userid"));
                userinfo.setUser_name((String)request.getParameter("user_name"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );
                Date userBirthday = sdf.parse((String)request.getParameter("birthday"));
                userinfo.setUser_birthday(userBirthday);
                userinfo.setUser_tel_num((String)request.getParameter("telnumber"));
                userinfo.setUser_addr((String)request.getParameter("address"));
                userinfo.setUser_post((String)request.getParameter("postNumber"));
                userinfo.setWeixin_id((String)request.getParameter("wechat"));
                userinfo.setUserfrom((String)request.getParameter("user_form"));
                userinfo.setOpen_id((String)request.getSession().getAttribute("openid"));
                userinfo.setUser_nation((String)request.getParameter("user_nation"));
                userinfo.setUser_education((String)request.getParameter("user_education"));
                userinfo.setUser_occupation((String)request.getParameter("user_occupation"));
                if(!"".equals(StringUtil.nvl(request.getParameter("sex")))){
                	userinfo.setUser_sex(request.getParameter("sex"));
                }
                CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"的OPENID为"+userinfo.getOpen_id());
                CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"的USERID为"+userinfo.getUser_id());
				if(updateSomeInfo.updateUser(userinfo)){
					//更新成功
					//重新查询用户信息，放到session中
					Tbl_user selectUser = new Tbl_user();
					selectUser.setOpen_id((String)request.getSession().getAttribute("openid"));
					selectUser.setUser_id((String)request.getSession().getAttribute("userid"));
					CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"的资料即将被更新");
					Tbl_user userinfo_new = new Tbl_user();
					UserDao userDao = new UserDaoImpl();
					userinfo_new = userDao.selectUser(selectUser);
					request.getSession().setAttribute("loginuser", userinfo_new);
					int return_code = Integer.parseInt((String)request.getParameter("returncode"));
					if(return_code == -3 ){
						if(result_count){
							//更新成功后页面跳转下订单的页面
							CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"下单时，收货地址不完整，所以更新完资料即将跳转到统一下单的servlet");
							response.sendRedirect("GoodsSettlementServlet");
						}else{
							CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"下单时，收货地址不完整，并且输入的上级销售ID不存在，所以即将返回原页面");
							//输入上级销售的情况下，并且返回了false，跳回原页面
							response.sendRedirect("web_mobile/updateUserInfo.jsp?errorcode=-4");
						}
					}else{
						if(result_count){
							if(request.getParameter("saleman_id") != null && !"".equals(request.getParameter("saleman_id"))){
								//填入了销售的情况下，但具体是第一次绑定还是选择的资料更新还需继续判断
								if(ongilUser.getVip_flag() == 0 && userinfo_new.getVip_flag() == 1){
									CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"资料修改完成，并且注册成为会员");
									//之前没有更新会员flg，该次选择了更新会员flg的场合，则提示"成功注册会员，目前您可以享受会员的所有权利。"
									response.sendRedirect("web_mobile/updateUserInfo.jsp?errorcode=1");
								}else{
									//资料更新成功
									CommonUtil.logger.info("【mobile】"+"会员"+ongilUser.getUser_name()+"资料修改完成，前台提示资料修改成功");
									response.sendRedirect("web_mobile/updateUserInfo.jsp?errorcode=2");
								}
							}else if(ongilUser.getVip_flag() ==0 && userinfo_new.getVip_flag() == 0){
								//这次没填入销售员ID，并且执行了更新，则提示"资料修改成功成功，由于您尚未填写健康顾问ID，所以无法享受会员的权利。"
								CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"资料修改完成，由于没有输入销售员ID,所以提示无法享受会员的权益");
								response.sendRedirect("web_mobile/updateUserInfo.jsp?errorcode=0");
							}
						}else{
							//更新成功后页面跳转
							response.sendRedirect("web_mobile/updateUserInfo.jsp?errorcode=-4");
							CommonUtil.logger.info("【mobile】"+"用户"+ongilUser.getUser_name()+"试图绑定销售员ID，但销售员ID不存在，所以资料修改失败");
						}
					}

					return;
				}else{
					//更新失败
					response.sendRedirect("web_mobile/updateUserInfo.jsp?errorcode=-1");
					CommonUtil.logger.error("【mobile】"+"会员"+ongilUser.getUser_name()+"资料修改失败");
					return;
				}
			}else if(updateKubun == 2){
				//销售修改资料的场合
				if(userType != 2){
					//非销售无权限进行该项操作
					response.sendRedirect("web_mobile/error.jsp");
					return;
				}
				Tbl_saleman salemaninfo = new Tbl_saleman();
				salemaninfo.setSaleman_id((String)request.getSession().getAttribute("userid"));
				salemaninfo.setSaleman_name((String)request.getParameter("user_name"));
				CommonUtil.logger.info("【mobile】"+"销售"+salemaninfo.getSaleman_name()+"的资料即将被更新，销售ID："+salemaninfo.getSaleman_id());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date salemanBirthday = sdf.parse((String)request.getParameter("birthday"));
				salemaninfo.setSaleman_birthday(salemanBirthday);
				salemaninfo.setSaleman_tel_num((String)request.getParameter("telnumber"));
				salemaninfo.setSaleman_addr((String)request.getParameter("address"));
				salemaninfo.setSaleman_post((String)request.getParameter("postNumber"));
				salemaninfo.setWeixin_id((String)request.getParameter("wechat"));
				salemaninfo.setAlipay_id((String)request.getParameter("alipay_id"));
				salemaninfo.setCard_bank((String)request.getParameter("bankName"));
				salemaninfo.setCard_name((String)request.getParameter("bankCreateName"));
				salemaninfo.setCard_number((String)request.getParameter("bankCardNo"));
				salemaninfo.setOpen_id((String)request.getSession().getAttribute("openid"));

				salemaninfo.setUserfrom((String)request.getParameter("user_form"));
                salemaninfo.setUser_nation((String)request.getParameter("user_nation"));
                salemaninfo.setUser_education((String)request.getParameter("user_education"));
                salemaninfo.setUser_occupation((String)request.getParameter("user_occupation"));
                if(!"".equals(StringUtil.nvl(request.getParameter("sex")))){
                	salemaninfo.setSaleman_sex(request.getParameter("sex"));
                }
				if(updateSomeInfo.updateSaleMan(salemaninfo)){
					//更新成功
					CommonUtil.logger.info("【mobile】"+"销售"+salemaninfo.getSaleman_name()+"的资料更新成功");
					Tbl_saleman selectSaleMan = new Tbl_saleman();
					selectSaleMan.setOpen_id((String)request.getSession().getAttribute("openid"));
					selectSaleMan.setSaleman_id((String)request.getSession().getAttribute("userid"));
					Tbl_saleman salemanInfo_new = new Tbl_saleman();
					SaleManDao saleManDao = new SaleManDaoImpl();
					salemanInfo_new = saleManDao.selectSaleMan(selectSaleMan);
					request.getSession().setAttribute("loginuser", salemanInfo_new);

					int return_code = Integer.parseInt(request.getParameter("returncode"));
					if(return_code == -3){
						//更新成功后页面跳转
						CommonUtil.logger.info("【mobile】"+"销售"+salemaninfo.getSaleman_name()+"下单时，收货地址不完整，所以更新完资料即将跳转到统一下单的servlet");
						response.sendRedirect("GoodsSettlementServlet");
						return;
					}else{
						CommonUtil.logger.info("【mobile】"+"销售"+salemaninfo.getSaleman_name()+"的资料更新成功，页面跳转至更新页面");
						response.sendRedirect("web_mobile/updateSalemanInfo.jsp?errorcode=0");
					}
					return;
				}else{
					//更新失败
					CommonUtil.logger.info("【mobile】"+"销售"+salemaninfo.getSaleman_name()+"的资料更新失败");
					response.sendRedirect("web_mobile/updateSalemanInfo.jsp?errorcode=-1");
					return;
				}

			}else if(updateKubun ==3){
				//销售认证的场合
				if(userType != 2){
					//非销售无权限进行该项操作
					response.sendRedirect("web_mobile/error.jsp");
					return;
				}
				Tbl_saleman salemaninfo = new Tbl_saleman();
				salemaninfo.setSaleman_name((String)request.getParameter("user_name"));
				salemaninfo.setSaleman_tel_num((String)request.getParameter("telnumber"));
                salemaninfo.setWeixin_id((String)request.getParameter("wechat"));
                String saleManID = updateSomeInfo.selectSaleMan(salemaninfo);
                boolean result_count = false;
                if(saleManID != null){
                	salemaninfo.setOpen_id((String)request.getSession().getAttribute("openid"));
                	//有该条记录的场合，更新当前销售员的open id
                	result_count = updateSomeInfo.updateSaleManOpenID(salemaninfo);
                	if(result_count){
                		response.sendRedirect("web_mobile/updateInfo.jsp?errorcode=0");
                		return;
                	}else{
                    	//数据库中没有该条记录的场合
                    	response.sendRedirect("web_mobile/updateInfo.jsp?errorcode=-1");
                    	return;
                	}
                }else{
                	//数据库中没有该条记录的场合
                	response.sendRedirect("web_mobile/updateInfo.jsp?errorcode=-1");
                	return;
                }
			}else if(updateKubun == 4){
				//用户绑定上级销售的场合
				if(userType != 1){
					//非会员无权限进行该项操作
					response.sendRedirect("web_mobile/error.jsp");
					return;
				}
				SaleManDao salemandao = new SaleManDaoImpl();
				Tbl_saleman salemaninfo = salemandao.selectSaleManByID((String)request.getParameter("saleman_id"));
				if(salemaninfo == null){
					response.sendRedirect("web_mobile/bindingsaleman.jsp?errorcode=-1");
					return;
				}
				Tbl_user userinfo = new Tbl_user();
				boolean result_count = false;
				System.out.println("开始绑定上级信息");
				userinfo.setUser_id((String)request.getSession().getAttribute("userid"));
				userinfo.setOpen_id((String)request.getSession().getAttribute("openid"));
				userinfo.setSaleman_id((String)request.getParameter("saleman_id"));
				result_count = updateSomeInfo.buildUpSaleman(userinfo);
				//用户资料更新后存入session
				UserDao userDao = new UserDaoImpl();
				Tbl_user userinfo_new = new Tbl_user();
				userinfo_new = userDao.selectUser(userinfo);
				request.getSession().setAttribute("loginuser", userinfo_new);

				if(result_count){
				    response.sendRedirect("web_mobile/bindingsaleman.jsp?errorcode=0");
				    return;
				}else{
					response.sendRedirect("web_mobile/bindingsaleman.jsp?errorcode=-1");
					return;
				}
				//当前登录用户对象存至session

			}else if(updateKubun == 5){
				//申请成为销售员的场合
				if(userType != 1){
					//非会员无权限进行该项操作
					response.sendRedirect("web_mobile/error.jsp");
					return;
				}
				if(updateSomeInfo.selectAuditSaleManStatus((String)request.getSession().getAttribute("userid"))!=null){
					//如果当前已经申请过的话，则返回页面，提示当前用户已申请销售
					response.sendRedirect("web_mobile/registertosaleman.jsp?errcode=-2");
					return;
				}
				if(ServletFileUpload.isMultipartContent(request)){
					DiskFileItemFactory factory = new DiskFileItemFactory();
			        // maximum size that will be stored in memory
			        factory.setSizeThreshold(4096);// 设置缓冲区大小，这里是4kb
			        // Location to save data that is larger than maxMemSize.
			        ServletFileUpload upload = new ServletFileUpload(factory);
			        upload.setHeaderEncoding("UTF-8");
			        // Parse the request to get file items.
			        List<FileItem> fileItems = null;


	                String saleman_id = "";
	                String user_name = "";
	                Double user_integral = 0.0;
	                String birthday = "";
	                String telnumber = "";
	                String address = "";
	                String postNumber = "";
	                String alipay_id = "";
	                String bankName = "";
	                String bankCreateName = "";
	                String bankCardNo = "";
		        	try{
		        		fileItems = upload.parseRequest(request);
		        		int i = 0;
		        		Iterator ite  = fileItems.iterator();
		        		FileItem item = null;
		        		while (ite .hasNext()) {
		        			item=(FileItem)ite.next();
		        			if(item.isFormField() && ((String)item.getFieldName()).equals("saleman_id"))
	                        {
	                        	saleman_id = new String(item.getString("utf-8"));
	                        }
	                        if(item.isFormField()  && ((String)item.getFieldName()).equals("user_name"))
	                        {
	                        	user_name = new String(item.getString("utf-8"));
	                        }
	                        if(item.isFormField()  &&  ((String)item.getFieldName()).equals("user_integral"))
	                        {
	                        	user_integral = Double.parseDouble(StringUtil.nvl(item.getString()));
	                        }
	                        if(item.isFormField()  &&  ((String)item.getFieldName()).equals("birthday"))
	                        {
	                        	birthday = new String(item.getString("utf-8"));
	                        }
	                        if(item.isFormField()  &&  ((String)item.getFieldName()).equals("telnumber"))
	                        {
	                        	telnumber = new String(item.getString("utf-8"));
	                        }
	                        if(item.isFormField()  &&  ((String)item.getFieldName()).equals("address"))
	                        {
	                        	address = new String(item.getString("utf-8"));
	                        }
	                        if(item.isFormField()  &&  ((String)item.getFieldName()).equals("postNumber"))
	                        {
	                        	postNumber = new String(item.getString("utf-8"));
	                        }
	                        if(item.isFormField()  &&  ((String)item.getFieldName()).equals("alipay_id"))
	                        {
	                        	alipay_id = new String(item.getString("utf-8"));
	                        }
	                        if(item.isFormField()  && ((String)item.getFieldName()).equals("bankName"))
	                        {
	                        	bankName = new String(item.getString("utf-8"));
	                        }
	                        if(item.isFormField() &&  ((String)item.getFieldName()).equals("bankCreateName"))
	                        {
	                        	bankCreateName = new String(item.getString("utf-8"));
	                        }
	                        if(item.isFormField()  &&  ((String)item.getFieldName()).equals("bankCardNo"))
	                        {
	                        	bankCardNo = new String(item.getString("utf-8"));
	                        }
		        		}
		        		for(FileItem fileItem:fileItems){

		                    String filePath = fileItem.getName();
		                    System.out.println(filePath);
		                    if(filePath==null || filePath.trim().length()==0)

		                        continue;
	                        try {
	                        	fileItem.write(new File("C:\\userimage\\" + (String)request.getSession().getAttribute("userid")+"_0" + (i+1)+".jpg"));
	                        	i++;
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                        }
		                }

		            }catch(FileSizeLimitExceededException e){
		                System.out.println("file size is not allowed");
		            }catch(FileUploadException e1){
		                e1.printStackTrace();
		            }
					SaleManDao salemandao = new SaleManDaoImpl();
					Tbl_saleman salemaninfo = salemandao.selectSaleManByID(saleman_id);
					if(salemaninfo == null){
						response.sendRedirect("web_mobile/registertosaleman.jsp?errcode=-3");
						return;
					}
					Tbl_sale_audit sale_audit = new Tbl_sale_audit();
					sale_audit.setReq_date(new Timestamp(new Date().getTime()));
					sale_audit.setReq_status("H003");
					sale_audit.setReq_user_id((String)request.getSession().getAttribute("userid"));
	                if(!updateSomeInfo.addAuditSaleMan(sale_audit)){
	                	//插入失败的话
	                	response.sendRedirect("web_mobile/registertosaleman.jsp?errcode=-1");
	                	return;
	                }
					salemaninfo.setSaleman_id(MakeUnqID.nextId());
					salemaninfo.setUp_saleman_id(saleman_id);
					salemaninfo.setOpen_id((String)request.getSession().getAttribute("openid"));
					salemaninfo.setSaleman_name(user_name);
					salemaninfo.setUser_integral(user_integral);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                Date salemanBirthday = sdf.parse(birthday);
	                salemaninfo.setSaleman_birthday(salemanBirthday);
	                salemaninfo.setSaleman_tel_num(telnumber);
	                salemaninfo.setSaleman_addr(address);
	                salemaninfo.setSaleman_post(postNumber);
					salemaninfo.setAlipay_id(alipay_id);
					salemaninfo.setCard_bank(bankName);
					salemaninfo.setCard_name(bankCreateName);
					salemaninfo.setCard_number(bankCardNo);
					//绑定上级
					System.out.println("开始绑定上级信息");
					Tbl_user userinfo = new Tbl_user();
					userinfo.setUser_id((String)request.getSession().getAttribute("userid"));
					userinfo.setOpen_id((String)request.getSession().getAttribute("openid"));
					userinfo.setSaleman_id(saleman_id);
					updateSomeInfo.buildUpSaleman(userinfo);
					//用户资料更新后存入session
					UserDao userDao = new UserDaoImpl();
					Tbl_user userinfo_new = new Tbl_user();
					userinfo_new = userDao.selectUser(userinfo);
					request.getSession().setAttribute("loginuser", userinfo_new);
					if(!salemandao.addSaleMan(salemaninfo)){
						response.sendRedirect("web_mobile/registertosaleman.jsp?errcode=-1");
						return;
					}
					response.sendRedirect("web_mobile/registertosaleman.jsp?errcode=0");
					return;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//出错就跳error jsp
			response.sendRedirect("web_mobile/error.jsp");
			return;
		}

	}
	/**
	 * 上传文件到数据表
	 *
	 * @param filePath
	 */
	private List<Object> upload(String filePath) {

		FileInputStream inStream = null;
		List<Object> return_obj = new ArrayList<Object>();
		File file = null;
		int lastIndexOfNode = filePath.lastIndexOf(".");
		int lastIndexOfBacklash = filePath.lastIndexOf("\\");
		String fileName = filePath.substring(lastIndexOfBacklash + 1, lastIndexOfNode);
		System.out.println("filePath=" + filePath);
		System.out.println("fileName=" + fileName);
		inStream = ImageUtil.getByteImage(filePath);
		file = new File(filePath);
		return_obj.add(file);
		return_obj.add(inStream);

		return return_obj;
	}

}
