package com.mnsn.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * @AUTHER LiuLonglong
 * @Motto Goals determine what you are going to be.
 * @URL http://www.cnblogs.com/mvilplss/
 * @Time 下午04:02:13
 * @Version
 */
public class MyUtils {
	
	
	public static Date getCreate_time_Date(){
		Date date = new Date();
		try{
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	

	/**
	 * 格式化时间1 格式为 YYYY-MM-DD
	 * 
	 * @return
	 */
	public static String getDataYmd1() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * 格式化时间2 格式为 YYYY-MM-DD HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDataYmdhms1() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	/**
	 * 格式化时间3 格式为 yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getDataYmdhms2() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	/**
	 * 判断字符串不为null并且不为""
	 * 
	 * @param str
	 * @return 如果成立返回 true
	 */
	public static boolean notEmpty(String str) {
		boolean result = false;
		if (str != null && !"".equals(str)) {
			result = true;
		}
		return result;
	}

	/**
	 * 将对象中等于null的String类型属性转化为""字符串 并将所有字符串trim
	 * 
	 * @param object
	 * @author 刘龙龙
	 * @return 处理过的对象
	 */
	@SuppressWarnings("unchecked")
	public static Object removeNull(Object object) {
		Class objectClass = object.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		for (Field field : fields) {
			try {
				Type type = field.getGenericType();
				if (type.equals(String.class)) {
					Method method = objectClass.getMethod("get"
							+ change(field.getName()));
					Object obj = method.invoke(object);
					if (obj == null) {
						Method mtd = objectClass.getMethod("set"
								+ change(field.getName()),
								new Class[] { String.class });
						mtd.invoke(object, new Object[] { "" });
					} else {
						Method mtd = objectClass.getMethod("set"
								+ change(field.getName()),
								new Class[] { String.class });
						mtd.invoke(object,
								new Object[] { ((String) obj).trim() });
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return object;

	}

	// 将字符串第一个字符大写
	private static String change(String src) {
		if (src != null) {
			StringBuffer sb = new StringBuffer(src);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}
	}
	// code1转码成code2
	public static String toEncode(String str){
		try {
			str = new String(str.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	// 判断编码 是就返回false 不是就返回true;
	public static Boolean checkEncode(String str,String encode){
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {  
			     return false;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}     
		return true;
	}
	/**
	 * to save log
	 * @param filePath
	 * @param fileName
	 * @param logContent
	 */
	public static void saveLog(String filePath,String fileName,String logContent){
		try {
			File path = new File(filePath);
			if (!path.exists()) {
				path.mkdir();
			}
			File file = new File(path + "/" + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter br = new BufferedWriter(new FileWriter(file, true));
			br.write(logContent);
			br.newLine();
			br.flush();
			br.close();
			System.out.println(file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 按周期删除文件
	 * 
	 * @param dir
	 * @param content
	 */
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
			System.out.println(file.getAbsolutePath());
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
	
	public static String HttpPost(String url){
		HttpClient hc = null;
		String data = "";
		try {
			hc = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			HttpResponse response = hc.execute(post);
			HttpEntity resEntity = response.getEntity();
			data = EntityUtils.toString(resEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	

}
