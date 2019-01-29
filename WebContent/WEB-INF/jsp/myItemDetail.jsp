<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>出品商品詳細</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />
<div class=container>
	<h3 class="mgn-top text-center">出品商品詳細</h3>

	<img class="card-img-top" src="img/image.jpg" alt="Card image cap">
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			商品名
		</div>
		<div class="col-sm-6 h5">
			サンプル名
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			商品説明
		</div>
		<div class="col-sm-6 h5">
			商品の詳しい説明です
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			単価
		</div>
		<div class="col-sm-6 h5">
			10000円
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			残数
		</div>
		<div class="col-sm-6 h5">
			777777個
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			送料
		</div>
		<div class="col-sm-6 h5">
			出品者負担
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			種類
		</div>
		<div class="col-sm-6 h5">
			<ul>
				<li>飯</li>
				<li>麺</li>
				<li>水</li>
			</ul>
		</div>
	</div>
</div>

</body>
</html>