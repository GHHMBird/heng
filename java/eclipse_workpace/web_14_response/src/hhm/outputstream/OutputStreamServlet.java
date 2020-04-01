package hhm.outputstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.File;

public class OutputStreamServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String realPath = getServletContext().getRealPath("picture.jpg");
		InputStream inputStream = new FileInputStream(realPath);
		
		ServletOutputStream stream = response.getOutputStream();
		int len = 0;
		byte[] bite = new byte[1024];
		while ((len=inputStream.read(bite))!=-1) {
			stream.write(bite,0,len);
		}
		inputStream.close();
		stream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}