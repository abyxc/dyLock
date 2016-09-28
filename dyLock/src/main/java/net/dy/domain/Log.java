package net.dy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年8月29日 上午9:34:52 
 * {@link http://www.chncode.net }
 */
@SuppressWarnings("serial")
public class Log implements Serializable{

	private String id;
	
	private String creareTime;

    private String logTxt;

    private String ip;
    
    private String time;
    
    private String cookies;
    
    
    



	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreareTime() {
		return creareTime;
	}

	public void setCreareTime(String creareTime) {
		this.creareTime = creareTime;
	}

	public String getLogTxt() {
		return logTxt;
	}

	public void setLogTxt(String logTxt) {
		this.logTxt = logTxt;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", creareTime=" + creareTime + ", logTxt=" + logTxt + ", ip=" + ip + ", time=" + time + ", cookies=" + cookies + "]";
	}
   
}
