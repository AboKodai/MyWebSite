<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<div class=container>
		<div class="card text-center mx-auto mgn-top" style=width:500px;>
			<div class="card-header bgc-main">
				<h2>食料品ECサイト</h2>
				<h4>ログイン</h4>
			</div>
			<form method="post" action="#">
				<div class="card-body my-3">
					<p class="card-text mb-5">
						ログインID：<input type="text" name="loginId">
					</p>
					<p class="card-text mb-5">
						パスワード：<input type="password" name="password">
					</p>
					<input type="submit" class="btn btn-primary" value="ログイン">
				</div>
			</form>
		</div>
		<p class="text-right mt-2">会員登録がまだの方は<a href="#">こちら</a>
	</div>
</body>
</html>