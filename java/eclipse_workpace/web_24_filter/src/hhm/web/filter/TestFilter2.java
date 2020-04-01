package hhm.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestFilter2 implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("i am TestFilter2");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
//		String name = fConfig.getFilterName();
//		fConfig.getInitParameter(arg0)
//		fConfig.getInitParameterNames()
//		fConfig.getServletContext()
	}
}