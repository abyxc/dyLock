<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>公证事项类型</title>
  <script type="text/javascript">
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
        function checkf(){

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
		        
		}  		
  </script>
</head>
  <body >
   	<div class="section">
  			<div class="titleDivZZZ">
  				<span class="titleDivTextZZZ">
  					<b>${meg}</b>
  				</span>
  			</div>
  		<form onsubmit="return checkf()" action="${basePath}/manage/type/doUpdate.action" method="post">	
  		<input type="hidden" name="searchTearm1" value="${searchTearm1}"> 

        <table cellpadding="0" cellspacing="0" style="width:100%;border: 1px solid #BDBDBD;">
            <tr>
                <th width="25%">
					<input style="margin-left: 12px;border: 0px;width:60px " type="button" onclick="cb(true)" value="全选" >
					<input style="margin-left: 12px;border: 0px;width:60px "type="button" onclick="cb(false)" value="反选">     
                </th>
                <th>事项名称</th>
            </tr>
            <s:iterator value="pictureNames"  status="sta" >
            	<tr <s:if test="#sta.index%2 == 0">style="background-color: #FFFFFF;"</s:if> >
            	    <td style="padding-left: 10px;"align="center">
                       <input id="cc${id}" type="checkbox" name="idd" value="${id}"> 
            	    </td>           	    
	                <td id="TDIdClass" align="center">	      
						${pic_name}
					</td>
            	</tr>
            </s:iterator>    
            <tr>
                <td>&nbsp;</td>
                <td align="right" >
                
		            <input type="submit" value="保 存" onmouseover="this.style.background='#87CEFA'" onmouseout="this.style.background='#7EC0EE'"
                    style="border:none;width:70px; height:23px;font-size:13px;color:#fff; background:#7EC0EE;margin-right: 30px" />    
                    <input type="button" onclick="history.go(-1)" value="返回" onmouseover="this.style.background='#87CEFA'" onmouseout="this.style.background='#7EC0EE'"
                    style="border:none;width:70px; height:23px;font-size:13px;color:#fff; background:#7EC0EE;margin-right: 30px" />               
                </td>
            </tr>       
        </table>
        </form>
        <script type="text/javascript">
       	      <s:iterator value="list"  status="sta" id="obj">            	                  	         	
        	         check('${obj}');      
       	      </s:iterator> 
       	</script>           
	    <div class="panelBar" style="background-color:FFFFFF;width: 100%;height: 25px;">
			<div id="page" style="float: right;background-color: #FFFFFF;">${page}</div>
		</div>
		
    </div>
  </body>
</html>
