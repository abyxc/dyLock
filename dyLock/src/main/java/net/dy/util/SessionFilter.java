package net.dy.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class SessionFilter implements Filter {

	private static Logger logger = Logger.getLogger(SessionFilter.class);

	public static final String[] unLoginPath = { "/chncodePDF/doc-to-pdf/pdf.do", "/chncodePDF/ppt-to-pdf/pdf.do", "/chncodePDF/excel-to-pdf/pdf.do", "/chncodePDF/txt-to-pdf/pdf.do", "/chncodePDF/pdf/pdf.do" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		logger.info("用户于:"+UtilFile.currentTimestamp()+"进入网站,IP: " + req.getRemoteHost());
		HttpServletRequest request = (HttpServletRequest) req;
		
		
		
		
		HttpServletResponse response = (HttpServletResponse) res;
		logger.info("URL******************************:" + request.getRequestURI());
//		System.out.println("***********************:"+req.getParameter("creareTime"));
		if(UtilFile.getCookieByName(request, "cookies") == null)
		UtilFile.addCookie(response, "cookies", UtilFile.randomUUID(), 60 * 60 * 10 );//10h失效
//		if(request.getRequestURI().contains(s))
//		if(Arrays.asList(unLoginPath).contains(request.getRequestURI())){
//		req = java.net.URLEncoder.
			chain.doFilter(req, res);
//		}else {
//			response.sendRedirect("/chncodePDF/index.jsp");
//		}
//		else 
//		System.out.println(UtilFile.getCookieByName(request, "cookies").getValue());
//		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
//		if (uri.indexOf("jsp")>0) {
//			chain.doFilter(req, res);
//		} else {
//			response.sendRedirect("/chncodePDF/unauthor.jsp");
//		}

	}
	

	@Override
	public void destroy() {
	}

}