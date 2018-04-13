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

import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.atongmu.util.ImageUtil;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class ReleaseGoodsServlet
 */
@WebServlet("/ReleaseGoodsServlet")
public class ReleaseGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement statement = null;
	private ResultSet rs = null;
	int add=1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleaseGoodsServlet() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String goodsName=null;//=request.getParameter("goodsName");
		String goodsCategory=null;//request.getParameter("goodsCategory");
		String goodsPrice=null;//request.getParameter("goodsPrice");
		String goodsStock=null;//request.getParameter("goodsStock"); 
		String goodsDiscount=null;//request.getParameter("goodsDiscount");
		String goodSpec=null;//request.getParameter("goodSpec");
		String goodsDescribe=null;//request.getParameter("goodsDescribe");
		String goodsKeys=null;//request.getParameter("goodsKeys");
		
		
		//======================================
		DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(4096);// 设置缓冲区大小，这里是4kb
        // Location to save data that is larger than maxMemSize.
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        // Parse the request to get file items.
        List<FileItem> fileItems = null;
        List<FileItem> fileItems2 = null;
        try {
			fileItems = upload.parseRequest(request);
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
				
			}
			
			boolean flag=releaseGoods(goodsName,goodsCategory,goodsPrice, goodsStock, goodsDiscount, 
					goodSpec, goodsDescribe, goodsKeys);
       
		if(flag){
			int id=getId();
			Iterator ite2  = fileItems.iterator();
		    while (ite2 .hasNext()) {
				item=(FileItem)ite2.next();
				
				
				if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage1"))
			    {
					if(item.getSize()==0){
						continue;
					}
				InputStream ins=	item.getInputStream();
				File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
				File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+id+"_1"+".jpg");
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
				if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage2"))
				{
					if(item.getSize()==0){
						continue;
					}
					InputStream ins=	item.getInputStream();
					File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
					File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+id+"_2"+".jpg");
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
				if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage3"))
				{
					if(item.getSize()==0){
						continue;
					}
					InputStream ins=	item.getInputStream();
					File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
					File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+id+"_3"+".jpg");
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
				if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage4"))
				{
					if(item.getSize()==0){
						continue;
					}
					InputStream ins=	item.getInputStream();
					File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
					File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+id+"_4"+".jpg");
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
				if(!(item.isFormField()) && ((String)item.getFieldName()).equals("goodsimage5"))
				{
					if(item.getSize()==0){
						continue;
					}
					InputStream ins=	item.getInputStream();
					File path=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images");
					File outFile=new   File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+id+"_5"+".jpg");
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
		}
		else{
			System.out.println("fail");
		}
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		//======================================end
    	response.sendRedirect("ShowGoodsListServlet");
	}
       
	private int getId() {
		int id=0;
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer("select max(goods_id) from tbl_commodity  ");
			statement = con.createStatement();
			rs = statement.executeQuery(sql.toString());
			while (rs.next()) {
				 id=rs.getInt(1);
			}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		MySQLUtil.closeAll(rs, statement, con, ps);
	}
		return id;
	}
//复制文件
	private void upload(int id,String path) throws IOException{
		
		 FileInputStream fis = new FileInputStream(path);
		    String ext=path.substring(path.lastIndexOf(".")).toLowerCase();  
	        byte[] buf = new byte[(int) path.length()];
	        fis.read(buf);
	        fis.close();
	        File file2 = new File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+id+"_"+add+ext);
	      //  fileItem.write(new File("C:\\websoft\\tomcat\\Tomcat7_site\\webapps\\goods_images\\"+id+"_"+add+ext));
	        add++;
	        if(file2.exists()){
	            file2.delete();
	        }
	        FileOutputStream fos = new FileOutputStream(file2);
	        fos.write(buf);
	        fos.flush();
	        fos.close();
	}
	
	private boolean releaseGoods(String goodsName,String goodsCategory,String goodsPrice,String goodsStock,String goodsDiscount,String goodSpec,
			String goodsDescribe,String goodsKeys) {
		int result = 0;
		Double integral=Double.parseDouble(goodsPrice)*0.05;
		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer sql = new StringBuffer("INSERT INTO tbl_commodity ");
			sql.append("(goods_name, ");
			sql.append(" goods_category_id, ");
			sql.append(" goods_price,");
			sql.append(" goods_stock,");
			sql.append(" goods_discount,");
			sql.append(" goods_spec,");
			sql.append(" goods_describe,");
			sql.append(" goods_keys,");
			sql.append(" goods_status,");
			sql.append(" add_date,");
			sql.append(" goods_integral,");
			sql.append(" goods_sales_volume");
			sql.append(" ) VALUES (");
			sql.append("?,?,?,?,?,?,?,?,'B002',NOW(),?,0)");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, goodsName);
			ps.setInt(2, Integer.parseInt(goodsCategory));
			ps.setDouble(3, Double.parseDouble(goodsPrice));
			ps.setInt(4,Integer.parseInt( goodsStock));
			ps.setDouble(5, Double.parseDouble(goodsDiscount));
			ps.setString(6, goodSpec);
			ps.setString(7, goodsDescribe);
			ps.setString(8, goodsKeys);
			ps.setDouble(9, integral);
//			InputStream inStream=ImageUtil.getByteImage(goodsmage1);
//			File file = new File(goodsmage1);
//			ps.setBinaryStream(9, inStream,(int)file.length());
//			inStream=ImageUtil.getByteImage(goodsmage2);
//			file= new File(goodsmage2);
//			ps.setBinaryStream(10, inStream,(int)file.length());
//			inStream=ImageUtil.getByteImage(goodsmage3);
//			file= new File(goodsmage3);
//			ps.setBinaryStream(11, inStream,(int)file.length());
//			inStream=ImageUtil.getByteImage(goodsmage4);
//			file= new File(goodsmage4);
//			ps.setBinaryStream(12, inStream,(int)file.length());
//			inStream=ImageUtil.getByteImage(goodsmage5);
//			file= new File(goodsmage5);
//			ps.setBinaryStream(13, inStream,(int)file.length());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, ps);
		}
		return result > 0 ? true : false;
		
		
	}

}
