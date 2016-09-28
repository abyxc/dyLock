<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>公证事项类型</title>
  <script type="text/javascript">

    function openhref(src,m){
            src=encodeURI(encodeURI(src)); 
            if(m==0){
		       $(".content-right").load(src);                     
            }else if(m==1){
	            if(confirm("确定删除吗？")){
		           $.ajax({   
				         async:false,  	           	       		
					     type:"post",
			             url:src,	 				       		          
			             success:function(d) { 
			                     $(".content-right").html(d);
			             }
			        });   	         
		         }
            }else if(m==2){
	           $.ajax({   
			         async:false,  	           	       		
				     type:"post",
		             url:src,	 				      
      		         data:$("#Form1").serialize(),	      		          
			         success:function(d) {
			                 $(".content-right").html(d);			            
			         }                        
               });
            }
        
    }  
 
          
  </script>
</head>
  <body >
	<div class="nav-header">
	    <p class="new-add">公证知识</p>
	</div>
	<div class="searchsty">   
	      <div style="float: left;">
	        <input type="button" class="zhenjian-add"onclick="openhref('${basePath}/manage/messages/releasemsg.jsp',0)"  value="增加"/>
	      </div>    	      
	  	  <form id="Form1" action="${basePath}/manage/messages/list.action?ids=2" method="post">
	   	  	<div class="xinxi-type">
	   	  	<span class="role-namen">
	   		 	<input type="text" name="searchTearm1" value="${searchTearm1}" placeholder="标题" />
	   		</span>
	            <input type="button" value="查询" class="zhenjian-add" onclick="openhref('${basePath}/manage/messages/list.action?ids=2',2)" 
	                   style="margin-left: 0px;height:30px;line-height: 28px;"/>
	      	</div>
	  	  </form>      
	</div>
    <div class="zjtype">
    <table class="zjtype-table" cellspacing="0" cellpadding="0">
            <tr class="table-th">
                <td width="40%">标题</td>
                <td>时间</td>
				<td>操作</td>
            </tr> 
            <s:iterator value="pageBean.dataList"  status="sta" >
           	<tr <s:if test="#sta.index%2 == 0">style="background-color: #FFFFFF;"</s:if> >
                <td style="width: 40%;">	                	                  
                   ${title}	                	                						
				</td>
                <td style="width: 30%;">	                	                  
                   <s:date name="create_time"/>                	                						
				</td>					
                <td style="width: 30%;">          		                	                
                    <a href="#" class="role-look"onclick="openlink('${basePath}/manage/messages/detailmsg.action?ids=${id}&meg=0&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)" >&nbsp;查看</a>
                	<a href="#" class="role-edit" onclick="openlink('${basePath}/manage/messages/detailmsg.action?ids=${id}&meg=1&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)">编辑</a>
                	<a href="#" class="role-del" onclick="openhref('${basePath}/manage/messages/update.action?ids=${id}&meg=1&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}',1)">删除</a>
                </td>
           	</tr>
            </s:iterator>
        </table>
        </div>
		<div class="panelBar" style="background-color:FFFFFF;width: 100%;height: 25px;">
		   <div id="page" style="float: right;background-color: #FFFFFF;">${page}</div>
		</div>
  </body>
 
</html>
