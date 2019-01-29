<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>ユーザ一覧</title>

</head>
<body>

	<jsp:include page="/baselayout/header.jsp" />


	<div class="container">
		<h3 class="text-center mgn-top">ユーザ一覧</h3>
		<div>
			<form method="post" action="#">
				<div class="text-center row">
					<div class="col-sm-3"></div>
					<div class="col-sm-3 my-3">ログインID：</div>
					<div class="col-sm-3"><input type="text" name="loginId" style="width: 250px"></div>
					<div class="col-sm-3"></div>
					<div class="col-sm-3"></div>
					<div class="col-sm-3 my-3">ユーザ名： </div>
					<div class="col-sm-3"><input type="text" name="name" style="width: 250px"></div>
					<div class="col-sm-3"></div>
					<div class="col-sm-3"></div>
					<div class="col-sm-3 my-3">生年月日：</div>
					<div class="col-sm-3"><input type="date" name="birthDateMin" style="width: 115px">～<input
							type="date" name="birthDateMax" style="width: 115px"></div>
					<div class="col-sm-3"></div>
				</div>
				<p class="text-center">
					<input type="submit" value="検索" style="width: 100px">
				</p>
			</form>
		</div>
		<hr size="5" color="black">
		<table class="table table-bordered mt-5">
			<thead class="thead-light">
				<tr>
					<th width="150px"></th>
					<th width="150px">ログインID</th>
					<th width="150px">ユーザ名</th>
					<th width="150px">生年月日</th>
					<th width="150px">住所</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<div class=row>
							<div class="col-sm-6">
								<a href="#" class="btn bg-info" style=color:white;>詳細</a>
							</div>
							<div class="col-sm-6">
								<a href="#" class="btn bg-danger" style=color:white;>削除</a>
							</div>
						</div>
					</td>
					<td>loginId</td>
					<td>userName</td>
					<td>birthDate</td>
					<td>homeAddress</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>