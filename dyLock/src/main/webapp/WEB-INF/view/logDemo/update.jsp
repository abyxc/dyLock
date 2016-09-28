<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="box box-info div_margin">
	<div class="box-header with-border">
		<h3 class="box-title">编辑日志</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
<!-- 	<form class="form-horizontal" id="ajaxForm"> -->
<%-- 		<input type="hidden" value="${list.id }" name="id" > --%>
<!-- 		<div class="box-body"> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label for="inputEmail3" class="col-sm-2 control-label">耗时：</label> -->
<!-- 					<div class="col-sm-10"> -->
<%-- 						<input type="text" class="form-control" value="${list.time }" name="time"> --%>
<!-- 					</div> -->
<!-- 					<label for="inputEmail3" class="col-sm-2 control-label">cookies：</label> -->
<!-- 					<div class="col-sm-10"> -->
<%-- 						<input type="text" class="form-control" value="${list.cookies }" name="cookies"> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			<div class="form-group"> -->
<!-- 				<label for="inputPassword3" class="col-sm-2 control-label">创建时间：</label> -->
<!-- 				<div class="col-sm-10"> -->
<%-- 					<input type="text" class="form-control" value="${list.creareTime }"  name="creareTime"> --%>
<!-- 				</div> -->
<!-- 				<label for="inputPassword3" class="col-sm-2 control-label">ip</label> -->
<!-- 				<div class="col-sm-10"> -->
<%-- 						<input type="text" class="form-control" value="${list.ip }" name="ip"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<!-- /.box-body -->
<!-- 		<div class="box-footer"> -->
<%-- 			<button type="submit" class="btn btn-default" onclick="ajax('${basePath }/log/logList.do')">返回</button> --%>
<%-- 			<button type="submit" class="btn btn-info pull-right" onclick="ajaxForm('${basePath }/log/saveUpadte.do')">提交</button> --%>
<!-- 		</div> -->
		<!-- /.box-footer -->
<!-- 	</form> -->
 <table id="example2" class="table table-bordered table-hover"></table>
</div>
<style type="text/css">
	.input_w{
		width: 50%;
		float: right;
		margin-right: 1%;
	}
	.div_margin{
		margin: 0 auto;
		width: 50%;
	}
</style>
<script type="text/javascript">
$(function () {
    $('#example2').DataTable({
      "paging": false,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": false,
      "autoWidth": false,
      "processing": true,
      "serverSide": true,
      "ajax": "${basePath }/log/updateAjax.do?id=85f6af0f0f434f41af8d8133efef17fa",
      "columns":[
                 {"data":"id"},
                 {"data":"creareTime"},
                 {"data":"logTxt"},
                 {"data":"ip"}
                 
                
                 ]
    });
  });
</script>