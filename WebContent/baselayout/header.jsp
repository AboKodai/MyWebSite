<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<nav>
	<div class=" row bgc-main shadow-sm" >
		<div class="col-sm-4"></div>
		<div class="col-sm-4 text-center h3 ">
			<a class="text-dark" href="Top">食料品ECサイト</a>
		</div>
		<c:if test="${userInfo == null }">
			<div class="col-sm-4 text-right pr-5">
				<a class="text-dark p-4" href="Cart">カート</a>
				<a class="btn btn-outline-danger text-right " href="Login">ログイン</a>
			</div>
		</c:if>
		<c:if test="${userInfo != null }">
			<div class="col-sm-4 text-right pr-5">
				<a href="MyPage?userId=${userInfo.userId }">${userInfo.userName}　さん</a>
				<a class="text-dark p-4" href="Cart">カート</a>
				<a class="btn btn-outline-danger text-right " href="Logout">ログアウト</a>
			</div>
		</c:if>
	</div>
</nav>