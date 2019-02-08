<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>購入結果</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />
<div class=container>
	<h3 class="text-center mt-5">購入が完了しました</h3>
		<div class="row mx-auto"style=width:50%;>
			<div class="text-center mx-auto">
				<a href="Top">
					<button type="button" class="btn btn-muted my-5">買い物を続ける</button>
				</a>
			</div>
			<div class="text-center mx-auto">
				<a href="MyPage">
					<button type="button" class="btn btn-primary my-5">マイページへ</button>
				</a>
			</div>
		</div>
	<h2 class="text-center">購入情報</h2>
	<div style=width:800px; class=mx-auto>
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th width=400>購入日時</th>
					<th width=200>合計金額</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${resultBuy.formatDate}</td>
					<td>${resultBuy.totalPrice }</td>
				</tr>
			</tbody>
		</table>
		<table class="table table-bordered mt-5">
			<thead class="thead-light">
				<tr>
					<th width=400>商品名</th>
					<th width=200>個数</th>
					<th width=200>小計</th>
				</tr>
				<c:forEach items="${resultBuyDetail }" var="item">
					<tr>
						<td>${item.itemName }</td>
						<td>${item.sellNumber }</td>
						<td>${item.itemPrice *item.sellNumber}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>