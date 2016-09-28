package net.dy.controller;

import java.util.List;

import javax.tools.Tool;

import net.dy.common.BaseAction;
import net.dy.domain.Log;
import net.dy.util.ToolClass;
import net.dy.util.UtilFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 E-mail:elizhiyong@163.com 
 * @version 创建时间：2016年8月26日 下午5:05:18 
 * {@link http://www.chncode.net }
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/log")
public class LogController extends BaseAction<Log>  {

	private static Logger LOG = LoggerFactory.getLogger(LogController.class);
	
	
	@RequestMapping("/common")
	public String common(){
		return "/common/common";
	}
	
//	@ResponseBody
	@RequestMapping("/logList")
	public String list(Model model, @RequestParam(value = "pageNum", required = false ,defaultValue="-1" ) int pageNum,Log log){
		PageHelper.startPage(pageNum, 10);
		List<Log> list = logService.selectList(log);
		  PageInfo<Log> page = new PageInfo<Log>(list);
		  String jsonString = JSON.toJSONString(list);
			System.out.println(jsonString);
			System.out.println(JSON.toJSONString(page));
			
		  model.addAttribute("page", page);
		  model.addAttribute("id", log.getId());
		  model.addAttribute("creareTime", log.getCreareTime());
		return "/logDemo/list";
	}
	
	@RequestMapping("/ajaxList")
	public String ajaxList( @RequestParam(value = "pageNum", required = false ,defaultValue="-1" ) int pageNum,Log log){
		PageHelper.startPage(pageNum, 10);
		List<Log> list = logService.selectList(log);
		  PageInfo<Log> page = new PageInfo<Log>(list);
		  String jsonString = JSON.toJSONString(list);
			System.out.println(jsonString);
			return jsonString;
	}
	@RequestMapping("/update")
	public String update(Model model, @RequestParam(value = "id", required = true  ) String id){
		Log list = logService.selectId(id);
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);
		model.addAttribute("list", list);
		return "/logDemo/update";
	}
	
	@RequestMapping("/saveUpadte")
//	@ResponseBody
	public String saveUpadte(Model model,Log log){
		logService.updateId(log);
		PageHelper.startPage(1, 10);
		List<Log> list = logService.select();
		 PageInfo<Log> page = new PageInfo<Log>(list);
		  model.addAttribute("page", page);
		return "/logDemo/list";
	}
	
	@RequestMapping("/deleteId")
	public String deleteId(Model model, String id){
		logService.deleteId(id);
		PageHelper.startPage(1, 10);
		List<Log> list = logService.select();
		 PageInfo<Log> page = new PageInfo<Log>(list);
		  model.addAttribute("page", page);
		return "/logDemo/list";
	}
	
	@RequestMapping("/insert")
	public String insert(){
		
		return "/logDemo/insert";
	}
	
	@RequestMapping("/saveInsert")
	public String saveInsert(Model model,Log log){
		if(log != null){
			log.setId(UtilFile.randomUUID());
			logService.insert(log);
		}
		PageHelper.startPage(1, 10);
		List<Log> list = logService.select();
		 PageInfo<Log> page = new PageInfo<Log>(list);
		  model.addAttribute("page", page);
		return "/logDemo/list";
	}
	@ResponseBody
@RequestMapping("/updateAjax")
public String updateAjax(String id){
	Log list = logService.selectId(id);
	List<Log> lists = logService.select();
	String jsonString = JSON.toJSONString(list);
	
//	ToolClass tool = new ToolClass();
//	tool.setCode("100");
//	tool.getDate().add(list);
//	Log data = new Log();
//	data.getData().add(list);
	System.out.println(JSON.toJSONString(jsonObject("100", list)));
//	System.out.println(JSON.toJSONString(lists));
//	System.out.println(jsonString);
	return JSON.toJSONString(jsonObject("100", list));
}
	
//	@ResponseBody
//	@RequestMapping("/list")
//	public String lists(Model model,@RequestParam(value = "pageNum", required = false ,defaultValue="-1" ) int pageNum,String id,String creareTime){
//		PageHelper.startPage(pageNum, 10);
//		List<Log> list = logService.selectList(log);
//		PageInfo<Log> page = new PageInfo<Log>(list);
//		model.addAttribute("page", page);
//		return "/common/starter";
//	}
	
}
