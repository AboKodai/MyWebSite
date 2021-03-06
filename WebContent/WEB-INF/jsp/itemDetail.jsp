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
		<img class="card-img-top mr-5 mgn-top" src="img/${item.failName}" alt="Card image cap" style="max-width:500px; max-height:500px;">
	</div>
	<div class="right-content">
		<h3 class="mgn-top">${item.itemName }</h3>
		<h5 class="mt-4"><説明><br>${item.itemDetail }</h5>
		<h5 class="mt-4">価格：${item.itemPrice }円</h5>
		<h5 class="mt-4">送料：${dmb.delivaryMethod }</h5>
		<form action="ItemDetail" method = "post">
			<h5 class="mt-4">
				購入数
				<c:if test="${item.itemNumber == 0 }">
					：<span class=text-danger>${sysMsg }</span>
				</c:if>
				<c:if test="${item.itemNumber != 0 }">
					<select name="sellNumber">
						<c:forEach var="i" step="1" begin="1" end="${item.itemNumber }">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</c:if>
				<input type="hidden" name="itemId" value="${item.itemId }">
			</h5>
			<c:if test="${item.itemNumber != 0 }">
				<div class="mx-auto">
					<button type="submit" class="btn btn-danger mt-4" value="addItem" name="select">カートに入れる</button>
				</div>
			</c:if>
		</form>
	</div>
</div>


</body>
</html>