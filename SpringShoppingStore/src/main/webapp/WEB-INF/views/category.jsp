<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home | E-Shopper</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/font-awesome.min.css" rel="stylesheet">
<link href="resources/css/prettyPhoto.css" rel="stylesheet">
<link href="resources/css/price-range.css" rel="stylesheet">
<link href="resources/css/animate.css" rel="stylesheet">
<link href="resources/css/main.css" rel="stylesheet">
<link href="resources/css/responsive.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="resources/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="resources/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="resources/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="resources/images/ico/apple-touch-icon-57-precomposed.png">
<style>
.glyphicon {
	margin-right: 5px;
}
.thumbnail {
	margin-bottom: 20px;
	padding: 0px;
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	border-radius: 0px;
}
.item.list-group-item {
	float: none;
	width: 100%;
	background-color: #fff;
	margin-bottom: 10px;
}
.item.list-group-item:nth-of-type(odd):hover,.item.list-group-item:hover
	{
	background: #428bca;
}
.item.list-group-item .list-group-image {
	margin-right: 10px;
}
.item.list-group-item .thumbnail {
	margin-bottom: 0px;
}
.item.list-group-item .caption {
	padding: 9px 9px 0px 9px;
}
.item.list-group-item:nth-of-type(odd) {
	background: #eeeeee;
}
.item.list-group-item:before,.item.list-group-item:after {
	display: table;
	content: " ";
}
.item.list-group-item img {
	float: left;
}
.item.list-group-item:after {
	clear: both;
}
.list-group-item-text {
	margin: 0 0 11px;
}
footer {
	background-color: lightgray;
	border: solid 1px black;
	font-family: 'Times New Roman', serif;
	padding: 10px;
}
footer {
	padding: 10px;
	border-radius: 3px;
}
body {
	font-family: Segoe UI;
	font-size: 14px;
	background-color: white;
}
html,body {
	padding: 0;
	margin: 0;
}
#main {
	border: solid 1px #ccc;
	border-radius: 5px;
	color: #202020;
	margin: 20px 0;
	padding: 5px;
}
#featProds {
	padding: 2px;
	width: 300px;
}
.container {
	width: 989px;
	margin: auto;
	background-color: white;
	padding: 5px;
}
#well {
	margin-top: 20px;
	text-align: justify;
}
</style>
</head>
<!--/head-->

<body>
	<div class="container">
	<header id="header">
		<%@include file="template/header.jsp"%>
	</header>
	<!--/header-->
	<section>
		<div class="container">
			<div class="row">
				<div class="padding-right">
					<div class="features_items">
						<c:choose>
							<c:when test="${empty subCatProds}">
								<c:forEach var="catProds" items="${catProds}">
									<c:url var="url" value="product">
										<c:param name="productId" value="${catProds.productId}" />
									</c:url>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img src="resources/images/home/${catProds.productId}.jpg"
														alt="" /> <a href="${url}">
														<h2>
															<c:out value="${catProds.name}" />
														</h2>
													</a>
													<p>
														&euro;
														<c:out value="${catProds.price}" />
													</p>
													<c:url var="add" value="/addProduct">
														<c:param name="productId" value="${catProds.productId}" />
													</c:url>
													<a href="${url}" class="btn btn-default add-to-cart"><i
														class="fa fa-shopping-cart"></i>Add to cart</a>
												</div>
												<div class="product-overlay">
													<div class="overlay-content">
														<a href="${url}">
															<h2>
																<c:out value="${catProds.name}" />
															</h2>
														</a>
														<p>
															&euro;
															<c:out value="${catProds.price}" />
														</p>
														<c:url var="add" value="/addProduct">
															<c:param name="productId" value="${catProds.productId}" />
														</c:url>
														<a href="${url}" class="btn btn-default add-to-cart"><i
															class="fa fa-shopping-cart"></i>Add to cart</a>
													</div>
												</div>
											</div>
											<div class="choose">
												<ul class="nav nav-pills nav-justified">
													<li><a href="#"><i class="fa fa-plus-square"></i>Add
															to wishlist</a></li>
													<li><a href="#"><i class="fa fa-plus-square"></i>Add
															to compare</a></li>
												</ul>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:if test="${not empty subCatProds}"></c:if>
								<c:forEach var="subCatProds" items="${subCatProds}">
									<c:url var="url" value="product">
										<c:param name="productId" value="${subCatProds.productId}" />
									</c:url>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<img
														src="resources/images/home/${subCatProds.productId}.jpg"
														alt="" /> <a href="${url}">
														<h2>
															<c:out value="${subCatProds.name}" />
														</h2>
													</a>
													<p>
														&euro;
														<c:out value="${subCatProds.price}" />
													</p>
													<c:url var="add" value="/addProduct">
														<c:param name="productId" value="${subCatProds.productId}" />
													</c:url>
													<a href="${url}" class="btn btn-default add-to-cart"><i
														class="fa fa-shopping-cart"></i>Add to cart</a>
												</div>
												<div class="product-overlay">
													<div class="overlay-content">
														<a href="${url}">
															<h2>
																<c:out value="${subCatProds.name}" />
															</h2>
														</a>
														<p>
															&euro;
															<c:out value="${subCatProds.price}" />
														</p>
														<c:url var="add" value="/addProduct">
															<c:param name="productId"
																value="${subCatProds.productId}" />
														</c:url>
														<a href="${url}" class="btn btn-default add-to-cart"><i
															class="fa fa-shopping-cart"></i>Add to cart</a>
													</div>
												</div>
											</div>
											<div class="choose">
												<ul class="nav nav-pills nav-justified">
													<li><a href="#"><i class="fa fa-plus-square"></i>Add
															to wishlist</a></li>
													<li><a href="#"><i class="fa fa-plus-square"></i>Add
															to compare</a></li>
												</ul>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</section>

	<footer id="footer">
		<!--Footer-->

		<div class="footer-bottom">
			<div class="container_i">
				<div class="row">
					<p class="pull-left">Copyright © 2015 HACKATHON. All rights
						reserved.</p>
					
				</div>
			</div>
		</div>

	</footer>
	<!--/Footer-->



	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.scrollUp.min.js"></script>
	<script src="resources/js/price-range.js"></script>
	<script src="resources/js/jquery.prettyPhoto.js"></script>
	<script src="resources/js/main.js"></script>
	</div>
</body>
</html>