<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<!--/header-->
	<section>
		<div class="container_i">
			<div class="row">
				<div class="padding-right">
					<div class="features_items ">
						<c:choose>
							<c:when test="${empty subCatProds}">
								<c:forEach var="catProds" items="${catProds}">
									<c:url var="url" value="sellHistory">
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
														class="fa fa-shopping-cart"></i>Sell history</a>
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
															class="fa fa-shopping-cart"></i>Sell history</a>
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
									<div class="col-sm-3">
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
														class="fa fa-shopping-cart"></i>Sell history</a>
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
															class="fa fa-shopping-cart"></i>Sell history</a>
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

</body>
</html>