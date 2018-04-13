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

import com.atongmu.util.MySQLUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class PictureShowServlet
 */
@WebServlet("/PictureShowServlet")
public class PictureShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PictureShowServlet() {
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
		if (request.getParameter("id") != null) {
			response.setContentType("image/jpeg");
			InputStream is = query_getPhotoImageBlob(request.getParameter("id").toString());
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

	public static InputStream query_getPhotoImageBlob(String id) {
		String sql = "select image from tbl_image where img_id = " + id;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		InputStream result = null;
		try {
			con = MySQLUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getBlob("image").getBinaryStream();
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, stmt, con, null);
		}
		return result;
	}

}
