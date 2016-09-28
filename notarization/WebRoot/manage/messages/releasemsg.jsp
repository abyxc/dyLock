<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}/css/relStyle.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">  
 
        function showconr(m){ 

	        var objre1=document.getElementById("meg1");
            var objre2=document.getElementById("content1");
            var objre3=document.getElementById("meg10");                        
            if(objre1.value==""){
                 alert("标题不能为空");  
			     return false;	                    
            }
            if(objre1.value.length<3){
                alert("标题不能少于2字"); 
			    return false;	                     
            }                             
            if(objre2.value==""){
                 alert("内容不能为空");  
			     return false;	                    
            }  
            if(objre2.value.length<5){
                alert("内容不能少于5字"); 
			    return false;	                     
            }   
	           $.ajax({   
			         async:false,  	           	       		
				     type:"post",
		             url:"${basePath}/manage/messages/insert.action",	 				      
      		         data:$("#form1").serialize(),	      		          
		        success:function(d) {
	                     
		                 if(d=="0"){
		                    if(m==1){	
		                        src=encodeURI(encodeURI("${basePath}/manage/messages/detailmsg.action?meg=0&ids=${messages.id}&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}"));		                   
		                      	$(".content-right").load(src);                                            
		                    }else{
                               $("#div2_msg").html('<div style="font-size:16px;color:red;margin-top: 100px;text-align:center;">发布成功!<br>'
	                           +'<div class="rell" onclick="openhref()" style="cursor: pointer;" >继续发布</div></div>');		                    
		                    }

	                     }else{
	                         alert("系统异常，请联系管理员");
	                     }                
	                                                                             
	                    }	                                  		           		                  	     
		       });

		       }
			    function openhref(){			 
			          $(".content-right").load("${basePath}/manage/messages/releasemsg.jsp");			        
			    };   		       
  
    </script>      
  

  </head>
  
  <body>
    <input id="meg3" type="hidden" name="messagesImgsrcs" value="">
           <div class="div1_msg">
           <div id="div2_msg" class="div2_msg" >   
              <ul>
                <li class="msg_td1" >发布消息</li>
                <li style="margin:0 auto;width:100%;"> 
                    <div style="margin:0 auto;width:702px;">
                    <form id="form1" action="${basePath}/manage/messages/insert.action" method="post">                  
                    <input type="hidden" name="messages.id" value="${messages.id}" >	
                    <input type="hidden" name="messages.create_time" value="${messages.create_time}" >
                    <table class="msg_table1">
	                    <tr>
		                    <td valign="bottom" height="35px">&nbsp;<div id="rv1" class="div3_msg">&nbsp;</div></td>
	                    </tr>
	                    <tr>
		                    <td  valign="bottom">		                                                                          
		                                            标&nbsp;题&nbsp;<input id="meg1" class="msg_input" type="text" name="messages.title" value="${messages.title}" style="width:658px;height:30px;border:0px;border-bottom: 1px solid #8A8A8A;padding-left:3px;">		                                                                            		                      		                                                                         
		                    </td>			    	      		                    
	                    </tr>
	                    <tr>
		                    <td class="msg_td2" colspan="4">&nbsp;</td>
	                    </tr>	                                        
	                    <tr>
		                    <td class="msg_td3">内&nbsp;容<div id="rv2" class="div4_msg"> </div></td>		                    
	                    </tr>	
	                    <tr>
		                    <td>
		                      <textarea id="content1"  style="width:700px;height:200px;" name="messages.info">${messages.info}</textarea>
		                    </td>
	                    </tr>
	                    <tr>
		                    <td>
                              <div class="div5_msg">
	                            <ul>
	                                <li> 
	                                <input id="meg10"  type="hidden" name="messages.info_type"  value="2" >	
<!-- 							        &nbsp;<b style="color:red;">*</b>发布类型: 
							            <select id="meg10" name="messages.info_type" >	
								           <option value="2" <s:if test="messages.info_type==2">selected="selected" </s:if> >&nbsp;知识&nbsp;</option>               									           
								           <option value="1" <s:if test="messages.info_type==1">selected="selected" </s:if>>&nbsp;简介 &nbsp;</option>	
								          	           
							            </select>    -->            
							        </li>	   
	                             </ul>	                              
	                          </div>		                 
		                    </td>
	                    </tr>
	                    <tr><td><div id="prw" class="div8_msg" ></div><div id="avatar_priview" style="display: none"></div></td></tr>	                    	  	                                      	                    	                                       	                    	                    	                                       	                    
                    </table>  
                    </form>                                         
                    </div>             
                </li>                                                
              </ul>
			  <div class="bottom">
			       <button class="submit" type="button" <s:if test="meg==1">onclick="showconr(1)"</s:if><s:else>onclick="showconr(0)"</s:else> id="btnOK">确定</button>
			       <input type="button" value="返回" class="back"  onclick="openlink('${basePath}/manage/messages/list.action?ids=2&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}'),'',0"/>
			  </div>                    
         </div>  
         </div>
   
  </body>
</html>


