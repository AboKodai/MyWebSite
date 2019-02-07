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
	<c:if test="${sysMsg != null }">
		<p class="text-center text-danger"> ${sysMsg }</p>
	</c:if>
	<form action="ItemDeleteFromCart" method="get" class="mx-auto">
		<div class="row mx-auto"style=width:45%;>
			<div class="text-center mx-auto">
				<input type="submit" class="btn btn-muted my-5" value="削除" style=width:110px;>
			</div>
			<div class="text-center mx-auto">
				<a href="Buy">
					 <button type="button" class="btn btn-primary my-5" value="購入手続き"style=width:110px;>購入手続き</button>
				</a>
			</div>
		</div>
		<div class="row">
			<c:forEach items="${cart}" var="item">
				<div class="card mb-3 mx-3" style=width:20rem;height:30rem;>
					<a href="ItemDetail?item_id=${item.itemId }">
						<img class="card-img-top" src="img/${item.failName}" alt="Card image cap" style="max-height:15rem;width:20rem;">
					</a>
					<div class="card-body">
						<p class="card-text">${item.itemName}</p>
						<p class="card-text">${item.itemPrice}円×${item.sellNumber }</p>
						<input type="checkbox" name="itemDelete" value="${item.itemId }"id="${item.itemId }">削除
					</div>
				</div>
			</c:forEach>
		</div>
	</form>
</div>
</body>
</html>