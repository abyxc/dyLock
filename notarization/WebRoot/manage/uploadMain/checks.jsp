<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>申办审核</title>
<%--  <script type="text/javascript" src="${basePath}/javascript/jquery-1.11.3.js"></script>--%>
<script src="${basePath}/js/look_ImgSH.js" type="text/javascript"></script>
</head>
  <body >
	<div class="nav-header">
	    <p class="new-add" style="float: left">申办审核&gt;&gt;审核</p>
	</div>
	<div class="shenban-upload">
		<form id="Form_s">
		  <s:iterator value="List" id="obj"  status="sta">
		<div class="nav-headerImg">
	    	<p class="new-addImg"  style="float: left;">基本信息【${obj[9] }】</p>
		</div>
	    <div class="look-table-container">
  			
  			  	<input type="hidden" name="ids" value="${obj[5]}">
				<input type="hidden" name="information.uploadMain_Id" value="${obj[5]}">
				<input type="hidden" value="${obj[9] }" id="gz_class" />
				<input type="hidden" value="${obj[10] }" id="language_id" />
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
		<div class="overflows">
	  		<div class="one">
				<s:iterator value="listImg"  status="sta" id="obj">
					<div class="div_num">
						<div style="text-align:center;height: 25px; ">
						<a title="${obj[0] }"><s:if test="#obj[0].length()>8"><s:property value="#obj[0].substring(0,8)"/>...</s:if><s:else>${obj[0]}</s:else></a>
						</div>
							<img style="width: 200px;height: 112px;"  onclick="hidden_urls('${obj[3]}')" src="${pathImgRead}${obj[2] }" title="${obj[4] }">
					</div>
					<input type="hidden" id="${obj[3] }hidden_url" value=${pathImgRead}${obj[1] }/>
				</s:iterator>
			</div>
	  		<div class="two">
	  				<table cellpadding="0" cellspacing="0" class="look-table">
	  					<tr class="operTableTRClass">
	  						<td id="sp_sp" ><span  class="name-title">审批：</span><s:radio id="radios" list="#{'2':'通过','3':'不通过'}" name="meg" onclick="select_sp($(this))" ></s:radio></td>
						</tr>  	
						<tr id="tr_bz" class="operTableTRClass">
							<td><span class="name-title">备注：</span><textarea name="status" id="status" style="width:500px;height:70px;" ></textarea></td>
						</tr> 
		  			</table>
		  			<div id="tr_bzs" style="text-align:center;" >
		  				<a style="font-size: 12px;color: red;"  >请备注栏中填写不通过的原因（即：缺少的材料）以逗号隔开！</a>
		  			</div>
	  		</div>
	  		<div id="fy_div">
	  			<div class="nav-headerImg">
		    		<p class="new-addImg"  style="float: left;">公证需纳费用</p>
				</div>
				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr class="operTableTRClass" >
  						<td><span class="name-title">份数费用：</span><input type="text" id="price_page" name="price_pages"/>元</td>
  						<td id="td_price_country" ><span class="name-title">使用地费用：</span><input type="text" id="price_country" name="price_countrys"/>元</td>
  						<td id="td_price_language"><span class="name-title">翻译费用：</span><input type="text" id="price_language" name="price_languages"/>元</td>
					</tr>  	
	  			</table>
	  		</div>
  		</div>
  		</form>
	</div>   	
	<div class="bottom" style="margin-top:0px;">
			<input type="button"  class="back" onclick="tijiao()" id="btnOK" value="提交"/>
	        <input type="button" value="返回" class="back" onclick="openlink('${basePath}/manage/uploadMain/list.action?searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}',0,0)"/>
	</div>
	<div id="outerdiv" class="div_imgs" >
    	<a class="close" onclick="$('#outerdiv').fadeOut('fast');"></a>
   		 <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div>
	</div>
	<div class="details_div" >
		<div class="details_img">
			<a><img src="${basePath}/images/ajax-loader.gif"></a>
		</div>
	</div>
  </body>
 	 <script type="text/javascript">
 	 $(function(){
			var gz_class = $('#gz_class').val();
			var language_id = $('#language_id').val();
			if(gz_class.indexOf('国内') > -1){
					$("#td_price_language").hide();
					$("#td_price_country").hide();
				}else{
					if(language_id.indexOf('英文') == -1){
						$('#td_price_language').hide();
						}
					}
 	 	 });
<%-- 	  金额输入绑定事件start --%>
	 $("#price_page").bind('keyup',function(){
		var price_page=this.value;
		checkMoney(price_page,'price_page','份数费用');
	 });
	$("#price_country").bind('keyup',function(){
		var price_country=this.value;
		checkMoney(price_country,'price_country','使用地费用');
	 });
	$("#price_language").bind('keyup',function(){
		var price_language=this.value;
		checkMoney(price_language,'price_language','翻译费用');
	 });
<%-- 	 金额输入绑定事件end --%>
	 
	 <%-- 判断金额是否是数字，且最多两位小数 --%>
	 function checkMoney(money,id,name){
		var indexD = money.indexOf('.');
		var aftStr="start";
		if(indexD>0){
			aftStr = money.substring(indexD +1,money.length);
			if(aftStr==''){
				money=money.substring(0,indexD);
			}
		}
		if(money.match(/^(:?(:?\d+.\d+)|(:?\d+))$/)){
			if(aftStr!='start' && aftStr.length>2){
				alert(name+"最多两位小数！");
				$("#"+id).val("");
			}
		}else{
			alert(name+"输入必须为数字！");
			$("#"+id).val("");
		}
		
	 }

 	 
 	 function select_sp (_this){
			if(_this.val() == '3'){
					$('#fy_div').hide();
					$('#tr_bz').fadeIn();
					$('#tr_bzs').fadeIn();
				}
			if(_this.val() == '2'){
					$('#fy_div').fadeIn();
					$('#tr_bz').hide();
					$('#tr_bzs').hide();
				}
 	 	 }
 	 function div_ing(){
  		$('#outerdiv').fadeOut('fast');
  		$('.details_div').hide();
  	 	 }
 	 function hidden_urls (id){
 		 	$('.details_div').show();
			var url = $('#'+id+'hidden_url').val();
			imgShow(url);
 	 	 }
  	  	function tijiao () {
	  	  	var gz_class = $('#gz_class').val();
	  	  	var radios = $('input:radio:checked').val();
	  	  	var price_page = $("#price_page").val();
	  	  	var price_country = $("#price_country").val();
	  	  	var price_language = $("#price_language").val();
	  	    var language_id = $('#language_id').val();
	  	  	if(typeof(radios) == 'undefined'){
					alert('审批栏未选择');
					$('#sp_sp').css('color','red');
					return false;
	  	  	  	}
	  	  if(radios == '3' && $("#status").val() == ''){
				alert("请备注栏中填写不通过的原因（即：缺少的材料）以逗号隔开！");
				return false;
			}
			if( radios == '2' ){
				if(price_page==''||price_page==null){
					alert("请输入份数费用");
					return false;
				}
			
			if(Number(gz_class.indexOf('国内')) == '-1'){
					if(price_country==''||price_country==null){
						alert("请输入使用地费用");
						return false;
					}
					if(language_id.indexOf('英文') > -1 ){
							if(price_language==''||price_language==null){
								alert("请输入翻译费用");
								return false;
							}
						}
				}
		    }
	  	if(!confirm('确认提交？')){return false;}
	  		setTimeout(function () { $('#btnOK').attr('onclick', '').bind('click', function () { reclick(); }); }, 1);
	  		var src = '${basePath}/manage/uploadMain/toChecks.action';
			  		 $.ajax({   
				         async:true,  	           	       		
					     type:"post",
			             url: src,	 				      
				         data:$("#Form_s").serialize(),	      		          
			             success:function(d) {
			                      if(d == '0'){
										alert("操作失败！此审核已被其他人操作，请刷新后再试");
										return false;
			                          }else{
			                        	  openlink('${basePath}/manage/uploadMain/list.action?searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}','',0);
			                              }
			             }
			       });
  	  	  	}
	  	  	
  	</script>
  		<style type="text/css">
  		.div_num{
  			border: 10px solid #F4F4F4;
  			float: left;
  			width: 220px;
  			height: 170px;
  		}
  		.one{ 
  			clear:both;
<%--  			height:340px;--%>
<%--  			overflow-y:auto;--%>
  		}
		.two{   clear:both;}
		.div_imgs{
		position:fixed;
		top:0;left:0;
		background:rgba(0,0,0,0.7);
		z-index:999;
		width:100%;
		height:100%;
		display:none;
	}
	.overflows{
			overflow-y:auto;
			height: 280px;
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
	#tr_bz,#tr_bzs{
		display:none;
	}
	.details_img{
		position:absolute;
		left: 46%; 
		top:42%;
		z-index:222;
		padding: 15px;
	}
	#fy_div{
		display:none;
	}
  	
  	</style>
</html>
