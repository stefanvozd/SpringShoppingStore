<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-md-6">
	<h2 id="addressTitle">Add new Product Form</h2>
	<form:form name="Product" commandName="Product" id="Product"
		class="form-horizontal" action="createnewproduct" method="POST"
		enctype="multipart/form-data">

		<div class="form-group">
			<label for="Name"> Product name </label> <input id="Name" name="Name"
				type="text" class="form-control" placeholder="Product Name" />
		</div>

		<div class="form-group">
			<label for="Manufacturer"> Manufacturer </label> <input
				id="Manufacturer" name="Manufacturer" type="text"
				class="form-control" placeholder="Manufacturer name" />
		</div>



		<div class="form-group">
			<label for="address1"> Category </label> <select id="Category_Id"
				name="Category_Id" class="form-control">
				<c:forEach items="${categoryList}" var="category">
					<option value="${category.getcategoryId()}">${category.getCategoryName()}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			<label for="fullname"> Subcategory </label> <select
				id="SubCategory_Id" name="SubCategory_Id" class="form-control">
					<option>Select...</option>
				<c:forEach items="${subcategoryList}" var="subcategory">
					<option id="${subcategory.getCategoryId()}" value="${subcategory.getSubCategoryId()}">${subcategory.getSubCategoryName()}</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<label for="city"> Product you are selling </label> <input
				type="file" class="form-control" name="productzip"
				accept="application/zip">
		</div>

		<div class="form-group">
			<label for="city"> Product's image </label> <input type="file"
				class="form-control" name="image" accept="image/jpg">
		</div>

		<div class="form-group">
			<label for="city"> Description </label>
			<textarea id="Description" class="form-control" name="Description"
				rows="4" cols="50">
			</textarea>
		</div>

		<div class="form-group">
			<label for="Price"> Price </label> <input id="Price" name="Price"
				type="number" class="form-control" placeholder="1000" />
		</div>

		<div class="form-group">
			<input type="submit" class="btn btn-large btn-primary"
				value="Publish this product" />
		</div>
	</form:form>
</div>

