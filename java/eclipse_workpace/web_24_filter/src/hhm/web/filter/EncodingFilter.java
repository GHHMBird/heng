package hhm.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		request.setCharacterEncoding("UTF-8");
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		//装饰者模式，对request的getParameter方法进行增强
		StrongRequest strongRequest = new StrongRequest(req);
		
		chain.doFilter(strongRequest, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

class StrongRequest extends HttpServletRequestWrapper{
	public StrongRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	private HttpServletRequest request;

	@Override
	public String getParameter(String name) {
		String text = request.getParameter(name);
		try {
			text = new String(text.getBytes("iso8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;
	}
}
