<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="Materialize/style.css" type="text/css">
<meta charset="UTF-8">
<title>エラーページ</title>
</head>
<body>
<jsp:include page="/baselayout/header.jsp" />
<h3 class="text-center mgn-top">システムエラーが発生しました。</h3>
<h4 class=text-center>${errorMsg }</h4>

<div class="text-center mx-auto">
	<a href="Top"><button type="submit" class="btn btn-primary my-5" >TOPページへ</button></a>
</div>
</body>
</html>