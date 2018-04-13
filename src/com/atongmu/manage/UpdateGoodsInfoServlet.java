package com.atongmu.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.atongmu.util.ImageUtil;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class ReleaseGoodsServlet
 */
@WebServlet("/UpdateGoodsInfoServlet")
public class UpdateGoodsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodsInfoServlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String goodsId=request.getParameter("goodsId");
		goodsId="9";
		String goodsName=null;//=request.getParameter("goodsName");
		String goodsCategory=null;//request.getParameter("goodsCategory");
		String goodsPrice=null;//request.getParameter("goodsPrice");
		String goodsStock=null;//request.getParameter("goodsStock");
		String goodsDiscount=null;//request.getParameter("goodsDiscount");
		String goodSpec=null;//request.getParameter("goodSpec");
		String goodsDescribe=null;//request.getParameter("goodsDescribe");
		String goodsKeys=null;//request.getParameter("goodsKeys");
		
	//=================================================
		DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(4096);// 设置缓冲区大小，这里是4kb
        // Location to save data that is larger than maxMemSize.
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        // Parse the request to get file items.
        List<FileItem> fileItems = null;
    	try {
			fileItems = upload.parseRequest(request);

		int i = 0;
		Iterator ite  = fileItems.iterator();
		FileItem item = null;
		while (ite .hasNext()) {
			item=(FileItem)ite.next();
			if(item.isFormField() && ((String)item.getFieldName()).equals("goodsName"))
            {
				goodsName = new String(item.getString("utf-8"));
            }
			if(item.isFormField() && ((String)item.getFieldName()).equals("goodsCategory"))
			{
				goodsCategory = new String(item.getString("utf-8"));
			}
			if(item.isFormField() && ((String)item.getFieldName()).equals("goodsPrice"))
			{
				goodsPrice = new String(item.getString("utf-8"));
			}
			if(item.isFormField() && ((String)item.getFieldName()).equals("goodsStock"))
			{
				goodsStock = new String(item.getString("utf-8"));
			}
			if(item.isFormField() && ((String)item.getFieldName()).equals("goodsDiscount"))
			{
				goodsDiscount = new String(item.getString("utf-8"));
			}
			if(item.isFormField() && ((String)item.getFieldName()).equals("goodSpec"))
			{
				goodSpec = new String(item.getString("utf-8"));
			}
			if(item.isFormField() && ((String)item.getFieldName()).equals("goodsDescribe"))
			{
				goodsDescribe = new String(item.getString("utf-8"));
			}
			if(item.isFormField() && ((String)item.getFieldName()).equals("goodsKeys"))
			{
				goodsKeys = new String(item.getString("utf-8"));
			}
			if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage1"))
            {
			
				if(item.getSize()==0){
					continue;
				}
			InputStream ins=	item.getInputStream();
			File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
			File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_1"+".jpg");
			if(!path.mkdirs()){
				path.mkdirs();
			}
			OutputStream os = new FileOutputStream(outFile);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
            }
			//222222
			if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage2"))
			{

				if(item.getSize()==0){
					continue;
				}
				InputStream ins=	item.getInputStream();
				File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
				File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_2"+".jpg");
				if(!path.mkdirs()){
					path.mkdirs();
				}
				OutputStream os = new FileOutputStream(outFile);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				ins.close();
			}
			//33333
			if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage3"))
			{

				if(item.getSize()==0){
					continue;
				}
				InputStream ins=	item.getInputStream();
				File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
				File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_3"+".jpg");
				if(!path.mkdirs()){
					path.mkdirs();
				}
				OutputStream os = new FileOutputStream(outFile);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				ins.close();
			}
			//4444444444444
			if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage4"))
			{

				if(item.getSize()==0){
					continue;
				}
				InputStream ins=	item.getInputStream();
				File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
				File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_4"+".jpg");
				if(!path.mkdirs()){
					path.mkdirs();
				}
				OutputStream os = new FileOutputStream(outFile);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				ins.close();
			}
			//5555555
			if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage5"))
			{

				if(item.getSize()==0){
					continue;
				}
				InputStream ins=	item.getInputStream();
				File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
				File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+goodsId+"_5"+".jpg");
				if(!path.mkdirs()){
					path.mkdirs();
				}
				OutputStream os = new FileOutputStream(outFile);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				ins.close();
			}
		}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
    	
    	boolean flag=uodateGoodsInfo(goodsId,goodsName,goodsCategory,goodsPrice, goodsStock, goodsDiscount, 
				goodSpec, goodsDescribe, goodsKeys);
		//=================================================
    	if(flag){
    		
    	}else{
    		
    	}
    	  response.sendRedirect("ShowGoodsListServlet");
    	
	}

	private boolean uodateGoodsInfo(String goodsId,String goodsName,String goodsCategory,String goodsPrice,String goodsStock,String goodsDiscount,String goodSpec,
			String goodsDescribe,String goodsKeys) {
		
		int result = 0;
		try {
			con = MySQLUtil.getOnlineConnection();
			int i=1;
			StringBuffer sql = new StringBuffer(" ");
			sql.append("UPDATE tbl_commodity   ");
			sql.append("SET                    ");
			sql.append("goods_id ="+goodsId+"       ");
			if(!"".equals(goodsName)&&goodsName!=null){
				sql.append(",goods_name =?        ");
			}
			if(!"".equals(goodsCategory)&&goodsCategory!=null){
			sql.append(",goods_category_id =?  ");
			}
			if(!"".equals(goodsPrice)&&goodsPrice!=null){
			sql.append(",goods_price =?        ");
			}
			if(!"".equals(goodsStock)&&goodsStock!=null){
			sql.append(",goods_stock =?        ");
			}
			if(!"".equals(goodsDiscount)&&goodsDiscount!=null){
			sql.append(",goods_discount =?     ");
			}
			if(!"".equals(goodSpec)&&goodSpec!=null){
			sql.append(",goods_spec =?        ");
			}
			if(!"".equals(goodsDescribe)&&goodsDescribe!=null){
			sql.append(",goods_describe =?     ");
			}
			if(!"".equals(goodsKeys)&&goodsKeys!=null){
			sql.append(",goods_keys =?         ");
			}
//			if(!"".equals(goodsmage1)&&goodsmage1!=null){
//			sql.append(",goods_image1 =?      ");
//			}
//			if(!"".equals(goodsmage2)&&goodsmage2!=null){
//			sql.append(",goods_image2 =?       ");
//			}
//			if(!"".equals(goodsmage3)&&goodsmage3!=null){
//			sql.append(",goods_image3 =?       ");
//			}
//			if(!"".equals(goodsmage4)&&goodsmage4!=null){
//			sql.append(",goods_image4 =?       ");
//			}
//			if(!"".equals(goodsmage5)&&goodsmage5!=null){
//			sql.append(",goods_image5 =?        ");
//			}
			sql.append("WHERE                      ");
			sql.append("goods_id = ?              ");
			ps = con.prepareStatement(sql.toString());
			if(!"".equals(goodsName)&&goodsName!=null){
			ps.setString(i, goodsName);
			i++;
			}
			if(!"".equals(goodsCategory)&&goodsCategory!=null){
			ps.setInt(i, Integer.parseInt(goodsCategory));
			i++;
			}
			if(!"".equals(goodsPrice)&&goodsPrice!=null){
			ps.setDouble(i, Double.parseDouble(goodsPrice));
			i++;
			}
			if(!"".equals(goodsStock)&&goodsStock!=null){
			ps.setInt(i,Integer.parseInt( goodsStock));
			i++;
			}
			if(!"".equals(goodsDiscount)&&goodsDiscount!=null){
			ps.setDouble(i, Double.parseDouble(goodsDiscount));
			i++;
			}
			if(!"".equals(goodSpec)&&goodSpec!=null){
			ps.setString(i, goodSpec);
			i++;
			}
			if(!"".equals(goodsDescribe)&&goodsDescribe!=null){
			ps.setString(i, goodsDescribe);
			i++;
			}
			if(!"".equals(goodsKeys)&&goodsKeys!=null){
			ps.setString(i, goodsKeys);
			i++;
			}
//			InputStream inStream;
//			File file ;
//			if(!"".equals(goodsmage1)&&goodsmage1!=null){
//			 inStream=ImageUtil.getByteImage(goodsmage1);
//			 file = new File(goodsmage1);
//			ps.setBinaryStream(i, inStream,(int)file.length());
//			i++;
//			}
//			if(!"".equals(goodsmage2)&&goodsmage2!=null){
//			inStream=ImageUtil.getByteImage(goodsmage2);
//			file= new File(goodsmage2);
//			ps.setBinaryStream(i, inStream,(int)file.length());
//			i++;
//			}
//			if(!"".equals(goodsmage3)&&goodsmage3!=null){
//			inStream=ImageUtil.getByteImage(goodsmage3);
//			file= new File(goodsmage3);
//			ps.setBinaryStream(i, inStream,(int)file.length());
//			i++;
//			}
//			if(!"".equals(goodsmage4)&&goodsmage4!=null){
//			inStream=ImageUtil.getByteImage(goodsmage4);
//			file= new File(goodsmage4);
//			ps.setBinaryStream(i, inStream,(int)file.length());
//			i++;
//			}
//			if(!"".equals(goodsmage5)&&goodsmage5!=null){
//			inStream=ImageUtil.getByteImage(goodsmage5);
//			file= new File(goodsmage5);
//			ps.setBinaryStream(i, inStream,(int)file.length());
//			i++;
//			}
			ps.setString(i, goodsId);
			if(i>1){
			result = ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return result > 0 ? true : false;
		
		
	}

}
