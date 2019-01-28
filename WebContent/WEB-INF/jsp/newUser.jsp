<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>新規会員登録</title>
</head>
<body>
<div class=container>
	<div  class="mx-auto" style=width:500px;>
		<div class="card mgn-top">
			<div class="card-header bgc-main text-center">
				<h2>食料品ECサイト</h2>
				<h4>会員登録</h4>
			</div>
			<form method="post" action="#">
				<div class="card-body my-3 row">
					<div class="col-sm-2"></div>
					<div class="card-text mb-4 col-sm-4">
						ログインID
					</div>
					<div class="col-sm-5">
						<input type="text" name="loginId" >
					</div>
					<div class="col-sm-2"></div>
					<div class="card-text mb-4 col-sm-4">
						ユーザ名
					</div>
					<div class="col-sm-5">
						<input type="text" name="userId" >
					</div>
					<div class="col-sm-2"></div>
					<div class="card-text mb-4 col-sm-4">
						パスワード
					</div>
					<div class="col-sm-5">
						<input type="password" name="password" >
					</div>
					<div class="col-sm-2"></div>
					<div class="card-text mb-4 col-sm-4">
						パスワード確認
					</div>
					<div class="col-sm-5">
						<input type="password" name="passwordCon" >
					</div>
					<div class="col-sm-2"></div>
					<div class="card-text mb-4 col-sm-4">
						住所
					</div>
					<div class="col-sm-5">
						<input type="text" name="homeAddress" >
					</div>
					<div class="col-sm-2"></div>
					<div class="card-text mb-5 col-sm-4">
						メールアドレス
					</div>
					<div class="col-sm-5">
						<input type="text" name="address" >
					</div>
					<input type="submit" class="btn btn-primary mx-auto" value="確認画面へ">
				</div>
			</form>
		</div>
		<p class="text-right mt-2">会員登録済の方は<a href="#">こちら</a>
	</div>
</div>
</body>
</html>