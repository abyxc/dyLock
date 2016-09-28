package net.dy.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月12日 上午9:50:51 
 * {@link http://www.chncode.net }
 */
public class ToolClass {
	
	private String code;
	private List<Object> data = new ArrayList<>();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public List<Object> getDate() {
		return data;
	}
	public void setDate(List<Object> date) {
		this.data = date;
	}
	@Override
	public String toString() {
		return "tool [code=" + code + ", date=" + data + "]";
	}
}
