<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>添加</title>
    <script type="text/javascript">
  		function tijiao(src){
  	  		var name = $("#name").val().trim();
  	  		if(name.length == 0){
  	  	  		alert("请填写事项名称！");
  	  	  		return false;
  	  		}
  	  		var type=document.getElementsByName('type.type'); 
		    var tt=0;
		    for (var i=0;i<type.length ;i++ ){		        

	           if(type[i].checked == true ){
	              tt=1;		           
	           }
		            
		    }
		    if(tt==0){
  	  	  		alert("请选择事项类型");
  	  	  		return false;		    
		    }	  		
 		
		    var input = document.getElementsByTagName("input");
		    var aa=0;
		    for (var i=0;i<input.length ;i++ ){
		        
		        if(input[i].type=="checkbox"){
		           if(input[i].checked == true ){
		              aa=1;		           
		           }
		        }		            
		    }
		                
		    if(aa==0){
		       alert("请选择证件类型");
		       return false;
		       
		    }
	        $.ajax({   
			         async:false,  	           	       		
				     type:"post",
		             url:src,	 
		             data:$("#Form").serialize(),					       		          
		        success:function(d) { 
		             $(".content-right").html(d);
		        }
		     });   		  	
		  	
  		}
  		
        function cb(b){
          
		    var input = document.getElementsByTagName("input");
		
		    for (var i=0;i<input.length ;i++ )
		    {
		        if(input[i].type=="checkbox")
		            input[i].checked = b;
		    }
		}    		
        function check(xx){
                document.getElementById("cc"+xx).checked=true;
           
        }		
 
		 		
  	</script>
  	</head>
  	<body>
	<div class="nav-header">
	    <p class="new-add">知识详情</p>
	</div>  
    <div class="zjtype" style="background: #fff;">
  			<form action="${basePath}/manage/type/insert.action" method="post" id="Form">
                <input type="hidden" name="meg" value="${meg}">
                <input type="hidden" name="type.id" value="${type.id}">
  				<div style="margin:0 auto;width: 350px;background-color: white;">
<%--  					<div style="width: 100%;height: 50px;"></div>--%>
  					<div style="width: 100%;height: 50px;text-align: left;line-height: 50px;font-size: 14px;">
  						名称：<s:textfield name="type.gz_name" id="name" cssStyle="width: 300px;height: 25px;">${gz_name }</s:textfield>
  					</div>
  					<div style="width: 100%;height: 50px;text-align: left;line-height: 50px;font-size: 14px;">
  						类型：<s:radio id="type1"  list="#{'0':'国内','1':'涉外（含港澳台）'}" name="type.type" ></s:radio>
  					</div>
  					<div style="width: 100%;height: 50px;text-align: left;font-size: 14px;">
  						备注：<s:textarea name="type.remark" cssStyle="height:50px;width: 300px;">${remark }</s:textarea>
  					</div>

   					<div style="width: 100%;text-align: left;font-size: 14px;">
   					<br>
   					        选择所需证件 ：<br>
	   					   <div class="div_list">
						        <table cellpadding="0" cellspacing="0" style="width:100%;margin-top:3px;line-height: 25px;border: 1px solid #8A8A8A;">
		
						            <s:iterator value="pictureNames"  status="sta" >
						            	<tr <s:if test="#sta.index%2 == 0">style="background-color: #EBEBEB;"</s:if> >
						            	    <td style="padding-left: 10px;"align="center">
						                       <input id="cc${id}" type="checkbox" name="idd" value="${id}"> 
						            	    </td>           	    
							                <td id="TDIdClass" align="center">	      
												${pic_name}
											</td>
						            	</tr>
						            </s:iterator>    
						            <tr>
						                <td colspan="2" valign="middle" height="25px">
											<input style="border: 0px;width:60px;float: left" type="button" onclick="cb(true)" value="全选" >
											<input style="margin-left: 12px;border: 0px;width:60px;float: left "type="button" onclick="cb(false)" value="反选">     
						                </td>
						               
						            </tr>    
						        </table>  
		        		</div>			  						
  					</div> 					
  				</div>
		    </form>
	        <script type="text/javascript">
	       	      <s:iterator value="list"  status="sta" id="obj">            	                  	         	
	        	         check('${obj}');      
	       	      </s:iterator> 
	       	</script>    		        

			<div class="button-container">
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="submit" onclick="tijiao('${basePath}/manage/type/insert.action')" >提交</button>
			    <button type="button" class="return" onclick="openlink('${basePath}/manage/type/list.action',0,0)" >返回</button>
			</div>			
  		</div>

  	</body>
		<style type="text/css">
			.div_list{
				overflow-y:auto;
				height: 300px;
			}
		</style>
</html>