<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>カート</title>
</head>
<body>

<jsp:include page="/baselayout/header.jsp" />

<div class="container">
	<h3 class="mgn-top text-center">カート</h3>
	<div class="row mx-auto"style=width:45%;>
		<form action="#" method="get" class="mx-auto">
			<div class="text-center">
				<input type="submit" class="btn btn-muted my-5" value="削除" style=width:110px;>
			</div>
		</form>
		<form action="#" method="get" class="mx-auto">
			<div class="text-center">
				<input type="submit" class="btn btn-primary my-5" value="購入手続き"style=width:110px;>
			</div>
		</form>
	</div>
	<div class="row">
		<div class="card mb-3 mx-3" style=width:20%;>
			<img class="card-img-top" src="img/image.jpg" alt="Card image cap">
			<div class="card-body">
				<p class="card-text">サンプル名</p>
				<p class="card-text">1000円</p>
				<input type="checkbox" name="" value="">カートから削除
			</div>
		</div>
		<div class="card mb-3 mx-3" style=width:20%;>
			<img class="card-img-top" src="img/image.jpg" alt="Card image cap">
			<div class="card-body">
				<p class="card-text">サンプル名</p>
				<p class="card-text">1000円</p>
				<input type="checkbox" name="" value="">カートから削除
			</div>
		</div>
		<div class="card mb-3 mx-3" style=width:20%;>
			<img class="card-img-top" src="img/image.jpg" alt="Card image cap">
			<div class="card-body">
				<p class="card-text">サンプル名</p>
				<p class="card-text">1000円</p>
				<input type="checkbox" name="" value="">カートから削除
			</div>
		</div>
		<div class="card mb-3 mx-3" style=width:20%;>
			<img class="card-img-top" src="img/image.jpg" alt="Card image cap">
			<div class="card-body">
				<p class="card-text">サンプル名</p>
				<p class="card-text">1000円</p>
				<input type="checkbox" name="" value="">カートから削除
			</div>
		</div>
		<div class="card mb-3 mx-3" style=width:20%;>
			<img class="card-img-top" src="img/image.jpg" alt="Card image cap">
			<div class="card-body">
				<p class="card-text">サンプル名</p>
				<p class="card-text">1000円</p>
				<input type="checkbox" name="" value="">カートから削除
			</div>
		</div>
		<div class="card mb-3 mx-3" style=width:20%;>
			<img class="card-img-top" src="img/image.jpg" alt="Card image cap">
			<div class="card-body">
				<p class="card-text">サンプル名</p>
				<p class="card-text">1000円</p>
				<input type="checkbox" name="" value="">カートから削除
			</div>
		</div>
		<div class="card mb-3 mx-3" style=width:20%;>
			<img class="card-img-top" src="img/image.jpg" alt="Card image cap">
			<div class="card-body">
				<p class="card-text">サンプル名</p>
				<p class="card-text">1000円</p>
				<input type="checkbox" name="" value="">カートから削除
			</div>
		</div>
	</div>
</div>
</body>
</html>