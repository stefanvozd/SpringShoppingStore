<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/css/bootstrap.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/jquery-1.8.0.js"></script>
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
</head>

<style>
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
}

html,body {
	padding: 0;
	margin: 0;
}

#main {
	border: solid 1px #ccc;
	border-radius: 5px;
	margin: 20px 0;
	padding: 5px;
}
</style>
<body>
	<div class="container">
		<div class="container">
			<header> <%@include file="template/header.jsp"%>
			</header>
			<section id="main">

			<h3>Thanks for placing your order!! Your order will be
				dispatched soon!!</h3>
			<a href=""><c:out value="Order Number: ${orderDetails.orderId}" /></a>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Product</th>
						<th>Price</th>
						<th>Order Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${prodList}">
						<tr>
							<td class="col-sm-8 col-md-6">
								<div class="media">
									<a class="thumbnail pull-left" href="#"> <img
										class="media-object"
										src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
										style="width: 72px; height: 72px;">
									</a>
									<div class="media-body">
										<h4 class="media-heading">
											<c:out value="${product.name}" />
										</h4>
										<h5 class="media-heading">
											by
											<c:out value="${product.manufacturer}   " />
										</h5>
										<span>Status: </span><span class="text-success"><strong>In
												Stock</strong></span>
									</div>
								</div>
							</td>
							<td><strong><c:out value="Rs.${product.price}" /></strong></td>
							<td><strong><c:out
										value="${orderDetails.orderStatus}" /></strong></td>

						</tr>
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td><strong><c:out
									value="Total:${orderDetails.orderTotal}" /></strong></td>
					</tr>
				</tbody>
			</table>
		</div>
		</section>
		<footer><%@include file="template/footer.jsp"%></footer>
		<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.scrollUp.min.js"></script>
	<script src="resources/js/price-range.js"></script>
	<script src="resources/js/jquery.prettyPhoto.js"></script>
	<script src="resources/js/main.js"></script>
	</div>
</body>
</html>
<script>
	$(document).ready(
			function() {
				$(".dropdown").hover(
						function() {
							$('.dropdown-menu', this).not('.in .dropdown-menu')
									.stop(true, true).slideDown("fast");
							$(this).toggleClass('open');
						},
						function() {
							$('.dropdown-menu', this).not('.in .dropdown-menu')
									.stop(true, true).slideUp("fast");
							$(this).toggleClass('open');
						});
			});
</script>