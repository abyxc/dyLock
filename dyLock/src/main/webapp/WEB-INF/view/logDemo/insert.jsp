<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="box box-info div_margin">
	<div class="box-header with-border">
		<h3 class="box-title">新增日志</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<form class="form-horizontal" id="ajaxForm">
		<div class="box-body">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">耗时：</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="time" placeholder="耗时">
					</div>
					<label for="inputEmail3" class="col-sm-2 control-label">cookies：</label>
					<div class="col-sm-10">
						<input type="text" class="form-control"  name="cookies" placeholder="cookies">
					</div>
				</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">创建时间：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control"  name="creareTime" placeholder="创建时间">
				</div>
				<label for="inputPassword3" class="col-sm-2 control-label">ip</label>
				<div class="col-sm-10">
						<input type="text" class="form-control"  name="ip">
				</div>
			</div>
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<button type="submit" class="btn btn-default" onclick="ajax('${basePath }/log/logList.do')">返回</button>
			<button type="submit" class="btn btn-info pull-right" onclick="ajaxForm('${basePath }/log/saveInsert.do')">提交</button>
		</div>
		<!-- /.box-footer -->
	</form>
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