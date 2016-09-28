<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>公证事项类型</title>
</head>
  <body >
  
	<div class="nav-header">
	    <p class="new-add">知识详情</p>
	</div>  
    <div class="zjtype">
    <table class="zjtype-table" cellspacing="0" cellpadding="0"style="text-align:center; ">
            <tr>
                <th ><br><b>${messages.title}</b></th>
            </tr>
            <tr>
                <td><s:date name="messages.create_time"/></td>
            </tr>            
            <tr>
           	    <td>       	         
           	       <div style="margin:auto;width:800px;word-wrap:break-word;text-align: left;margin-bottom: 100px;font-size: 15px;float: center;"><pre>${messages.info}</pre></div>          	             	       
           	    </td>
            </tr>
<%--             <tr>
           	    <th>
           	    <s:iterator value="str"  status="sta" id="obj">
           	    ${obj}
           	    </s:iterator>
           	    </th>
            </tr>    --%>         
            <tr>
                <td>
				  <div class="bottom">
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" class="back"  onclick="openlink('${basePath}/manage/messages/list.action?ids=2&&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)"/>
				  </div>              
                </td>
            </tr>                
        </table>

    </div>
  </body>
</html>
