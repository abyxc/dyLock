package net.dy.util;


/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年9月12日 上午9:50:51 
 * {@link http://www.chncode.net }
 */
public class ToolString {
	
	private String code;
	private String data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "tool [code=" + code + ", date=" + data + "]";
	}
}
