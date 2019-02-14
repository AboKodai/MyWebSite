<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>マイページ</title>
</head>
<body>

<jsp:include page="/baselayout/header.jsp" />

<div class=container>
	<h3 class="text-center mgn-top">会員情報</h3>
	<table class="table table-bordered bg-light">
		<tbody>
			<tr>
				<td>ユーザ名：${userInfo.userName }</td>
				<td>ログインID：${userInfo.loginId }</td>
				<td>生年月日：${userInfo.formatDate}</td>
			</tr>
			<tr>
				<td>メールアドレス：${userInfo.address}</td>
				<td colspan="2">住所：${userInfo.homeAddress }</td>
		</tbody>
	</table>
	<div class=row>
		<div class="text-center mx-auto">
			<a href="UserDelete?user_id=${userInfo.userId}"><button type="submit" class="btn btn-danger my-5" >退会</button></a>
		</div>
		<div class="text-center mx-auto">
			<a href="UserUpdate?user_id=${userInfo.userId}"><button type="submit" class="btn btn-success my-5" >会員情報変更</button></a>
		</div>
		<div class="text-center mx-auto">
			<a href="PasswordUpdate?user_id=${userInfo.userId}"><button type="submit" class="btn btn-primary my-5" >パスワード変更</button></a>
		</div>
	</div>

</div>
	<hr>

<div class=container>
	<h3 class="text-center">最新の購入</h3>
	<c:if test="${buy != null }">
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th>購入日時</th>
					<th>合計</th>
				</tr>
				<tr>
					<td>${buy.formatDate }</td>
					<td>${buy.totalPrice }円</td>
				</tr>
			</tbody>
		</table>
		<p class="text-right">
		<a href="BuyList">購入履歴一覧</a>
		</p>
	</c:if>
	<c:if test="${buy == null }">
		<p class="text-center h4 text-danger">購入した商品はありません</p>
	</c:if>
</div>
<hr>
<div class="text-center mx-auto">
		<a href="NewItem?user_id=${userInfo.userId}"><button type="submit" class="btn btn- my-5" >商品の出品</button></a>
	</div>
<div class="row px-5">
	<div class="col-sm-6 ">
		<h3 class="text-center">受注一覧</h3>
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th>発送状況</th>
					<th>受注日時</th>
					<th>商品</th>
					<th>数量</th>
					<th>購入者</th>
					<th>住所</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a href="#" class="btn bg-info mr-1" style=color:white;>詳細</a>発送処理<input type="checkbox"  class="ml-1"></td>
					<td>ユーザ名</td>
					<td>yyyy年mm月dd日hh時mm分ss秒</td>
					<td>購入ユーザ住所</td>
				</tr>
				<tr>
					<td><a href="#" class="btn bg-info mr-1" style=color:white;>詳細</a>発送処理<input type="checkbox" class="ml-1"></td>
					<td>ユーザ名</td>
					<td>yyyy年mm月dd日hh時mm分ss秒</td>
					<td>購入ユーザ住所</td>
				</tr>
				<tr>
					<td><a href="#" class="btn bg-info mr-1" style=color:white;>詳細</a>発送処理<input type="checkbox" class="ml-1"></td>
					<td>ユーザ名</td>
					<td>yyyy年mm月dd日hh時mm分ss秒</td>
					<td>購入ユーザ住所</td>
				</tr>
				<tr>
					<td><a href="#" class="btn bg-info mr-1" style=color:white;>詳細</a>発送処理<input type="checkbox" class="ml-1"></td>
					<td>ユーザ名</td>
					<td>yyyy年mm月dd日hh時mm分ss秒</td>
					<td>購入ユーザ住所</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="col-sm-6 ">

		<h3 class="text-center">出品商品一覧</h3>
		<c:if test="${myItemList == null}">
			<p class="text-center h4 text-danger">出品中の商品はありません
		</c:if>
		<c:if test="${myItemList != null}">
			<table class="table table-bordered">
				<thead class="thead-light">
					<tr>
						<th width="150px"></th>
						<th width="150px">商品名</th>
						<th width="50px">価格</th>
						<th width="50px">残数</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${myItemList}" var="item">
						<tr>
							<td>
								<div class=row>
									<div class="col-sm-4">
										<a href="MyItemDetail?itemId=${item.itemId }" class="btn bg-info" style=color:white;>詳細</a>
									</div>
									<div class="col-sm-4">
										<a href="ItemUpdate?itemId=${item.itemId }" class="btn bg-warning" style=color:white;>更新</a>
									</div>
									<div class="col-sm-4">
										<a href="ItemDelete?itemId=${item.itemId }" class="btn bg-danger" style=color:white;>削除</a>
									</div>
								</div>
							</td>
							<td>${item.itemName }</td>
							<td>${item.itemPrice }円</td>
							<td>${item.itemNumber }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>



</body>
</html>