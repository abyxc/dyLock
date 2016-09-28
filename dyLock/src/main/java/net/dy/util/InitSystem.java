package net.dy.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author ll
 * <h3>优雅的代码需要愉快的心情</h3>
 * 2015-1-17下午01:32:27
 * http://www.cnblogs.com/mvilplss/
 */
public class InitSystem implements ServletContextListener {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public void contextDestroyed(ServletContextEvent sce) {

	}
	public void contextInitialized(ServletContextEvent sce) {
		String basePath = sce.getServletContext().getInitParameter("basePath");
		String appPath = sce.getServletContext().getRealPath("/");
		sce.getServletContext().setAttribute("basePath", basePath);
		log.log(Level.INFO, "初始化系统路径：" + appPath);
		log.log(Level.INFO, "初始化项目路径：" + basePath);

//		try {
//			ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
//		} catch (Exception e) {
//			log.log(Level.WARNING, "加载权限失败 >-<|| ...");
//			e.printStackTrace();
//		}
		log.log(Level.INFO, "恭喜你！系统启动成功，祝您好心情");
	}
}
