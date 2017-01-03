<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<title>华西期货保证金计算平台</title>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>华西期货保证金计算平台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link href="${pageContext.request.contextPath}/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/flat-ui.min.css" rel="stylesheet">		
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="h6 text-center">华西期货保证金计算平台</div>
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-lg-2 control-label">交易所</label>
							<div class="col-lg-10">
								<select id="exchange" name="exchange" data-toggle="select" class="form-control select select-primary">
									<option value=""> --请选择-- </option>
									<c:forEach var="exchange" items="${exchanges}">
										<option value="${exchange}">${exchange}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">品种</label>
							<div class="col-lg-10">
								<select id="name" name="name" data-toggle="select" class="form-control select select-primary"></select>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPoint" class="col-lg-2 control-label">点位</label>
							<div class="col-lg-10">
								<input type="number" class="form-control" id="money" placeholder="点位"/>
							</div>
						</div>
						<div class="form-group">
							<label for="inputHand" class="col-lg-2 control-label">手数</label>
							<div class="col-lg-10">
								<input type="number" class="form-control" id="hand" placeholder="手数"/>
							</div>
						</div>
						<div class="form-group">
						    <div class="col-lg-12">
						      <button id="calculate" type="button" class="btn btn-primary">获取结果</button>
						    </div>
						</div>
					</form>
					<div class="form-group has-success">
				  		<label class="control-label" for="inputSuccess1">保证金</label>
						<input type="text" class="form-control" id="result" readonly/>
						<span class="form-control-feedback fui-check"></span>
					</div>					
				</div>
			</div>			
		</div>
		<script src="${pageContext.request.contextPath}/js/vendor/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/flat-ui.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/application.js"></script>
	    <script src="${pageContext.request.contextPath}/js/options.js"></script>
	</body>
</html>