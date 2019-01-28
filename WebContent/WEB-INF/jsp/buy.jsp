<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>購入</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />
<div class=container>
	<h3 class="text-center mgn-top">購入商品</h3>
	<div style=width:800px; class=mx-auto>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th width=400>商品名</th>
					<th width=200>数量</th>
					<th width=200>小計</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>タン</th>
					<td>1</td>
					<td>200</td>
				</tr>
				<tr>
					<th>バラ</th>
					<td>100</td>
					<td>100000</td>
				</tr>
				<tr>
					<th>モモ</th>
					<td>8</td>
					<td>8000</td>
				</tr>
			</tbody>
		</table>
		<div class="text-right my-5">配送希望日
			<input type="date" name="deliveryDate">
		</div>
		<div class="text-right my-5">
			<input type="submit" class="btn btn-primary mt-4" style=width:200px; value="購入確認画面へ">
		</div>
	</div>
</div>
</body>
</html>