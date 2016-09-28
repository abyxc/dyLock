<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head lang="en">
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>申办管理</title>
    <link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
	<script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
</head>
<body>
	<s:iterator value="list" id="obj" status="sta">
	<div class="bid-container">
	    <dl class="bid-content" onclick="toDetails('${obj[0]}');">
	        <dt>
	            <span class="bid-title"><em>申办项目：</em> ${obj[1]}</span>
	            <time class="bid-time">
	            	${obj[2]}
	            </time>
	        </dt>
	        <dd><em>申办人：</em> ${user_name}</dd>
	        <dd><em>申办单位：</em>浙江省杭州市国立公证处</dd>
	        <dd>
	        	<span class="total">共<a> ${obj[3]}</a>份公证书</span> 
	        	<span class="money">
	        		<s:if test="#obj[5] == 2 || #obj[5] == 5 || #obj[5] == 6 ">
		        		费用合计：<i>￥${obj[4]}</i>
	        		</s:if>
	        	</span>
	        </dd>
	    </dl>
	    <div class="bid-tail">
	        <span class="bid-type">状态：<span class="bid-refuse">
	        <td>
				<s:if test="#obj[5] == 0">拒绝审核</s:if>
				<s:elseif test="#obj[5] == 1">等待审核</s:elseif>
				<s:elseif test="#obj[5] == 2">审核成功</s:elseif>
				<s:elseif test="#obj[5] == 3">审核失败</s:elseif>
				<s:elseif test="#obj[5] == 4">已撤销</s:elseif>
				<s:elseif test="#obj[5] == 5">已缴费</s:elseif>
				<s:elseif test="#obj[5] == 6">已完结</s:elseif>
			</td>
	        </span></span>
	        <div class="button-container">
		        <s:if test="#obj[5] == 1">
		        	<button class="revocation" onclick="setUploadMainId('${obj[0]}');">撤销</button>
		        </s:if>
				<s:if test="#obj[5] == 2">
					<button class="revocation" onclick="setUploadMainId('${obj[0]}');">撤销</button>
		            <button class="pay" onclick="toPayMoney('${obj[0]}');">付款</button>
		        </s:if>
	        </div>
	    </div>
	</div>
	</s:iterator>
<div class="revocation-container"></div>
<div class="revo-reason">
    <form action="">
        <dl class="revo-dl">
            <dt class="reason-title">撤销公证理由</dt>
            <dd>
                <label for="reason1" class="reason-container">
                    <input type="radio" name="reason" id="reason1" value="1" class="reason-input"/>
                    <label class="reason-label" for="reason1"></label>速度太慢，已直接线下办理
                </label>
            </dd>
            <dd>
                <label for="reason2" class="reason-container">
                    <input type="radio" name="reason" id="reason2" value="2" class="reason-input"/>
                    <label class="reason-label" for="reason2"></label>计划有变，不需要做公证
                </label>
            </dd>
            <dd>
                <label for="reason3" class="reason-container">
                    <input type="radio" name="reason" id="reason3" value="3" class="reason-input"/>
                    <label class="reason-label" for="reason3"></label>材料无法提供，办理不了
                </label>
            </dd>
            <dd>
                <label for="reason4" class="reason-container">
                    <input type="radio" name="reason" id="reason4" value="4" class="reason-input"/>
                    <label class="reason-label" for="reason4"></label>其他理由
                </label>
            </dd>
            <dd class="button-group">
                <button class="cancel" type="button" onclick="closeWindow();">取消</button>
                <button class="confirm" type="button" onclick="putBack();">确定</button>
            </dd>
        </dl>
    </form>
</div>
<div class="revo-success">
    <dl class="revo-dl">
        <dt class="reason-title">撤销公证成功！</dt>
        <dd>
            <button class="confirm" onclick="revoSuccess()">确定</button>
        </dd>
    </dl>
</div>
<script>
	//将要撤销的id存在页面缓存中
	var uploadMainId = "";
	function setUploadMainId(id){
		uploadMainId = $.trim(id);
		$(".revocation-container").css("display", "block");
        $(".revo-reason").css("display", "block");	
	}	

	//将点击撤销的弹出的窗口关闭
	function closeWindow(){
		$(".revocation-container").css("display", "none");
        $(".revo-reason").css("display", "none");
	}

	//点击撤销后的弹出页面中的确定按钮后执行的事件
	function putBack(){
		id = uploadMainId;
		if(id.length == 0){
			alert("页面出现错误！");
			return false;
		}

		//判断是不是已经选中撤回原因
		var checkedRadio = $('input[name="reason"]:checked').val();;
		if(checkedRadio == undefined){
			alert("请选择撤回理由！");
			return false;
		}
		
		$.ajax({
            type: "post",
            url: "${basePath}/manage/wechat/wcbid/putBack.action",
            data: {"upload_main_id":id},
            dataType: "text",
            success: function(data){
                if(data == 1){
                	$(".revo-reason").css("display", "none");
                    $(".revo-success").css("display", "block");
                }
            	if(data == 2){
                	alert("系统错误！");
            	}           
            }
        });
	}

	function revoSuccess(){
		//撤销成功
      	$(".revo-success").css("display", "none");
      	$(".revocation-container").css("display", "none");
      	location.reload();
  	}
	
	//点击付款执行的时间
	function toPayMoney(id){
		window.location.href = "${basePath}/manage/wechat/wcbid/toPayMoney.action?uploadMain.id="+id;
	}

	//前往查看页面
	function toDetails(id){
		window.location.href = "${basePath}/manage/wechat/wcbid/toDetails.action?uploadMain.id="+id;
	}
	   
</script>
</body>
</html>