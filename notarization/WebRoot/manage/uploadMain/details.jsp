<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>申办详情</title>
  <script src="${basePath}/js/look_ImgSH.js" type="text/javascript"></script>
</head>
  <body >
	<div class="nav-header">
			<s:if test=" action == 2">
				<p class="new-add" style="float: left">申办记录&gt;&gt;查看</p>
			</s:if>
			<s:else>
				<p class="new-add" style="float: left">申办审核&gt;&gt;查看</p>
			</s:else>
	</div>
	<div class="shenban-upload">
	<s:iterator value="List" id="obj"  status="sta">
		<div class="nav-headerImg">
	    	<p class="new-addImg"  style="float: left;">基本信息【${obj[9]}】</p>
		</div>
	    
	    <div class="look-table-container">
  			  
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr class="operTableTRClass">
  						<td><span class="name-title">记录编号：</span>${obj[7]}</td>
  						<td><span class="name-title">客户姓名：</span>${obj[0]}</td>
  						<td><span class="name-title">证件类型：</span>
  							<s:if test="#obj[1] == 1">身份证</s:if>
							<s:elseif test="#obj[1] == 2">护照</s:elseif>
							<s:elseif test="#obj[1] == 3">台湾地区身份证</s:elseif>
				    		<s:elseif test="#obj[1] == 4">港澳通行证</s:elseif>
  						</td>
					</tr>  	
					<tr class="operTableTRClass">
						<td title="${obj[4] }"><span class="name-title">申请事项：</span><s:if test="#obj[4].length()>6"><s:property value="#obj[4].substring(0,6)"/>...</s:if><s:else>${obj[4]}</s:else></td>
						<td><span class="name-title">公证书份数：</span>${obj[6] }</td>
						<td><span class="name-title">证件号：</span>${obj[8] }</td>
					</tr> 
					<tr class="operTableTRClass">
						<td><span class="name-title">翻译语言：</span>${obj[10] }</td>
						<td><span class="name-title">公证用途：</span>${obj[11] }</td>
						<td><span class="name-title">证件使用国家：</span>${obj[12] }</td>
					</tr> 
					<tr class="operTableTRClass">
						<td><span class="name-title">领取方式：</span>${obj[13] }</td>
						<td></td>
						<td></td>
					</tr> 		
	  			</table>
  		</div>
  		 </s:iterator>
  		<div class="nav-headerImg">
	    	<p class="new-addImg"  style="float: left;">上传材料</p>
		</div>
  		<div class="overflow" >
			<s:iterator value="listImg"  status="sta" id="obj">
				<div class="div_num">
					<div style="text-align:center; height: 25px;">
					<a title="${obj[0] }"><s:if test="#obj[0].length()>8"><s:property value="#obj[0].substring(0,8)"/>...</s:if><s:else>${obj[0]}</s:else></a>
					</div>
						<img style="width: 200px;height: 112px;" onclick="hidden_urls('${obj[3]}')"  src="${pathImgRead}${obj[2] }" title="${obj[4] }" >
				</div>
				<input type="hidden" id="${obj[3] }hidden_url" value='${pathImgRead}${obj[1] }' />
			</s:iterator>
		</div>
  		
	</div>   	
	<div class="bottom">
			<s:if test=" action == 2">
				<input type="button" value="返回"  class="back" onclick="openlink('${basePath}/manage/uploadMain/list.action?status=2&searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}',0,0)"/>
			</s:if>
			<s:else>
				<input type="button" value="返回"  class="back" onclick="openlink('${basePath}/manage/uploadMain/list.action?searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}',0,0)"/>			
			</s:else>
	</div>
	<div id="outerdiv" class="div_imgs" >
    	<a class="close" onclick="div_ing()"></a>
   		 <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div>
	</div>
	<div class="details_div" >
		<div class="details_img">
			<a><img src="${basePath}/images/ajax-loader.gif"></a>
		</div>
	</div>
  </body>
 	 <script type="text/javascript">
 	 function div_ing(){
 		$('#outerdiv').fadeOut('fast');
 		$('.details_div').hide();
 	 	 }
 	 function hidden_urls (id){
 	 	 $('.details_div').show();
			var url = $('#'+id+'hidden_url').val();
			imgShow(url);
	 	 }
  	</script>
  		<style type="text/css">
  		.div_num{
  			border: 10px solid #F4F4F4;
  			float: left;
  			width: 220px;
  			height: 170px;
  		}
  		.bottom{
  			clear:both;
  		}
  		.btn-containers{
  			position:fixed;
  			bottom:0;
  			align:aenter;
    		width: 88px;
		    height: 32px;
		    background-color: #ff7d3d;
		    color: #fff;
		    font-size: 18px;
		    margin: 60px 100px 40px 20px;
		    border-radius: 2px;
  		}
  		.overflow{
  			height:270px;
  			overflow-y:auto;
  		}
		.div_imgs{
		
			position:fixed;
			top:0;left:0;
			background:rgba(0,0,0,0.7);
			z-index:999;
			width:100%;
			height:100%;
			display:none;
	}
	.details_div{
		background: rgb(0, 0, 0) none repeat scroll 0% 100%;
		width: 100%; height: 100%; 
		position: absolute; 
		top: 0px; left: 0px; 
		z-index: 111; 
		opacity: 0.4;
		display:none;
	}
	.details_img{
		position:absolute;
		left: 46%; 
		top:42%;
		z-index:222;
		padding: 15px;
	}
  	</style>
</html>
