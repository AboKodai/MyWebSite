<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>登録内容確認</title>
</head>
<body>
<div class=container>
		<div class="card mx-auto mgn-top" style=width:500px;>
			<div class="card-header bgc-main text-center">
				<h2>食料品ECサイト</h2>
				<h4>会員登録</h4>
			</div>
			<form method="post" action="#">
				<div class="card-body my-3">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="card-text col-sm-4">
							ログインID
						</div>
						<div class="col-sm-5 mb-4">
							${user.loginId}
						</div>
						<div class="col-sm-2"></div>
						<div class="card-text col-sm-4">
							ユーザ名
						</div>
						<div class="col-sm-5 mb-4">
							${user.userName}
						</div>
						<div class="col-sm-2"></div>
						<div class="card-text col-sm-4">
							生年月日
						</div>
						<div class="col-sm-5 mb-4">
							${user.formatDate}
						</div>
						<div class="col-sm-2"></div>
						<div class="card-text col-sm-4">
							住所
						</div>
						<div class="col-sm-5 mb-4">
							${user.homeAddress}
						</div>
						<div class="col-sm-2"></div>
						<div class="card-text mb-4 col-sm-4">
							メールアドレス
						</div>
						<div class="col-sm-5 mb-4">
							${user.address}
						</div>
					</div>
					<p class="text-center text-danger my-4">以上の内容で登録します。よろしいですか？</p>
					<div class="row">
						<div class="mx-auto">
							<button type="submit" name="confirm" class="btn btn-muted mt-4" value="cancel" >訂正</button>
						</div>
						<div class="mx-auto">
							<button type="submit" name="confirm" class="btn btn-primary mt-4" value="submit">登録</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>