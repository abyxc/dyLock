<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
    	<meta charset="UTF-8">
   		<meta name="viewport" content="width=device-width,user-scalable=no">
    	<title>杭州国立公证处</title>
    	<script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
    	<link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
    	<link href="${basePath}/images/title.ico" rel="shortcut icon"/>
	</head>
	<body>
		<header class="info-header">
   			 办理业务：<s:if test="gz_type==0">国内</s:if>
   			 <s:elseif test="gz_type==1">涉外</s:elseif>-<s:property value="type.gz_name"/>
		</header>
		<article>
		<form action="${basePath}/manage/wechat/wcuploadMain/detail.action" method="post" id="Form">
			<s:hidden name="gz_type"></s:hidden>
			<input type="hidden" name="uploadMain.users_id" value="${user.id}"/>
			<s:hidden name="uploadMain.gz_type_id" ></s:hidden>
			<table class="info-table" cellpadding="0" cellspacing="0">
        		<tr>
            		<td>手机号码：<s:property value="user.mobile_phone"/></td>
        		</tr>
       			<tr>
            		<td><span class="letter">证&nbsp;件&nbsp;号</span>&nbsp;：<s:property value="user.idcard_number"/></td>
        		</tr>
        		<s:if test="gz_type==0">
            		<s:hidden name="uploadMain.gz_class" value="国内"></s:hidden>
					<s:hidden name="uploadMain.country_id" value="中国"></s:hidden>
					<s:hidden name="uploadMain.language_id" value="中文"></s:hidden>
        		</s:if>
        		<s:if test="gz_type==1">
        		<s:hidden name="uploadMain.gz_class" value="涉外"></s:hidden>
        		<tr>
           			 <td class="use-place">请选择公证书使用地</td>
        		</tr>
        		<tr>
            		<td class="place-value"><span class="place">
            			<s:select name="uploadMain.country_id" id="country_id"
							list="#{'美国':'美国','日本':'日本','俄罗斯':'俄罗斯','法国':'法国','英国':'英国'}" 
							listKey="key" listValue="value" headerKey="" headerValue="---请选择---" ></s:select>
            			</span><i></i>
            		</td>
        		</tr>
        		<tr>
            		<td class="info-language">是否需要翻译其他语言<span>（翻译需另外付费）</span></td>
        		</tr>
        		<tr>
            		<td class="language-value"><span class="language">
            			<s:select name="uploadMain.language_id" id="language_id"
							list="#{'英语':'英语','日语':'日语','俄语':'俄语','法语':'法语'}" 
							listKey="key" listValue="value" headerKey="不翻译" headerValue="---不翻译---" ></s:select>
						</span><i></i>
					</td>
        		</tr>
        		<tr>
            		<td class="info-application">公证书用途<i></i>
        		</tr>
        		<tr>
            		<td class="application-value"><span class="application">
            		<s:select name="uploadMain.gz_use" id="gz_use" list="#{'出差':'出差','旅游':'旅游','留学':'留学'}" 
						listKey="key" listValue="value" headerKey="" headerValue="---请选择---" ></s:select>
						</span><i></i>
					</td>
        		</tr>
        		</s:if>
        		<tr>
            		<td class="banli">
               			<div class="banli-left">办理份数</div>
                		<div class="banli-right">
                    		<s:textfield name="uploadMain.gz_count" id="gz_count" cssClass="copies" value="1"></s:textfield>
                		</div>
            		</td>
        		</tr>
        		<tr>
            		<td>
                		<div class="linzheng-left">领证方式</div>
                		<div class="linzheng-right">
                    	<div class="info-radio"><input type="radio" id="radio1" class="radio" value='1' name="uploadMain.gz_receive_type" checked="checked"/><label for="radio1"></label><span>本人</span>  </div>
                    	<div class="info-radio radio-right"><input type="radio" id="radio2" class="radio" value='2' name="uploadMain.gz_receive_type"/><label for="radio2"></label><span>代理</span>  </div>
                		</div>
            		</td>
        		</tr>
    		</table>
		</form>
		</article>
		<footer class="bid-footer">
   			 <a href="#" class="bid-footer-next" onclick="tijiao();">下一步</a>
		</footer>
</body>
<SCRIPT type="text/javascript">
	function tijiao(){
		if($("#country_id").val()==""){
			alert("请选择公证书使用地！");
			return false;
		}
		if($("#gz_use").val()==""){
			alert("请选择公证书用途！");
			return false;
		}
		if($("#gz_count").val()==""){
			alert("请填写办理份数！");
			return false;
		}
		$("#Form").submit();
	}
</SCRIPT>
</html>