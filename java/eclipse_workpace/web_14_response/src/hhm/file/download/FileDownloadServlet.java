package hhm.file.download;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public class FileDownloadServlet extends HttpServlet {
	
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//什么情况下需要文件下载	--->	浏览器不能提供浏览的文件就会默认下载
		//什么情况下需要在服务器端编写文件下载代码	--->	浏览器可以解析的文件需要编写
		String fileName = request.getParameter("filename");
		
		//解决中文文件下载后的乱码问题
		fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
		
		//给文件名编码，这样在服务器解码就不会出现乱码
		String userAgent = request.getHeader("User-Agent");
		String newName;
		if (userAgent.contains("MSIE")) {
			// IE浏览器
			newName = URLEncoder.encode(fileName, "utf-8");
			newName = newName.replace("+", " ");
		} else if (userAgent.contains("Firefox")) {
			// 火狐浏览器
			BASE64Encoder base64Encoder = new BASE64Encoder();
			newName = "=?utf-8?B?" + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
		} else {
			// 其它浏览器
			newName = URLEncoder.encode(fileName, "utf-8");				
		}
		
		//得到文件MIME类型
		String mimeType = getServletContext().getMimeType(fileName);
		response.setContentType(mimeType);
		
		//设置文件下载的类型		--->	客户端通过文件的MIME区分类型是否应该下载
		response.setHeader("Content-Disposition", "attachment;filename="+newName);
		
		
		
		
		String realPath = getServletContext().getRealPath(fileName);
		//获得文件输入流
		FileInputStream inputStream = new FileInputStream(realPath);
		ServletOutputStream outputStream = response.getOutputStream();
		int len = 0;
		byte[] bite = new byte[3096];
		while ((len=inputStream.read(bite))!=-1) {
			outputStream.write(bite,0,len);
		}
		inputStream.close();
		outputStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}