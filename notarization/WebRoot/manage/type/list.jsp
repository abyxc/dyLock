<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>公证事项类型</title>
  <script type="text/javascript">
    function submithref(id){
	           $.ajax({   
			         async:false,  	           	       		
				     type:"post",
		             url:'${basePath}/manage/type/listpapers.action?type.id='+id,	 				      
      		         data:$("#f"+id).serialize(),	      		          
		        success:function(d) { 
		             $(".content-right").html(d);
		        }
		        });          
    }
    function openhref(src,m){
            if(m==0){
	           $.ajax({   
			         async:false,  	           	       		
				     type:"post",
		             url:src,	 				       		          
		        success:function(d) { 
		             $(".content-right").html(d);
		        }
		        });              
            
            }else{
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
            }  
        
    }    
 
  </script>
</head>
  <body >
	<div class="nav-header">
	    <p class="new-add">公证事项类型</p>
	</div>
	<div class="searchsty">
    		<div style="float: left;">
		        <input type="button" class="zhenjian-add" onclick="openhref('${basePath}/manage/type/uppapers.action?ids=0',0)"  value="增加"/>
		    </div>
	   	  	<div class="xinxi-type" style="height:50px">
	   	  	<span class="role-namen">
	   		</span>
	      	</div>
    	</div>
	<div class="zjtype">
	    <table class="zjtype-table" cellspacing="0" cellpadding="0">
	        <tr class="table-th">
	            <td>事项名称</td>
	            <td>类型</td>
	            <td>备注</td>
	            <td>操作</td>
	        </tr>
	            <s:iterator value="pageBean.dataList"  status="sta" id="obj">
	            
	            	<tr <s:if test="#sta.index%2 == 0">style="background-color: #FFFFFF;"</s:if> >
		                <td style="width: 20%;">                	                  
		                   ${gz_name}	                	                						
						</td>
						<td style="width: 20%;">                	                  
		                   <s:if test="type == 0">国内</s:if>  
		                   <s:elseif test="type == 1">涉外（含港澳台）</s:elseif>              	                						
						</td>
						<td style="width: 20%;">
							${remark}
						</td>
		                <td style="width: 40%;">
	
		                	<form id="f${id}" action="" method="post">
		                	 <input type="hidden" name="type.gz_name" value="${gz_name}">
		                	</form>	 	                		                	                
	                        <a href="#" class="role-look" onclick="submithref('${id}')" >&nbsp;查看</a>
		                	<a href="#" class="role-edit" onclick="openhref('${basePath}/manage/type/toupdate.action?type.id=${id}&ids=1',0)" >编辑</a>	          
		                	<a href="#" class="role-del" onclick="openhref('${basePath}/manage/type/delete.action?type.id=${id}',1)">删除</a>
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
