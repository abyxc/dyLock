/**
 * 
 */
package com.mnsn.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHER LiuLonglong
 * @Motto Goals determine what you are going to be.
 * @URL http://www.cnblogs.com/mvilplss/
 * @Time 2015-2-3上午09:28:20
 */
public class InputCache {
	private static Map<Integer, InputStream> IC = new HashMap<Integer, InputStream>();
	private static Integer cursor = 0;

	public static void putIs(InputStream is) {
		IC.put(getInCursor(), is);
	}

	public static InputStream getIs() {
		InputStream is = IC.get(getOutCursor());
		try {
			is.available();
		} catch (IOException e) {
			getIs();
		}
		return is==null? IC.get(getOutCursor()):is; 
	}

	private static Integer getInCursor() {
		System.out.println("放进去："+cursor);
		if(cursor==1000){
			cursor = 0;
		}else{
			cursor++;
		}
		return cursor;
	}

	private static Integer getOutCursor() {
		System.out.println("取出来："+(cursor==0? 1000:cursor-1));
		return cursor==0? 1000:cursor-1;
	}

}
