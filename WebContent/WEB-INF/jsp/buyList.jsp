<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>購入一覧</title>
</head>
<body>

<jsp:include page="/baselayout/header.jsp" />

<div class=container>
	<h3 class="text-center mgn-top">購入一覧</h3>
	<c:if test="${buyList != null }">
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th></th>
					<th>購入日時</th>
					<th>合計</th>
				</tr>
				<c:forEach items="${buyList }" var="buy">
					<tr>
						<td class="text-center"><a href="BuyDetail?buyId=${buy.buyId }" class="btn bg-info" style=color:white;>詳細</a>
						<td class="h5">${buy.formatDate }</td>
						<td class="h5">${buy.totalPrice }円</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${buyList == null }">
		<p class="text-center h4 text-danger">購入した商品はありません</p>
	</c:if>
</div>



</body>
</html>