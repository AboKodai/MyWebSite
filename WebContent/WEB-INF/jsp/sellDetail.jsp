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
					<th>配送希望日</th>
					<th>住所</th>
					<th>メールアドレス</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>ユーザ名</td>
					<td>yyyy年mm月dd日hh時mm分ss秒</td>
					<td>ｍｍ月ｄｄ日</td>
					<td>購入ユーザ住所</td>
					<td>購入ユーザメールアドレス</td>
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
					<td>モモ</td>
					<td>8</td>
					<td>8000円</td>
				</tr>
			</tbody>
		</table>
	</div>
	<form method="post" action="#">
		<div class="row">
			<div class="text-p-re mr-4 ml-auto">
				<input type="checkbox" name="">処理済にする
			</div>
			<div class="text-p-re ">
				<input type="submit" value="適用">
			</div>
		</div>
	</form>
	<form action="#" method="get">
				<div class="text-center mx-5">
					<input type="submit" class="btn btn-primary my-5" value="マイページへ">
				</div>
			</form>
</div>
</body>
</html>