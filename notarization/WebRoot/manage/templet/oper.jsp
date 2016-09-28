<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>短信模板</title>
  </head>
<script type="text/javascript">
// 短信内容
	function wordCnt(content) {
		var cnt = 0;
		var cts = content.split("");
		for ( var i = 0; i < cts.length; i++) {
			if (cts[i].charCodeAt(0) < 299) {
				cnt++;
			} else {
				cnt += 2;
			}
		}
		document.getElementById("wordCnt").innerHTML=cnt;
		return cnt;
	}
	function smsCnt(cnt){
		var smsCnt = 0;
		smsCnt = (cnt+140-1)/140;
		var smsCntStr = smsCnt.toString();
		if(smsCntStr.indexOf(".")>0){
			smsCntStr = smsCntStr.substring(0, smsCntStr.indexOf("."));
		}
		document.getElementById("smsCnt").innerHTML=smsCntStr;
		}
	function conipt(val){
		smsCnt(wordCnt(val));
		}
	function check(){
		var content = document.getElementById("contentSms").value;
		if(content==""){
			alert("模板内容不能为空！");
			return;
			}
		document.getElementById("Form").submit();
		}
</script>
	<body>
    	<div class="nav-header">
		    <p class="new-add">模板列表&gt;&gt;新增</p>
		</div>
		<div class="look-table-container">
		    <form action="${basePath}/manage/templet/insert.action" method="post" id="Form">
		    <s:hidden name="templet.id"></s:hidden>
		    <table cellpadding="0" cellspacing="0" class="look-templet">
		    	<tr>
		    		<td>名称：</td>
		    		<td><s:textfield name="templet.name" id="name" cssStyle="width: 200px;height: 25px;"></s:textfield></td>
		    	</tr>
		    	<tr>
		    		<td>类型：</td>
		    		<td><s:select list="typeList"  listValue="gz_name" listKey="id" headerValue="--请选择--" headerKey="" name="templet.type_id" id="type_id"  cssStyle="width: 200px;"/></td>
		    	</tr>
		    	<tr>
		    		<td>内容：</td>
		    		<td>
		    			<table>
		    				<tr>
		    					<td>
		    						<s:textarea name="templet.content" cssStyle="height:100px;width:750px;" id="contentSms" onkeyup="conipt(this.value)"></s:textarea>
		    					</td>
					    		<td>
					    		    <table style="margin-left:0px">
					    		    	<tr>
					    		    		<td>
					    		    			<input type="button" value="申办类型" id="text2" onclick="insert1()"/>
					    		    		</td>
					    		    	</tr>
					    		    	<tr>
					    		    		<td>
					    		    			<input type="button" value="姓名" id="text1" onclick="insert()"/>
					    		    		</td>
					    		    	</tr>
					    		    	<tr>
					    		    		<td>
					    		    			<input type="button" value="申办状态" id="text3" onclick="insert2()"/>
					    		    		</td>
					    		    	</tr>
					    		    	<tr>
					    		    		<td>
					    		    			<input type="button" value="楼层" id="text4" onclick="insert3()"/>
					    		    		</td>
					    		    	</tr>
					    		    	<tr>
					    		    		<td>
					    		    			<input type="button" value="联系电话" id="text5" onclick="insert4()"/>
					    		    		</td>
					    		    	</tr>
					    		    </table>
					    		</td>
		    				</tr>
		    			</table>
		    		</td>
		    	</tr>
		    	<tr><td></td><td>已输入【<span id="wordCnt">0</span>】个字符，将被拆分为【<span id="smsCnt">0</span>】条发送</td></tr>
		    </table>
		    </form>
		   </div>
			<div class="button-container">
			    <button class="submit" onclick="tijiao()" id="btnOK" >提交</button>
			    <button type="button" class="return" onclick="openlink('${basePath}/manage/templet/list.action?searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
			</div>	
  </body>
  <script>
  		function insert(){
  			var content = $("#contentSms").val();
  			if(content.indexOf("姓名")>0){
				alert("只能点击一个姓名!")
				return false;
  	  			}
  	  		var aa = $("#contentSms").val();
  	  		var str = "【"+$("#text1").val()+"】";
			aa = aa+str;
			$("#contentSms").val(aa);
  	  		}
  		function insert1(){
  			var content = $("#contentSms").val();
  			if(content.indexOf("申办类型")>0){
				alert("只能点击一个申办类型!")
				return false;
  	  			}
  	  		var aa = $("#contentSms").val();
  	  		var str = "【"+$("#text2").val()+"】";
			aa = aa+str;
			$("#contentSms").val(aa);
  	  		}
  		function insert2(){
  			var content = $("#contentSms").val();
  			if(content.indexOf("申办状态")>0){
				alert("只能点击一个申办状态!")
				return false;
  	  			}
  	  		var aa = $("#contentSms").val();
  	  		var str = "【"+$("#text3").val()+"】";
			aa = aa+str;
			$("#contentSms").val(aa);
  	  		}
  		function insert3(){
			var content = $("#contentSms").val();
			if(content.indexOf("楼层")>0){
				alert("只能点击一个楼层!")
				return false;
	  			}
	  		var aa = $("#contentSms").val();
	  		var str = "【"+$("#text4").val()+"】";
			aa = aa+str;
			$("#contentSms").val(aa);
	  		}
		function insert4(){
			var content = $("#contentSms").val();
			if(content.indexOf("联系电话")>0){
				alert("只能点击一个联系电话!")
				return false;
	  			}
	  		var aa = $("#contentSms").val();
	  		var str = "【"+$("#text5").val()+"】";
			aa = aa+str;
			$("#contentSms").val(aa);
	  		}
  		function tijiao(){
  	  		var name = $("#name").val().trim();
  	  		if(name.length == 0){
  	  	  		alert("请填写模板名称！");
  	  	  		return false;
  	  		}
	  	  	if($("#type_id").val() == ""){
		  	  		alert("请选择类型！");
		  	  		return false;
		  		}
  	  		var content = $("#contentSms").val();
  	  		if(content.indexOf("姓名")>0){
  	  		}else{
  	  	  			alert("请填写正确的模板内容!");
  	  	  			return false;

  	  	  		}
	  	  	if(content.indexOf("申办类型")>0){
		  		}else{
		  	  			alert("请填写正确的模板内容!");
		  	  			return false;
	
		  	  		}
		  	if(content.indexOf("楼层")>0){
	  		}else{
	  	  			alert("请填写正确的模板内容!");
	  	  			return false;

	  	  		}
		  	if(content.indexOf("联系电话")>0){
	  		}else{
	  	  			alert("请填写正确的模板内容!");
	  	  			return false;

	  	  		}
			//有一个事件触发事件可以兼容ie
		  	 
		  	if(confirm('确认提交？')){
		  		setTimeout(function () { $('#btnOK').attr('onclick', '').bind('click', function () { reclick(); }); }, 1);
			  	openlink('${basePath}/manage/templet/insert.action?searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','Form',1);
		  	}
  		}
  </script>
</html>
