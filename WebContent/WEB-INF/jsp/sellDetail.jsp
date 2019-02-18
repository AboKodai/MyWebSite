<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>受注詳細</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />
<div class=container>
	<h3 class="text-center mt-5">受注情報</h3>
	<div class=mx-auto>
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th>購入者</th>
					<th>購入日時</th>
					<th>住所</th>
					<th>メールアドレス</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${sell.buyUserName }</td>
					<td>${sell.formatDate }</td>
					<td>${sell.buyUserHomeAddress}</td>
					<td>${sell.buyUserAddress }</td>
				</tr>
			</tbody>
		</table>
		<h3 class="text-center mt-5">商品情報</h3>
		<table class="table table-bordered mt-5">
			<thead class="thead-light">
				<tr>
					<th>商品名</th>
					<th>個数</th>
					<th>小計</th>
				</tr>
				<tr>
					<td>${sell.itemName }</td>
					<td>${sell.itemNumber }</td>
					<td>${sell.itemPrice }円</td>
				</tr>
			</tbody>
		</table>
	</div>
	<form method="post" action="SellDetail">
		<div class="row">
			<div class="text-p-re mr-4 ml-auto">
				<c:if test="${sell.checkbox == 0 }">
					<input type="checkbox" name="checkbox" value="1" class="ml-1" >処理済
				</c:if>
				<c:if test="${sell.checkbox == 1 }">
					<input type="checkbox" name="checkbox" class="ml-1" value="1" checked = "checked" >処理済
				</c:if>
				<input type="hidden" name="buyDetailId" value="${sell.buyDetailId }">
			</div>
			<div class="text-p-re ">
				<input type="submit" value="適用">
			</div>
		</div>
	</form>
	<form action="MyPage" method="get">
				<div class="text-center mx-5">
					<input type="submit" class="btn btn-primary my-5" value="マイページへ">
				</div>
			</form>
</div>
</body>
</html>