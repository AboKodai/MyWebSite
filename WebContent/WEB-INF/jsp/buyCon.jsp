<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>購入確認</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />
<div class=container>
	<h3 class="text-center mgn-top">購入商品</h3>
	<div style=width:800px; class=mx-auto>
		<table class="table table-bordered" >
			<thead>
				<tr>
					<th width=400>商品名</th>
					<th width=200>数量</th>
					<th width=200>小計</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cart}" var="item">
					<c:if test="${item.delivaryMethod == 1 }">
						<tr>
							<td>${item.itemName }</td>
							<td>${item.sellNumber }</td>
							<td>${item.itemPrice * item.sellNumber }円</td>
						</tr>
					</c:if>
					<c:if test="${item.delivaryMethod == 2 }">
						<tr>
							<td>${item.itemName }</td>
							<td>${item.sellNumber }</td>
							<td>${item.itemPrice * item.sellNumber}円+送料</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
			<div class="text-right my-5 h4 ">
				合計：${buy.totalPrice}円<c:if test="${flag }">＋送料</c:if>
			</div>
		<div class="text-right my-5">
			<a href="BuyResult">
				<button type="button" class="btn btn-primary mt-4" style=width:200px; >購入確定</button>
			</a>
			<br>
		</div>
	</div>
</div>
</body>
</html>