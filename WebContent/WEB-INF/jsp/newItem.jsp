<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>商品登録</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />
<div class=container>
	<h3 class="mgn-top text-center">商品登録</h3>

		<form method="post" action="#">
			<div class="row mt-5">
				<div class="col-sm-4"></div>
				<div class="col-sm-2 h5">
					商品名
				</div>
				<div class="col-sm-6">
					<input type="text" name="itemName" style=width:350px; required>
				</div>
			</div>
			<div class="row mt-5">
				<div class="col-sm-4"></div>
				<div class="col-sm-2 h5">
					商品説明
				</div>
				<div class="col-sm-6">
					<input type="text" name="itemDetail" style=width:350px;height:400px; required>
				</div>
			</div>
			<div class="row mt-5">
				<div class="col-sm-4"></div>
				<div class="col-sm-2 h5">
					価格
				</div>
				<div class="col-sm-6 h5">
					<input type="number" name="itemPrice" style=width:150px; class="mr-3" required>円
				</div>
			</div>
			<div class="row mt-5">
				<div class="col-sm-4"></div>
				<div class="col-sm-2 h5">
					個数
				</div>
				<div class="col-sm-6 h5">
					<input type="number" name="itemNumber" style=width:150px; class="mr-3" required>個
				</div>
			</div>
			<div class="row mt-5">
				<div class="col-sm-4"></div>
				<div class="col-sm-2 h5">
					種類
				</div>
				<div class="col-sm-6 h5">
					<c:forEach var="itbList" items="${itbList }">
						<div class="form-inline mb-3">
							<input type="checkbox" name="type" value="${itbList.itemTypeId }" ><div>${itbList.itemTypeName }</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="row mt-5">
				<div class="col-sm-4"></div>
				<div class="col-sm-2 h5">
					送料
				</div>
				<div class="col-sm-6 h5">
					<select name="deliveryMethod" style=width:150px; required>
						<c:forEach var="dmbList" items="${dmbList }">
							<option value=${dmbList. delivaryMethodId }>${dmbList.delivaryMethod}</option>
						</c:forEach>
					</select>
				</div>
			</div>
				<div class="row mt-5">
				<div class="col-sm-4"></div>
				<div class="col-sm-2 h5">
					写真
				</div>
				<div class="col-sm-6 h5">
					<input type="file" name="fileName"  class="mr-3">
				</div>
			</div>
			<div class="row mx-auto"style=width:40%;>
				<div class="mx-auto">
					<input type="submit" class="btn btn-muted my-5" value="キャンセル">
				</div>
				<div class="mx-auto">
					<input type="submit" class="btn btn-primary my-5" value="登録" style=width:110px;>
				</div>
			</div>

		</form>
</div>

</body>
</html>