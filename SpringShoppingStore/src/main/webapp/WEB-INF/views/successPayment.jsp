<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Payment status</title>
<link rel="stylesheet" href="resources/css/bootstrap.css" />
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/jquery-1.8.0.js"></script>
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
<body>
	<div class="container">
		<header>
			<%@include file="template/header.jsp"%>
		</header>
		<section>
			<div class="container">
				<div class="row">
					<div class="col-sm-3">	
					</div>
					<div class="col-sm-6">
						<p>Thanks for placing your order!! Your order will be dispatched soon!!</p>
					</div>
					<div class="col-sm-3">	
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
</html>