<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>退会</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />

<div class=container>
	<div class="card mx-auto mgn-top" style=width:500px;>
		<div class="card-header bg-right text-center">
			<h2>退会</h2>
		</div>
		<form method="post" action="#">
			<p class="text-danger p-5">退会処理を行うと現在登録されている会員情報・商品情報はすべて削除されます。</p>

			<p class=text-center>userNameさんの退会処理を行ってもよろしいですか？

				<div class="row">
					<div class="mx-auto">
						<input type="submit" class="btn btn-muted mt-4" value="キャンセル">
					</div>
					<div class="mx-auto mb-5">
						<input type="submit" class="btn btn-primary mt-4" style=width:110px; value="退会する">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>