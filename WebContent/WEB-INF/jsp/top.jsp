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
<title>食料品ECサイト</title>
</head>
<body>

	<jsp:include page="/baselayout/header.jsp" />

	<!-- sideBar -->
	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<form method="get" action="SearchResult">
					<input class="col-md-11" type="search" name="searchWord"
						style="margin-top: 10px;" placeholder="検索ワード">
					<p style="margin-top: 30px;">＜ジャンル＞</p>
					<c:forEach var="typeList" items="${typeList }">
						<p>
							<input type="checkbox" name="type"value="${typeList.itemTypeId }" style="vertical-align: middle;">${typeList.itemTypeName }
						</p>
					</c:forEach>
					<p>
						<input type="submit" value="絞り込み">
				</form>
			</nav>

			<!-- /sideBar -->

			<!-- body -->
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

			<div class="row">
				<div class=col-md-4></div>
				<div class="col-md-4 text-center my-5">
					<h3>商品一覧</h3>
				</div>
				<div class=col-md-4></div>
				<c:forEach var="randomList" items="${randomList }">
					<div class="col-md-3 text-center my-3">
						<div class="card" style="width: 20rem;height:30rem">
							<a href="ItemDetail?item_id=${randomList.itemId }">
								<img class="card-img-top" src="img/${randomList.failName }"alt="Card image cap" style="max-height:15rem;width:20rem;">
							</a>
							<div class="card-body">
								<p class="card-text">${randomList.itemName }</p>
								<p class="card-text">${randomList.itemPrice }円</p>
								<c:if test="${randomList.itemNumber == 0 }">
									<p class="text-danger">売り切れ</p>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

</body>
</html>