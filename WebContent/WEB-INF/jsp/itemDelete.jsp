<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>商品削除</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />
	<div class=container>
		<div class="card mx-auto mgn-top" style=width:500px;>
			<div class="card-header bg-right text-center">
				<h3>商品削除</h3>
			</div>
			<form method="post" action="#">
				<input type="hidden" value="${item.itemId}" name="itemId">
				<img class="card-img-top" src="img/${item.failName }" alt="Card image cap" style="height:35rem;width:30rem;">
				<p class=text-center>${item.itemName}</p>
				<p class=text-center>商品情報を削除します。よろしいですか？</p>
				<div class="row">
					<div class="mx-auto">
						<a href="MyPage">
							<button type="button" class="btn btn-muted mt-4">キャンセル</button>
						</a>
					</div>
					<div class="mx-auto mb-5">
						<input type="submit" class="btn btn-primary mt-4" style=width:110px; value="削除">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>