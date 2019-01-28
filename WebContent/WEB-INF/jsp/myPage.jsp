<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>マイページ</title>
</head>
<body>

<jsp:include page="/baselayout/header.jsp" />

<div class=container>
	<h3 class="text-center mgn-top">会員情報</h3>
	<table class="table table-bordered bg-light">
		<tbody>
			<tr>
				<td>ユーザ名：userName</td>
				<td>ログインID：loginId</td>
				<td>生年月日：yyyy年mm月dd日</td>
			</tr>
			<tr>
				<td>メールアドレス：address</td>
				<td colspan="2">住所：homeAddress</td>
		</tbody>
	</table>
	<div class=row>
		<form action="#" method="get" class="mx-auto">
			<div class="text-center">
				<input type="submit" class="btn btn-success my-5" value="基本情報変更">
			</div>
		</form>
		<form action="#" method="get" class="mx-auto">
			<div class="text-center">
				<input type="submit" class="btn btn-primary my-5" value="パスワード変更">
			</div>
		</form>
		<form action="#" method="get" class="mx-auto">
			<div class="text-center">
				<input type="submit" class="btn btn-danger my-5" value="退会">
			</div>
		</form>
	</div>
</div>
	<hr>

<div class=container>
	<h3 class="text-center">最新の購入</h3>
	<table class="table table-bordered">
		<thead class="thead-light">
			<tr>
				<th>購入日時</th>
				<th>希望配送日</th>
				<th>合計</th>
			</tr>
			<tr>
				<td>yyyy年mm月dd日</td>
				<td>mm月dd日</td>
				<td>8000円</td>
			</tr>
		</tbody>
	</table>
	<p class="text-right">
		<a href="#">購入履歴一覧</a>
	</p>
</div>
<hr>

<div class="row px-5">
	<div class="col-sm-6 mgn-top">
		<h3 class="text-center">受注一覧</h3>
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th>発送状況</th>
					<th>購入者</th>
					<th>購入日時</th>
					<th>配送希望日</th>
					<th>住所</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a href="#" class="btn bg-info mr-1" style=color:white;>詳細</a>発送処理<input type="checkbox"  class="ml-1"></td>
					<td>ユーザ名</td>
					<td>yyyy年mm月dd日hh時mm分ss秒</td>
					<td>ｍｍ月ｄｄ日</td>
					<td>購入ユーザ住所</td>
				</tr>
				<tr>
					<td><a href="#" class="btn bg-info mr-1" style=color:white;>詳細</a>発送処理<input type="checkbox" class="ml-1"></td>
					<td>ユーザ名</td>
					<td>yyyy年mm月dd日hh時mm分ss秒</td>
					<td>ｍｍ月ｄｄ日</td>
					<td>購入ユーザ住所</td>
				</tr>
				<tr>
					<td><a href="#" class="btn bg-info mr-1" style=color:white;>詳細</a>発送処理<input type="checkbox" class="ml-1"></td>
					<td>ユーザ名</td>
					<td>yyyy年mm月dd日hh時mm分ss秒</td>
					<td>ｍｍ月ｄｄ日</td>
					<td>購入ユーザ住所</td>
				</tr>
				<tr>
					<td><a href="#" class="btn bg-info mr-1" style=color:white;>詳細</a>発送処理<input type="checkbox" class="ml-1"></td>
					<td>ユーザ名</td>
					<td>yyyy年mm月dd日hh時mm分ss秒</td>
					<td>ｍｍ月ｄｄ日</td>
					<td>購入ユーザ住所</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="col-sm-6 mgn-top">
		<h3 class="text-center">出品商品一覧</h3>
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th></th>
					<th>商品名</th>
					<th>価格</th>
					<th>残数</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<a href="#" class="btn bg-info" style=color:white;>詳細</a>
						<a href="#" class="btn bg-warning" style=color:white;>更新</a>
						<a href="#" class="btn bg-danger" style=color:white;>削除</a>
					</td>
					<td>もも</td>
					<td>500円</td>
					<td>10個</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>



</body>
</html>