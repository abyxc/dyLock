<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head lang="en">
   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no">
    <title>杭州市国立公证处</title>
    <link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
    <script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${basePath}/javascript/img_bianji.js"></script>
    <link href="${basePath}/images/title.ico" rel="shortcut icon"/>
</head>
<body>




<article>

	<div id="div_tc">
	</div>
	<header class="info-header">
    办理业务：${type.gz_name }
</header>
	<div class="YS">
			<input type="hidden" id="form_id" value="${status }"/>
		
			<s:hidden name="status" value="1"></s:hidden>
			<s:iterator value="list"  status="sta" id="obj">  
			<form  id="form${obj[1]}" >
				<input type="hidden" name="ids" value="${ids }"/>
				<input type="hidden" id="biaoji${obj[1]}"  value="${obj[4] }"/>
				<input type="hidden" id="meg" name="meg" value="${obj[1] }"/>
				<div class="home_div">
					<s:if test="#obj[0]!=null"><a class="a${obj[1]}">${obj[0]}【${obj[2]}】</a></s:if>  
					<div style="height: 65px;width: 75px;">
					     <div class="upload-img" id="div_forms${obj[1]}" >
							<input accept="image/*" type="file" name="uploads" class="comment-pic-upd" id='${obj[1]}'  onchange="previewImage(this) "  />
							<img  src="${basePath}/images/photograph.png" alt="上传照片" title="">
						</div>
					</div>
					<div id="div_Add${obj[1]}" style="width: 160px;">
					</div> 
				</div>
			 </form>
			</s:iterator>
			<form action="${basePath}/manage/wechat/wcuploadMain/toFillRecord2.action" method="post" id="Form">
				<s:hidden name="uploadMain.country_id" id="country_id"/>
				<s:hidden name="uploadMain.gz_class" id="gz_class"/>
				<s:hidden name="uploadMain.gz_count" id="gz_count"/>
				<s:hidden name="uploadMain.gz_receive_type" id="gz_receive_type"/>
				<s:hidden name="uploadMain.gz_type_id" id="gz_type_id"/>
				<s:hidden name="uploadMain.gz_use" id="gz_use"/>
				<s:hidden name="uploadMain.language_id" id="language_id"/>
				<s:hidden name="uploadMain.users_id" id="users_id"/>
				<s:hidden name="upload_mainId" id="upload_mainId"/>
		 	</form>
   	</div>
     <p class="up-center">
        证件必须是真实拍摄不能使用复印件，图片中无反光、无遮挡、无水印，确保清晰真实可见，容易识别。
    </p>
    </article>
	<footer class="upload-footer">
    	<a class="upload-footer-next" onclick="submits()" id="btnOK" >下一步</a>
	</footer>
</body>
<script type="text/javascript">
	function dels(_this){
		alert(_this.id);
		}
	function submits () {
		var strIds = "";
		var formId = $("#form_id").val();//为db_gz_picture_name ID
		formId = formId.replace("/","");
		formId = formId.split("||");
		var biaoji_1 = '1';
		for(var i=0;i<formId.length ;i++ ){
			var val = $("#"+formId[i]).val();
			var BJ = $("#biaoji"+formId[i]).val();
			if(val == '' && BJ == 1 ){
				biaoji_1 = '2';
				$(".a"+formId[i]).css('background-color','red');
			}
		}
		if(biaoji_1 == '2'){
			alert("红色标题表示必须提供照片");
			return false;
		}
		setTimeout(function () { $('#btnOK').attr('onclick', '').bind('click', function () { reclick(); }); }, 1);
		 $('#div_tc').append('<div id="div_tc01" class="alert_div" ></div><div id="div_tc02" class="loaders"><a><img src="${basePath}/images/ajax-loader.gif"></a><div>');
				for(var i=0;i<formId.length;i++){
<%--						alert("进入循环");--%>
						var formData = new FormData($( "#form"+formId[i] )[0]);
						var url = "${basePath}/manage/wechat/wcuploadMain/uploadImg.action";
						$.ajax({
						 	 url:url,
					       	type: "POST",
					       	 cache: false,  
					       	async:false,
				      		 contentType: false,  
				      	  	 processData: false,
				      	 	data:formData,// 要提交的表单 
				      	 	success: function(data) {
						      	 	strIds += data;
					       			if(Number(i) == Number(formId.length-1 )){
										alert("成功");
								   }
						       	 }
				  });
			}
			$("#upload_mainId").val(strIds);
			$("#Form").submit();
		}
</script>
<style type="text/css">
			.YS{
				position: relative; 
				left: 2%;
			}
<%--		#div_Add{--%>
<%--			align:center;--%>
<%--		}--%>
		#div_s1{
			height: 65px;
		}
		.home_divs{
			border:1px solid red;
			height: 100%;
		}
		.alert_div{
		background: rgb(0, 0, 0) none repeat scroll 0% 100%;
		width: 100%; height: 100%; 
		position: absolute; 
		top: 0px; left: 0px; 
		z-index: 1111; 
		opacity: 0.4;
	}
	.loaders{
		position:absolute;
		left: 43%; 
		top:50%;
		z-index:1122;
		padding: 15px;
	}
<%--	 #preview{width:213px;height:120px;border:1px solid #000;overflow:hidden;}--%>
	 #imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
	  .imgBig{
            height: 96px;
            width: 170px;
        }
        .upload-img{
		position: relative;
		margin: 7px 20px 0 0;
		overflow: hidden;
<%--		float: left;--%>
		
	}
		.upload-img img{
			width: 58px;
			height: 58px;
		 
		}							
 
	.comment-pic-upd{
		position: absolute;
		top: 0;
		left: 0;
		
		z-index: 100;
		width: 58px;
		height: 58px;
		 
		filter: progid:DXImageTransform.Microsoft.Alpha(opacity=0);
		filter:alpha(opacity=0);
		-moz-opacity:0;
		-khtml-opacity: 0;
		opacity:0;
		background: none;
		border: none;
		cursor: pointer;
	}
</style>
</html>