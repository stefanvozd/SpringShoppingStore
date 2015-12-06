 function validateVatNumber() {
 	var vatRegNum = $("#VatRegNum").val();
 	var selectedCountry = $("#vat_countries").val();
 	
    var result;
 	
 	if (vatRegNum == '') result = false;

 	vatRegNum = vatRegNum.toUpperCase();

 	var regEx = / / ;
 	var useRegEx = false;

 	switch (selectedCountry) {
 	case "FR":
 		regEx = /^(FR-?)?[0-9A-Z]{2}\ ?[0-9]{9}$/ ;
 		useRegEx = true;
 		break;
 	case "NL":
 		regEx = /^(NL-?)?[0-9]{9}B[0-9]{2}$/ ;
 		useRegEx = true;
 		break;
 	case "DE":
 	case "EE":
 	case "EL":
 		regEx = /^((EE|EL|DE|PT)-?)?[0-9]{9}$/ ;
 		useRegEx = true;
 		break;
 	case "IT":
 	case "LV":
 		regEx = /^((IT|LV)-?)?[0-9]{11}$/ ;
 		useRegEx = true;
 		break;
 	case "SE":
 		regEx = /^(SE-?)?[0-9]{12}$/ ;
 		useRegEx = true;
 		break;
 	case "ES":
 		regEx = /^(ES-?)?([0-9A-Z][0-9]{7}[A-Z])|([A-Z][0-9]{7}[0-9A-Z])$/ ;
 		useRegEx = true;
 		break;
 	case "BE":
 		regEx = /^(BE-?)?0?[0-9]{9}$/ ;
 		useRegEx = true;
 		break;
 	case "SK":
 	case "PL":
 		regEx = /^((PL|SK)-?)?[0-9]{10}$/ ;
 		useRegEx = true;
 		break;
 	case "HU":
 	case "FI":
 	case "LU":
 	case "MT":
 	case "SI":
 		regEx = /^((FI|HU|LU|MT|SI)-?)?[0-9]{8}$/ ;
 		useRegEx = true;
 		break;
 	case "CY":
 		regEx = /^(CY-?)?[0-9]{8}[A-Z]$/ ;
 		useRegEx = true;
 		break;
 	case "CZ":
 		regEx = /^(CZ-?)?[0-9]{8,10}$/ ;
 		useRegEx = true;
 		break;
 	case "DK":
 		regEx = /^(DK-?)?([0-9]{2}\ ?){3}[0-9]{2}$/ ;
 		useRegEx = true;
 		break;
 	case "IE":
 		regEx = /^(IE-?)?[0-9][0-9A-Z\+\*][0-9]{5}[A-Z]$/ ;
 		useRegEx = true;
 		break;
 	case "GB":
 	case "UK":
 		regEx = /^(GB-?)?([1-9][0-9]{2}\ ?[0-9]{4}\ ?[0-9]{2})|([1-9][0-9]{2}\ ?[0-9]{4}\ ?[0-9]{2}\ ?[0-9]{3})|((GD|HA)[0-9]{3})$/ ;
 		useRegEx = true;
 		break;
 	case "AT":
 		regEx = /^(AT)?U[0-9]{8}$/;
 		useRegEx = true;
 		break;
 	default:
 		useRegEx = false;
 	}

 	if (useRegEx) {
 		result = regEx.test(vatRegNum);
 	}
 	else {
 		result = vatRegNum.length > 5;
 	}
 	
	if (!result) {
	    $("#vat-entry-alert").css({"display": "block"});	
    }
 	else {
		$("#vat-entry-alert").css({"display": "none"});
	}
		
 }
 
 function validatePostCode(postCode) {
 	//alert(postCode);
 	if (postCode == '') return false;

 	postCode = postCode.toUpperCase();
 	var country = $("#b_country").val();

 	var regEx;
 	var useRegEx = false;
 	
 	switch (country) {
 	case "GB":
 		regEx = /^(GIR ?0AA|(?:[A-PR-UWYZ](?:\d|\d{2}|[A-HK-Y]\d|[A-HK-Y]\d\d|\d[A-HJKSTUW]|[A-HK-Y]\d[ABEHMNPRV-Y])) ?\d[ABD-HJLNP-UW-Z]{2})$/;
 		useRegEx = true;
 		break;
 	case "FR":
 		regEx = /^(F-)?((2[A|B])|[0-9]{2})[0-9]{3}$/ ;
 		useRegEx = true;
 		break;
 	default:
 		useRegEx = false;
 		break;
 	}

 	if (useRegEx) {
 		return regEx.test(postCode);
 	}
 	else {
 		return postCode.length > 3;
 	}

 	
 }
 
 function LoadCountries(dropDown, zoneGroupId) {
    $(dropDown).empty();
	$.getJSON('/deliveryhelper.aspx', { getDeliveryCountries: "1", zoneGroupId: zoneGroupId }, function(json) {
	var countries = "";
	if (json) {
		$.each(json, function(i, item) {
			countries += "<option ";
			if (item.IsCurrentRegion)countries += "selected='selected' ";
			countries += "value=" + item.Abbreviation + ">" + item.Country + "</option>";
		});
		$(dropDown).html(countries);
	}});
 }