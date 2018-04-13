package com.atongmu.servlet;

import java.io.File;
import java.io.FileInputStream;
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

import com.atongmu.bean.Tbl_code_master;
import com.atongmu.bean.Tbl_manager;
import com.atongmu.util.ImageUtil;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class GoodsPicUploadServlet
 */
@WebServlet("/GoodsPicUploadServlet")
public class GoodsPicUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodsPicUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		request.setAttribute("image_id_list_test", selectManager());
		request.getRequestDispatcher("web_browser/goodsimageupload.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String filename = request.getParameter("image");
		if(!filename.equals("")){
			upload(filename);
		}
		response.sendRedirect("GoodsPicUploadServlet");
	}

	/**
	 * 上传文件到数据表
	 * 
	 * @param filePath
	 */
	private void upload(String filePath) {
		FileInputStream inStream = null;
		int lastIndexOfNode = filePath.lastIndexOf(".");
		int lastIndexOfBacklash = filePath.lastIndexOf("\\");
		String fileName = filePath.substring(lastIndexOfBacklash + 1, lastIndexOfNode);
		System.out.println("filePath=" + filePath);
		System.out.println("fileName=" + fileName);
		int affect = 0;
		String sql = "insert into tbl_commodity(goods_image1) values(?)";
		Connection con = null;
		PreparedStatement insertPstmt = null;
		try {
			con = MySQLUtil.getConnection();
			insertPstmt = con.prepareStatement(sql);

			inStream = ImageUtil.getByteImage(filePath);
			File file = new File(filePath);
			// 设置二进制流参数
			insertPstmt.setBinaryStream(1, inStream, (int)file.length());
			affect = insertPstmt.executeUpdate();
			System.out.println("affect=" + affect);
			if (affect == 1) {
				System.out.println("文件(" + filePath + ")上传成功");
			}

		} catch (SQLException e) {
			// System.out.println("上传文件失败，原因向数据表添加记录出错: " + e);
			e.printStackTrace();
		} finally {
			// 回收IO资源，自定义的方法
			MySQLUtil.closeAll(null, null, con, insertPstmt);
		}
	}
	public List<Integer> selectManager() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs  = null;
        List<Integer> image_id_list_test = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("SELECT goods_id from tbl_commodity ");
			statement = con.createStatement();
			rs = statement.executeQuery(sql.toString());
			image_id_list_test = new ArrayList<Integer>();
			while (rs.next()) {
				image_id_list_test.add(rs.getInt("goods_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return image_id_list_test;
	}

}
