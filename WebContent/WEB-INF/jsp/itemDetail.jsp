<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>商品詳細</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />
<div class=container>
	<div class="left-content">
		<img class="card-img-top mr-5 mgn-top" src="img/image.jpg" alt="Card image cap" style="max-width:500px; max-height:500px;">
	</div>
	<div class="right-content">
		<h3 class="mgn-top">サンプル商品名</h3>
		<h5 class="mt-4"><説明><br>しょうひんしょうさいだよぉぉぉぉぉぉぉぉぉぉぉぉぉぉぉぉぉぉぉぉああああああああああああああああああああああ</h5>
		<h5 class="mt-4">価格：100000円</h5>
		<h5 class="mt-4">
			購入数
			<select name="buyNumber">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
			</select>
		</h5>
		<div class="mx-auto">
			<input type="submit" class="btn btn-danger mt-4" value="カートに入れる">
		</div>
		<div class="mx-auto mb-5">
			<input type="submit" class="btn btn-primary mt-4" style=width:140px; value="購入手続き">
		</div>
	</div>
</div>


</body>
</html>