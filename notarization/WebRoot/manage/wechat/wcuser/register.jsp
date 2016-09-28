<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no">
    <title>杭州国立公证处</title>
    <link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
	<script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<article>
        <table class="medical-table" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <div class="medical-top">真实姓名：<span class="vereist">(必填*)</span></div>
                    <div class="medical-down">
                    	<input type="text" name="name" id="user_name" value="${name}" placeholder="请输入真实姓名"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="medical-top">证件类型：<span class="vereist">(必填*)</span></div>
                    <div class="medical-down-special" >
                    	<select name="idcard_type" id="types">
                            <option value="">&nbsp;&nbsp;-- 请选择 --</option>
                            <option value="1">&nbsp;&nbsp;身份证</option>
                            <option value="2">&nbsp;&nbsp;护照</option>
                            <option value="3">&nbsp;&nbsp;台湾地区身份证</option>
                            <option value="4">&nbsp;&nbsp;港澳通行证</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="medical-top">证件号码：<span class="vereist">(必填*)</span></div>
                    <div class="medical-down">
                    	<input type="text" name="idcard_number" id="user_idcard_number" value="${idcard_number}"
                    	 	placeholder="请输入由数字或字母组合的证件号"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="medical-top">请输入手机号：<span class="vereist">(必填*)</span></div>
                    <div class="medical-down">
                    	<input type="text" name="mobile_phone" id="user_mobile_phone" value="${mobile_phone}"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="medical-top">请输入验证码：</div>
                    <div class="medical-down">
                    	<input type="text" name="test_code_jsp" id="user_test_code_jsp" value="${test_code_jsp}"
                    		class="media-code"/> 
                        <input type="button" value="获取短信验证码" class="media-gaincode"/>
                    </div>
                </td>
            </tr>
        </table>
        <form action="${basePath}/manage/wechat/wcuser/toSuccess.action" method="post" id="Form">
        	<input type="hidden" name="code" value="${code}"/>
        </form>
</article>
<footer class="bid-footer">
    <a href="#" onclick="tijiao();" class="bid-footer-next">提交</a>
</footer>
<script>
    $(function () {
        $(".medical-table td").click(function () {
            $(".box:checked");
        });
        var wait = 60;
        function time(o) {
            if (wait == 0) {
                o.removeAttribute("disabled");
                $("input.media-gaincode").css("background","#f39c12");
                o.value="获取短信验证码";
                wait =60;
            } else {
                o.setAttribute("disabled",true);
                $("input.media-gaincode").css("background","#aaa");
                o.value="重新发送（" + wait + "s）";
                wait--;
                setTimeout(function(){time(o)},1000)
            }
        }
      	//发送验证码
        $(".media-gaincode").click(function(){
        	var user_mobile_phone = $.trim($("#user_mobile_phone").val());
    		$("#user_mobile_phone").val(user_mobile_phone);
    		if(!user_mobile_phone.match(/^1[3|4|5|7|8][0-9]\d{8}$/)){
    			alert("手机号格式错误！");
    			return false;
    		}
    		$.post("${basePath}/manage/wechat/wcuser/sendTestCode.action",{"mobile_phone":user_mobile_phone},function(data){
    			if(data == 2){
    				alert("系统异常！");
    			}
    			if(data == 3){
        			alert("手机号格式错误！");
    			}
    		});
	        time(this);
        });
    })

    
    //提交，用ajax方式进行提交
    function tijiao(){
        var user_name = $.trim($("#user_name").val());
		var user_idcard_type = $.trim($("#types").val());
        var user_idcard_number = $.trim($("#user_idcard_number").val());
        var user_mobile_phone = $.trim($("#user_mobile_phone").val());
        var user_test_code_jsp = $.trim($("#user_test_code_jsp").val());
        
        $("#user_name").val(user_name);
        $("#user_idcard_number").val(user_idcard_number);
        $("#user_mobile_phone").val(user_mobile_phone);
        $("#user_test_code_jsp").val(user_test_code_jsp);
        if(user_name.length == 0){
            alert("真实姓名必填！");
            return false;
        }
		if(user_idcard_type == 0){
			alert("证件类型必选！");
			return false;
		}
        if(user_idcard_number.length == 0){
        	 alert("证件号码必填！");
             return false;
        }
        if(user_mobile_phone.length == 0){
        	 alert("手机号码必填！");
             return false;
        }
        if(user_test_code_jsp.length == 0){
        	 alert("验证码必填！");
             return false;
        }



        if(!user_mobile_phone.match(/^1[3|4|5|7|8][0-9]\d{8}$/)){
            alert("手机号格式错误！");
            return false;
        }
        if(!user_test_code_jsp.match(/^\d{6}$/)){
            alert("验证码必须是6位数据！");
            return false;
        }

		var code = '${code}';
       	$.post("${basePath}/manage/wechat/wcuser/insert.action",
       	       	{"code":code,"name":user_name,"idcard_type":user_idcard_type,
   	       		 "idcard_number":user_idcard_number,"mobile_phone":user_mobile_phone,"test_code_jsp":user_test_code_jsp},
       	       	function(data){
   	       	       	if(data == 0){
   	       	      		$("#Form").submit();	       	      		
   	       	       	}else if(data == 1){
   	   	   	       		alert("未获取到验证码！");
   	       	       	}else if(data == 2){
   	   	       	       	alert("验证码错误！");
   	       	       	}else if(data == 3){
   	       	     	 	alert("链接超时，请重新进入网站！");
	       	       	}else if(data == 4){
	       	       		alert("系统异常！");
	       	       	}else{
						alert("未知错误！");
		       	    }
       			}
       	);
    }
</script>
</body>
</html>