package com.mnsn.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mnsn.project.privilege.Privilege;

/**
 * 
 * @author ll
 * <h3>优雅的代码需要愉快的心情</h3>
 * 2015-1-17下午01:32:35
 * http://www.cnblogs.com/mvilplss/
 */
public class PriCache {
	public static Collection<String> prisAll = new ArrayList<String>();

	public static void setCache(List<Privilege> headPrivileges) {
		for (Privilege privilege : headPrivileges) {
			prisAll.add(privilege.getCode());
		}
	}
}
