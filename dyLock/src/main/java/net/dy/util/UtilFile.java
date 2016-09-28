package net.dy.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilFile {
	private static Logger LOG = LoggerFactory.getLogger(UtilFile.class);
	public static final String HHMMSS = "hh:mm:ss";
	public static final String YYYYMM = "yyyyMMdd";
	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDDHH = "yyyyMMddHH";
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	public static final String MASSAGE_PORT = "http://www.lx198.com/sdk/send?";//短信接口
	public static final String ACC_NAME = "18166384543";
	public static final String ACC_PWD = "dy890129";
	
	public static String randomUUID(int len) {
		return UUID.randomUUID().toString().replace("-", "").substring(0, len);
	}
	public static String randomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static String currentHour(){
		return toString(new Date(), YYYYMMDDHH);
	}
	public static String currentTime() {
		return toString(new Date(), HHMMSS);
	}
	public static String currentDates() {
		return toString(new Date(), YYYYMM);
	}

	public static String currentDate() {
		return toString(new Date(), YYYYMMDD);
	}
	
	public static String currentTimestamp() {
		return toString(new Date(), YYYYMMDDHHMMSS);
	}
	public static String toString(Date date, String formatString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(date);
	}
	
	private static String  getH(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
		System.out.println("二个小时前的时间：" + df.format(calendar.getTime()));
		return df.format(calendar.getTime());
	}
	
	
	
	
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
		System.out.println(value);
	    Cookie cookie = new Cookie(name,value);
	    cookie.setPath("/");
	    if(maxAge>0)  cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	 
	 
	 
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
//　　　　　　　递归删除目录中的子目录下
            LOG.info("正在删除文件夹:" + dir);
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
	
	/**
	 * 定时器
	 */
	  // 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
	  // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
	  public static void timer() {
//		  cron
//	    Calendar calendar = Calendar.getInstance();
//	    calendar.set(Calendar.HOUR_OF_DAY, 20); // 控制时
//	    calendar.set(Calendar.MINUTE, 26);    // 控制分
//	    calendar.set(Calendar.SECOND, 0);    // 控制秒
	 
//	    Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的12：00：00
	 
	    Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	      public void run() {
	    	  LOG.info("请注意！！！***********定时器开始执行了*******" + currentTimestamp());
	    	String h = getH();
	    	  Properties prop = new Properties();
	    	  try {
				prop.load(UtilFile.class.getClassLoader().getResourceAsStream("filePath.properties"));
				deleteDir( new File(prop.getProperty("filePath") + prop.getProperty("excel-to-pdf") + "/" + h));
				deleteDir( new File(prop.getProperty("filePath") + prop.getProperty("txt-to-pdf") + "/" + h));
				deleteDir( new File(prop.getProperty("filePath") + prop.getProperty("ppt-to-pdf") + "/" + h));
				deleteDir( new File(prop.getProperty("filePath") + prop.getProperty("doc-to-pdf") + "/" + h));
				deleteDir( new File(prop.getProperty("filePath") + prop.getProperty("pwdPdf") + "/" + h));
			} catch (IOException e) {
				e.printStackTrace();
			}
	      }
	    }, 1000 * 60, 1000 * 60 * 10);// 这里设定将延时每天固定执行；60秒后定时器开始执行，隔10分钟执行一次
	  }
}
