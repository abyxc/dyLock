<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${basePath }/adminLTE/plugins/datatables/dataTables.bootstrap.css">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<!-- <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<!-- <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script> -->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<!-- <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        功能列表
<%--         <small>Optional description頁：${page }</small> --%>
<small>Optional description</small> 
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
        <li class="active">Here</li>
      </ol>
    </section>
     <!-- Main content -->
    <section class="content">
    
    <div class="row">
        <div class="col-xs-12">
          <div class="box">
<!--           	<div class="box-header"> -->
<!--               <h3 class="box-title">Hover Data Table</h3> -->
<!--             </div> -->
            <div >
            <button type="button" class="btn btn-primary bu_left"  onclick="ajax('${basePath }/log/insert.do')">新增</button>
            	 <form id="ajaxForm" >
            	 <button type="button" class="btn btn-primary bu_right"  onclick="ajaxForm('${basePath }/log/logList.do')">搜索</button>
					 <input type="text" class="form-control widths" name="id" value="${id }" placeholder="请输入id" >
					 <input type="text" class="form-control widths" name="creareTime" value="${creareTime }" placeholder="创建时间" >
   				 </form>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>功能名称</th>
                  <th>跳转url</th>
                  <th>创建时间</th>
                  <th>编辑</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${list.authorityName }</td>
                        <td>${list.authorityUrl }</td>
                        <td>${list.createTime }</td>
                         <td>
                          <button type="button" onclick="ajax('${basePath }/log/update.do?id=${amsLog.id }')" class="btn bg-olive btn-flat margin update"><i class="fa fa-fw fa-pencil-square-o"></i>&nbsp;&nbsp;编辑</button>
                         <button type="button" onclick="ajax('${basePath }/log/deleteId.do?id=${amsLog.id }')"  class="btn bg-orange margin"><i class="fa fa-fw fa-remove"></i>&nbsp;&nbsp;删除</button>
                         </td>
                    </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
      <div class="box-footer clearfix">
      	<ul  class="pagination pagination-sm no-margin pull-left">
			<li><a>当前第${page.pageNum}页</a></li>
      		<li><a>总页数:${page.pages}</a></li>
      		<li><a>总共${page.total}条</a></li>
      	</ul>
      	<ul class="pagination pagination-sm no-margin pull-right">
      		<c:if test="${page.hasPreviousPage}">
                        <li><a onclick = "ajax('${basePath }/log/logList.do?pageNum= ${page.prePage}&id=${id }&creareTime=${creareTime }')" href="#">前一页</a></li>
             </c:if>
             <c:forEach items="${page.navigatepageNums}" var="nav">
                    <c:if test="${nav == page.pageNum}">
                        <li><a style="font-weight: bold;">${nav}</a></li>
                    </c:if>
                    <c:if test="${nav != page.pageNum}">
                    	
                       <li><a onclick = "ajax('${basePath }/log/logList.do?pageNum=${nav}&id=${id }&creareTime=${creareTime }')" href="#">${nav}</a></li>
                    </c:if>
              </c:forEach>
              <c:if test="${page.hasNextPage}">
                  <li><a onclick = "ajax('${basePath }/log/logList.do?pageNum=${page.nextPage}&id=${id }&creareTime=${creareTime }')" href="#">下一页</a></li>
              </c:if>
      	</ul>
      </div>
    </section>
    <script src="${basePath }/adminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${basePath }/adminLTE/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<style type="text/css">
		.widths{
			width: 15%;
			float: right;
			margin-right: 1%;
		}
		.bu_right{
			float: right;
			margin-right: 2%;
		}
		.bu_left{
			float: left;
			margin-left: 2%;
		}
		.from_div1{
			height: 50px;
			widows: 600px;
			border-color: red 2px;
		}
/* 		.margin { */
/* 			margin-left: 15px; */
/* 		} */
	</style>
	<script>
// 	$(document).ready(function(){
// 		$('.bu_right').click(function(){
// 			$.ajax({
// 				async:true, 
// 		         type: "post",
// 		         url: "${basePath }/log/logList.do",
// 		         data: $("#form_search").serialize(),// 要提交的表单
// 		         success: function(d) {
// 		        	 $(".content-wrapper").empty();
// 	            	 $(".content-wrapper").html(d);
// 		        	 }
// 		     });
			
// 		});
// 	})
  $(function () {
    $('#example2').DataTable({
      "paging": false,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": false,
      "autoWidth": false
    });
  });
</script>