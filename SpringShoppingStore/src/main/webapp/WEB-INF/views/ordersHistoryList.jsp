<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<body>
	<div class="col-md-12">
		<div class="row">
			<div class="table-responsive">
				<h1>Orders Information</h1>
				<table class="table table-bordered table-hover table-condensed">
					<thead>
						<th>Order Id</th>
						<th>Quantity</th>
						<th>Created Date</th>
					</thead>
					<tbody>
						<c:forEach var="orders" items="${orderList}">
							<tr>
								<c:url var="orderDetail" value="orderDetail">
									<c:param name="id" value="${orders.orderItemId}" />
								</c:url>
								<td><a href="${orderDetail}"><c:out
											value="${orders.orderItemId}" /></a></td>
								<td><c:out value="${orders.quantity}" /></td>
								<td><c:out value="${orders.createdDate}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>