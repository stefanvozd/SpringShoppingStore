<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.css" />
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/clipboard.js"></script>
<script src="resources/js/clipboard-action.js"></script>
<script src="resources/js/jquery-1.8.0.js"></script>
<style>
.navbar-header {
	padding: 2px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
	<div>
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-slide-dropdown">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<c:url var="home" value="home" />
			<div id="logo">
				<a href="${home}"><img src="resources/logo.png" /></a>
			</div>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-slide-dropdown">
			<ul class="nav navbar-nav">
				<c:forEach var="categories" items="${categoryMap}">
					<c:set var="category" value="${categories.key}" />
					<c:url var="url" value="cateogry">
						<c:param name="category" value="${category.categoryName}" />
					</c:url>

					<li class="dropdown"><a href="${url}" class="dropdown-toggle"
						data-toggle="dropdown" role="button"><c:out
								value="${category.categoryName}" /></a>
						<ul class="dropdown-menu" role="menu">
							<c:forEach var="subCategory" items="${categories.value}">
								<li class="divider"></li>
								<c:url var="subCaturl" value="subcateogry">
									<c:param name="subcategory"
										value="${subCategory.subCategoryName}" />
								</c:url>
								<li><a tabindex="-1" href="${subCaturl}"><c:out
											value="${subCategory.subCategoryName}" /></a></li>
							</c:forEach>
						</ul></li>
				</c:forEach>
			</ul>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a data-title='register' href="#" data-toggle='modal' data-target='#register' data-whatever='@mdo' style='min-width: 123px;'><img src="http://www.mbills.si/favicon.ico"/>Generate HTML</a>
				</li>
				<li class="dropdown"><a href="/shopping/createnewproduct" class="dropdown-toggle"
						data-toggle="dropdown" role="button">
						<img src="resources/images/home/sell.png" width="20px"/>
						<c:out	value="Sell" />
						</a>
						</li>
				
				<li class="dropdown"><a href="#"><span id="cartIcon"><i
						class="icon-shopping-cart icon-red"></i>
						 <c:set var="cartItems" value="${cart.numberOfItems}" /> <span
						class="headerCartItemsCount"> <c:choose>
								<c:when test="${empty cartItems}">0
							</c:when>
								<c:otherwise>
									<c:out value="${cartItems}" />
								</c:otherwise>
							</c:choose>
					</span> <span class="headerCartItemsCountWord"><c:out
								value="${cartItems==1?'item':'items'}" /></span></span> <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<c:url var="cart1" value="/cart" />
						<li><a tabindex="-1" href="${cart1}">View Cart</a></li>
					</ul>
				</li>
				<c:choose>
					<c:when test="${not empty customer}">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Welcome, ${customer.userName} <span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="account">Account</a></li>
								<li><a href="orderHistory">Orders</a></li>
								<li><a href="myProductList">My products list</a></li>
								<li><a href="wishlist">Wishlist</a></li>
								<li><a href="logout">Logout</a></li>
							</ul></li>
					</c:when>
					<c:otherwise>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown">
								<a style="white-space: nowrap; margin-right: 5px;" href="login">Sign In/Register</a>
							</li>
						</ul>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="register" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<span class="modal-title custom_align" id="Heading" style="font-size: 20px;font-weight: bold;">Generate HTML code</span>
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
								</div> 
								<div style="text-align: justify; text-justify: inter-word;" class="modal-body">
									<p>Welcome to <b>Hal mBill</b> payments!<br>This is page
									where you can find generated HTML code for your web site, if you want to implement
									payments via mBills.<br><br>
									You should copy the form text below to your web site. 
									In the input tag with attribute <b>type="phoneNumber"</b> you should have
									predefined value of you mobile number. In the input tag with attribute <b>type="amount"</b> you should implement total amount
									of products calculated by user. Change amount from fixed 199.17 to your.
									<br><br>
									Click on <b>mBill</b> button should redirect user to our web site, where he will pay for it.</p>
								</div>								
								<div id="htmlCode" style="margin-right: 15px;margin-left: 15px;text-align: justify;text-justify: inter-word;" class="form-group">
									<pre><xmp><form action="http://localhost:8080/shopping/paymentGateway" method="post">
	<input type="text" name="amount" value="199.17" readonly>
	<input type="hidden" name="phoneNumber" value="">
	<input type="image" src="http://localhost:8080/shopping/resources/images/mbills.png" alt="Submit Form">
</form></xmp></pre>
								</div>
							</div>
						</div>
					</div>
</body>
</html>