<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*,java.sql.Timestamp" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>华西期货保证金计算系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminUser.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery.min.js"></script>
</head>

<body>
	<div id="banner">
		<div class="info">
			<span class="l">您好，欢迎使用华西期货保证金计算系统！</span>
			<span class="r">
				<jsp:useBean id="now" class="java.util.Date" />
				<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm" />
			</span>
		</div>
	</div>
	<div id="wrap">
		<div id="right">
			<h4>上传表单</h4>
	        <div class="pass" style="margin-top:12px;">
	        <form name="editFrm" action="add.do" enctype="multipart/form-data" method="post" onsubmit="return checkSubmit();">
				<div class="item">
					<span style="font-size:15px">选择文件(</span><span style="font-family: Arial; font-size: 10px;color: red;">必须上传符合格式的Excel文件</span>)：<input type="file" name="excel" id="excel"/>
				</div>
	            <div class="btncontain">
	            	<input class="adduserbtn" type="submit" value="确认添加"/>
	            </div>
	            <div class="item" style="color:red;font-size:15px">${result}</div>
	        </form> 
	        </div>					
		</div>
	</div>
	<div id="footer">Copyright &copy; 2016 华西期货保证金计算系统</div>
</body>

<script>
function checkSubmit() {
	if (document.getElementById("excel").value == "") {
		alert("请选择Excel文件！");
		return false;
	} else {
		var arr = document.getElementById("excel").value.split(".");
		var last = arr.length - 1;
		if ((arr[last] != "xls" && arr[last] != "XLS") && (arr[last] != "xlsx" && arr[last] != "XLSX")) {
			alert("上传的Excel文件必须为xls或xlsx格式！");
			return false;
		}
	}
}
</script>
</html>