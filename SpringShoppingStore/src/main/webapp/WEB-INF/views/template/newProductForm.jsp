<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-md-6">
	<h2 id="addressTitle">Add new Product Form</h2>
	<form:form commandName="productFormm" id="productFormm"
		class="form-horizontal" action="createnewproduct" method="POST">
		
		<div class="form-group">
			<label for="Name"> Product name </label> <input id="Name"
				name="Name" type="text" class="form-control"
				placeholder="Product Name" />
		</div>
		
		<div class="form-group">
			<label for="Manufacturer"> Manufacturer </label> <input id="Manufacturer"
				name="Manufacturer" type="text" class="form-control"
				placeholder="Manufacturer name" />
		</div>
		
		<div class="form-group">
			<label for="address1"> Category </label> <select id="Category_Id" name="Category_Id" class="form-control">
				<option value="volvo">Volvo</option>
				<option value="saab">Saab</option>
				<option value="mercedes">Mercedes</option>
				<option value="audi">Audi</option>
			</select>
		</div>
		<div class="form-group">
			<label for="fullname"> Subcategory </label> <select id="SubCategory_Id" name="SubCategory_Id" class="form-control">
				<option value="volvo">Volvo</option>
				<option value="saab">Saab</option>
				<option value="mercedes">Mercedes</option>
				<option value="audi">Audi</option>
			</select>
		</div>
		
		<div class="form-group">
			<label for="city"> Product you are selling  </label> 
			<input type="file" class="form-control" name="productzip" accept="application/zip">
		</div>
		
		<div class="form-group">
			<label for="city"> Product's image  </label> 
			<input type="file" class="form-control" name="image" accept="image/jpg">
		</div>
		
		<div class="form-group">
			<label for="city"> Description </label>
			<textarea id="Description" class="form-control" name="Description" rows="4"
				cols="50" placeholder="Product's Description">
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