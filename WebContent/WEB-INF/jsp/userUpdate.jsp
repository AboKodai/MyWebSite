<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>ユーザ情報更新</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />

<div class=container>
		<div class="card mx-auto mgn-top" style=width:500px;>
			<div class="card-header bg-right text-center">
				<h3>会員情報更新</h3>
			</div>
			<form method="post" action="#">
				<div class="card-body my-3">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="card-text mb-4 col-sm-4">
							ログインID
						</div>
						<div class="col-sm-5">
							<input type="text" value="loginIdHogehoge" name="loginId">
						</div>
						<div class="col-sm-2"></div>
						<div class="card-text mb-4 col-sm-4">
							ユーザ名
						</div>
						<div class="col-sm-5">
							<input type="text" value="loginIdHogehoge" name="userName">
						</div>

						<div class="col-sm-2"></div>
						<div class="card-text mb-4 col-sm-4">
							住所
						</div>
						<div class="col-sm-5">
							<input type="text" value="loginIdHogehoge" name="homeAddress">
						</div>
						<div class="col-sm-2"></div>
						<div class="card-text mb-4 col-sm-4">
							メールアドレス
						</div>
						<div class="col-sm-5">
							<input type="text" value="loginIdHogehoge" name="address">
						</div>
					</div>
					<div class="row">
						<div class="mx-auto">
							<input type="submit" class="btn btn-muted mt-4" value="キャンセル">
						</div>
						<div class="mx-auto">
							<input type="submit" class="btn btn-primary mt-4" style=width:110px; value="更新">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>