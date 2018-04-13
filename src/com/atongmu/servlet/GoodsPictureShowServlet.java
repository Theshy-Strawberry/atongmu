package com.atongmu.servlet;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class PictureShowServlet
 */
@WebServlet("/GoodsPictureShowServlet")
public class GoodsPictureShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodsPictureShowServlet() {
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
		CommonUtil.logger.info("【mobile】"+"into GoodsPictureShowServlet,doGet");
		String id = request.getParameter("id");
		String goods_id = request.getParameter("goods_id");
			if (request.getParameter("id") != null) {
				response.setContentType("image/jpeg");
				InputStream is = query_getPhotoImageBlob(id,goods_id);
				if (is != null) {
					try {
						is = new BufferedInputStream(is);
						BufferedImage bi = ImageIO.read(is);
						OutputStream os = response.getOutputStream();
						JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
						encoder.encode(bi);
						os.close();
						is.close();
					} catch (IOException e) {
						// TODO: handle exception
						System.err.println(e.getMessage());
					}
				}
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static InputStream query_getPhotoImageBlob(String id,String goods_id) {
		CommonUtil.logger.info("【mobile】"+"into GoodsPictureShowServlet,query_getPhotoImageBlob");
		String sql = "";
		switch (id) {
		case "1":
			sql = "select goods_image1 from  tbl_commodity where goods_id = "+ goods_id;
			break;
		case "2":
			sql = "select goods_image2 from tbl_commodity where goods_id = "+ goods_id ;
			break;
		case "3":
			sql = "select goods_image3 from tbl_commodity where goods_id = "+ goods_id;
			break;
		case "4":
			sql = "select goods_image4 from tbl_commodity where goods_id = "+ goods_id;
			break;
		case "5":
			sql = "select goods_image5 from tbl_commodity where goods_id = "+ goods_id;
			break;
		default:
			break;
		}
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		InputStream result = null;
		try {
			con = MySQLUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				switch (id) {
				case "1":
					result = rs.getBlob("goods_image1").getBinaryStream();
					break;
				case "2":
					result = rs.getBlob("goods_image2").getBinaryStream();
					break;
				case "3":
					result = rs.getBlob("goods_image3").getBinaryStream();
					break;
				case "4":
					result = rs.getBlob("goods_image4").getBinaryStream();
					break;
				case "5":
					result = rs.getBlob("goods_image5").getBinaryStream();
					break;
				default:
					break;
				}

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, stmt, con, null);
		}
		return result;
	}

}
