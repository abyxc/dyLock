package com.mnsn.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.mnsn.project.user.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * 系统日志拦截器
 * @AUTHER LiuLonglong
 * @Motto Goals determine what you are going to be.
 * @URL http://www.cnblogs.com/mvilplss/
 * @Time 下午04:09:37
 * @Version
 */
public class Dolog implements Interceptor {

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation ai) throws Exception {

		ai.addPreResultListener(new PreResultListener() {

			public void beforeResult(ActionInvocation ai, String arg1) {
				try {
					StringBuffer sb = new StringBuffer();
					sb.append(MyUtils.getDataYmdhms2() + ":");
					Map<String, Object> session = ai.getInvocationContext().getSession();
					User user = (User) session.get("loginUser");
					if (user != null) {
						sb.append("操作人：" + user.getName());
					} else {
						sb.append("操作人：系统未获取");
					}
					sb.append("类名：" + ai.getAction() + " ");
					sb.append("方法名：" + ai.getInvocationContext().getName()+ " ");
					Map<String, Object> map = ai.getInvocationContext().getParameters();
					Set<String> keys = map.keySet();
					sb.append("参数：");
					for (String key : keys) {
						sb.append(key + "=" + ((Object[]) map.get(key))[0]+ "#");
					}
					sb.append(" ");
					sb.append("执行结果：" + ai.getResultCode() + " ");
					String appPath = ServletActionContext.getServletContext().getRealPath("/");
					saveLog(appPath + "operLog", sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		return ai.invoke();
	}

	public static void saveLog(String dir, String content) {
		try {
			File path = new File(dir);
			if (!path.exists()) {
				path.mkdir();
			}
			File LogDir = new File(path + "/"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1));
			if (!LogDir.exists()) {
				LogDir.mkdir();
			}
			File file = new File(LogDir + "/"
					+ Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
					+ ".log");
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter br = new BufferedWriter(new FileWriter(file, true));
			br.write(content);
			br.newLine();
			br.flush();
			br.close();

			File LogDirOld = new File(
					path
							+ "/"
							+ (Calendar.getInstance().get(Calendar.MONTH) - 2 > 0 ? (Calendar
									.getInstance().get(Calendar.MONTH) - 2)
									: Calendar.getInstance()
											.get(Calendar.MONTH) + 10));
			if (LogDirOld.exists()) {
				File[] fileOlds = LogDirOld.listFiles();
				for (File f : fileOlds) {
					f.delete();
				}
				LogDirOld.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void destroy() {

	}

	public void init() {

	}
}
