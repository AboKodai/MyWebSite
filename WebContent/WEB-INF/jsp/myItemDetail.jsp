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

	<p class="text-center">
		<img src="img/${item.failName }" alt="Card image cap" width="500px" height="500px">
	</p>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			商品名
		</div>
		<div class="col-sm-6 h5">
			${item.itemName}
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			商品説明
		</div>
		<div class="col-sm-6 h5">
			${item.itemDetail }
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			単価
		</div>
		<div class="col-sm-6 h5">
			${item.itemPrice }円
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			残数
		</div>
		<div class="col-sm-6 h5">
			${item.itemNumber }
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			送料
		</div>
		<div class="col-sm-6 h5">
			${dmb.delivaryMethod }
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-sm-4"></div>
		<div class="col-sm-2 h5">
			種類
		</div>
		<div class="col-sm-6 h5">
			<c:forEach items="${typeList}" var="type">
					<c:if test="${itemTypeList.contains(type.itemTypeId)}">
						<ul>
							<li>${type.itemTypeName }</li>
						</ul>
					</c:if>
			</c:forEach>
		</div>
	</div>
</div>

</body>
</html>