/* Expansys/8a/core.js */
$(document).ready(function(){
    
  //search
  if ( $("#search #q").length > 0 ) {

      //search auto complete
    $("#search #q").autocomplete("/iaAjax.ashx?action=search&", {
        delay: 50,
        minChars: 3,
        cacheLength: 1,
        matchSubset: 0, //disable local cache
        width: 200,
        max: 10,
        selectFirst: false,
        scroll: false
    });
   
    $("#search #q").result( //auto submit form on selection
       function () {
          $("#search").submit();
       }
    );
  }
  
  //*************************
  //Search submit
  $("#search").submit( 
     function () {
        search_action = $(this).attr("action"); 
        search_query = $("#search #q").val(); //get search query ...
        search_query = jQuery.trim(search_query); // ... and trim whitespace      
        search_url = search_action + "?search=" + search_query; //build search path
        
        if (search_query == "") { // check for empty queries
            $("#search #query").val("");
            $("#search #query").focus();
          } else {
            window.location = search_url;
          }
        return false; //block default form submission
     }
  );
  
  //Footer newsletter signup
  $(".nl_signup_button,#signup_wrap .close").click( 
     function () {
        $("#signup_wrap").fadeToggle();
        $(".nl_signup_button").toggleClass('active');
        return false; //block default form submission
     }
  );
  
  //basic validation
  $("#footerSignupForm").simpleValidate();

  //lazy load images 
  if ($("img.lazy").length > 0 ) {
   $("img.lazy").lazyload({ 
        effect : "fadeIn"
    });
  }
   
  //nav menus
  
  //v1 -------------
  var config = {
    sensitivity: 5,
    interval: 200, 
    over: showMenu, 
    timeout: 350, 
    out: hideMenu
   };

  if ( $("#nav").length > 0 ) {
    $("#nav > ul > li").hoverIntent(config);
    
    //hide menu
    $("#nav .close,#nav > ul > li > a").click(
      function () {
        $("#nav .menu").hide();
      }
    );
  }
  
  //v2 -------------
   
  var config_v2 = {
    sensitivity: 5,
    interval: 100, 
    over: showMenu_v2, 
    timeout: 250, 
    out: hideMenu_v2
  };

  if ( $("#nav-v2").length > 0 ) {  
    $("#nav-v2 .nitem").hoverIntent(config_v2);
  }

  //social media event counter
  $("#topnav .icon").click(function () {
    var _class = $(this).attr('class').replace(" icon","");
     if(_class.length > 0){
       _gaq.push(['_trackEvent', 'Social Icon', 'Header', _class]);
     } 
  });
  
  $("#footer .icon").click(function () {
    var _class = $(this).attr('class').replace(" icon","");
     if(_class.length > 0){
       _gaq.push(['_trackEvent', 'Social Icon', 'Footer', _class]);
     } 
  });
  
	//JS product snippet	  
	if ($('.js-live-sku').length > 0) {
	
		var _skus = new Array(); 

		//loop through the js-live-sku and build an array of SKUs
		$('.js-live-sku').each( function( index, element ){
			_skus.push ($(this).data('sku'));
		});

		//send SKUs to ajax request
		getProductData(_skus.toString());	
	}

	function getProductData(stockcodes) {
		$.post('/snippetHelper.aspx', { s: stockcodes}, function(data) { 
				if (data == null) return;
				setProductInfo(data);		
			}, 'json');
	}

	function setProductInfo(data) {
		//populate product info
		for (var i = 0; i < data.length; ++i) {

		var _sku = data[i]['stockcode'];
		var _title = data[i]['title']; 
		var _version = data[i]['version']; 
		var _fqn = data[i]['name']; 
		var _stock = data[i]['stocklevel']; 
		var _price = data[i]['total_price_formatted']; 
		var _available = data[i]['available']; 
		var _availability_text = data[i]['availability_text']; 
		var _url = data[i]['url'];
		var _supPrice;
		
		//check we have a decimal
		if ( _price.indexOf(".") > -1 ) {
		  _supPrice = _price.replace(".","<span class=\"decimal\">.");
		  _supPrice = _supPrice  + "</span>";	
		} else {
		  _supPrice = _price;
		}
		

		//write data
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__sku").text(_sku);
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__title").text(_title);
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__version").text(_version);
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__fqn").text(_fqn);
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__price").html(_price); //write as HTML
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__availability_text").text(_availability_text);
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__available").text(_available);
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__stock").text(_stock);
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__url").attr("href", _url); //set link attribute 
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__supprice").html(_supPrice);//price with wrapped decimal for styling	
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__autoimg").attr("src", "//www.expansys.com/i/l/l" + _sku + ".jpg"); //broken
		
		//auto images (el hack)
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__img[data-size='t']").replaceWith("<img src=\"//www.expansys.com/i/t/t" + _sku + ".jpg\" alt=\"" + _title + "\">");
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__img[data-size='f']").replaceWith("<img src=\"//www.expansys.com/i/f/f" + _sku + ".jpg\" alt=\"" + _title + "\">");
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__img[data-size='s']").replaceWith("<img src=\"//www.expansys.com/i/s/s" + _sku + ".jpg\" alt=\"" + _title + "\">");
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__img[data-size='n']").replaceWith("<img src=\"//www.expansys.com/i/n/n" + _sku + ".jpg\" alt=\"" + _title + "\">");
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__img[data-size='l']").replaceWith("<img src=\"//www.expansys.com/i/l/l" + _sku + ".jpg\" alt=\"" + _title + "\">");
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__img[data-size='g']").replaceWith("<img src=\"//www.expansys.com/i/g/g" + _sku + ".jpg\" alt=\"" + _title + "\">");	
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__img[data-size='h']").replaceWith("<img src=\"//www.expansys.com/i/h/h" + _sku + ".jpg\" alt=\"" + _title + "\">");	
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__img[data-size='p']").replaceWith("<img src=\"//www.expansys.com/i/p/p" + _sku + ".jpg\" alt=\"" + _title + "\">");	
		$(".js-live-sku[data-sku='" + _sku + "'] .js-live-sku__img[data-size='b']").replaceWith("<img src=\"//www.expansys.com/i/b/b" + _sku + ".jpg\" alt=\"" + _title + "\">");	
		
		}
	}  
	
	//end JS product snippets

	//AU GST help lightbox
	if ( $(".gst_help").length > 0 && parent.$.fancybox) { // check gst help exists and that we have Fancybox
	$(".gst_help").fancybox({
        'transitionIn'		: 'fade',
        'transitionOut'		: 'fade',
        'overlayColor'      : '#000',
        'hideOnContentClick' : true
    }); 
	}
});

// functions
function showMenu(){
  $(".menu", this).show();  
}  
  
function hideMenu() {
  $(".menu", this).hide(); 
}

function showMenu_v2(){
  if ($(".menu", this).length > 0) {
	  $(".menu", this).show(); 
	  $(".menu", this).append('<span class=\"marrow\"></span>'); //write span for menu arrow
	  var _width = 980; //max page width
	  var _label_pos = $(this).position().left; //current nav label position 
	  var _menu_pos = $(".menu", this).position().left; //current menu position 
	  var _menu_width = $(".menu", this).width();//menu width
	  var _free_space = _width - _label_pos - _menu_width; 
	  if (_menu_width == 482) { 
		var _nudge = _label_pos - 502;
	  } else { //233
		var _nudge = _label_pos - 752;
	  }
	  //console.log(_nudge + "/" + _label_pos + "/" + _menu_width);
	  if (_free_space < 0) {
		$(".menu", this).css({"margin-left":_free_space+ "px"}); //use negative "free" space to keep menu right aligned
		$(".marrow", this).css("left", _nudge); //position arrow according to label position 
	  }
	}	  
}  
  
function hideMenu_v2() {
  $(".menu", this).hide(); 
}

function msgBar(message,style,duration) {
  if(typeof(style)==='undefined') style = "msgbar note";
  if(typeof(duration)==='undefined') duration = 5000;
  $(".msgbar").hide(); //kill any previous message bar
  $("<div />", {'class': style, html: message }).hide().prependTo("body").fadeIn(500).delay(duration).fadeOut(1000, function() { $(this).remove(); });
}

function validateCreditCard(ccNumb) {  

// v2.0
var valid = "0123456789"  // Valid digits in a credit card number
var len = ccNumb.length;  // The length of the submitted cc number
var iCCN = parseInt(ccNumb);  // integer of ccNumb
var sCCN = ccNumb.toString();  // string of ccNumb
sCCN = sCCN.replace (/^s+|s+$/g,'');  // strip spaces
var iTotal = 0;  // integer total set at zero
var bNum = true;  // by default assume it is a number
var bResult = false;  // by default assume it is NOT a valid cc
var temp;  // temp variable for parsing string
var calc;  // used for calculation of each digit

// Determine if the ccNumb is in fact all numbers
for (var j=0; j<len; j++) {
  temp = "" + sCCN.substring(j, j+1);
  if (valid.indexOf(temp) == "-1"){bNum = false;}
}

// if it is NOT a number, you can either alert to the fact, or just pass a failure
if(!bNum){
  /*alert("Not a Number");*/bResult = false;
}

// Determine if it is the proper length 
if((len == 0)&&(bResult)){  // nothing, field is blank AND passed above # check
  bResult = false;
} else{  // ccNumb is a number and the proper length - let's see if it is a valid card number
  if(len >= 15){  // 15 or 16 for Amex or V/MC
    for(var i=len;i>0;i--){  // LOOP throught the digits of the card
      calc = parseInt(iCCN) % 10;  // right most digit
      calc = parseInt(calc);  // assure it is an integer
      iTotal += calc;  // running total of the card number as we loop - Do Nothing to first digit
      i--;  // decrement the count - move to the next digit in the card
      iCCN = iCCN / 10;                               // subtracts right most digit from ccNumb
      calc = parseInt(iCCN) % 10 ;    // NEXT right most digit
      calc = calc *2;                                 // multiply the digit by two
      // Instead of some screwy method of converting 16 to a string and then parsing 1 and 6 and then adding them to make 7,
      // I use a simple switch statement to change the value of calc2 to 7 if 16 is the multiple.
      switch(calc){
        case 10: calc = 1; break;       //5*2=10 & 1+0 = 1
        case 12: calc = 3; break;       //6*2=12 & 1+2 = 3
        case 14: calc = 5; break;       //7*2=14 & 1+4 = 5
        case 16: calc = 7; break;       //8*2=16 & 1+6 = 7
        case 18: calc = 9; break;       //9*2=18 & 1+8 = 9
        default: calc = calc;           //4*2= 8 &   8 = 8  -same for all lower numbers
      }                                               
    iCCN = iCCN / 10;  // subtracts right most digit from ccNum
    iTotal += calc;  // running total of the card number as we loop
  }  // END OF LOOP
  if ((iTotal%10)==0){  // check to see if the sum Mod 10 is zero
    bResult = true;  // This IS (or could be) a valid credit card number.
  } else {
    bResult = false;  // This could NOT be a valid credit card number
    }
  }
}
// change alert to on-page display or other indication as needed.
//if(bResult) {
//  alert("This IS a valid Credit Card Number!");
//}
//if(!bResult){
//  alert("This is NOT a valid Credit Card Number!");
//}
  return bResult; // Return the results

}

// plugins ...
/* hoverIntent r6 // http://cherne.net/brian/resources/jquery.hoverIntent.html */
(function($){$.fn.hoverIntent=function(f,g){var cfg={sensitivity:32,interval:200,timeout:30};cfg=$.extend(cfg,g?{over:f,out:g}:f);var cX,cY,pX,pY;var track=function(ev){cX=ev.pageX;cY=ev.pageY};var compare=function(ev,ob){ob.hoverIntent_t=clearTimeout(ob.hoverIntent_t);if((Math.abs(pX-cX)+Math.abs(pY-cY))<cfg.sensitivity){$(ob).unbind("mousemove",track);ob.hoverIntent_s=1;return cfg.over.apply(ob,[ev])}else{pX=cX;pY=cY;ob.hoverIntent_t=setTimeout(function(){compare(ev,ob)},cfg.interval)}};var delay=function(ev,ob){ob.hoverIntent_t=clearTimeout(ob.hoverIntent_t);ob.hoverIntent_s=0;return cfg.out.apply(ob,[ev])};var handleHover=function(e){var ev=jQuery.extend({},e);var ob=this;if(ob.hoverIntent_t){ob.hoverIntent_t=clearTimeout(ob.hoverIntent_t)}if(e.type=="mouseenter"){pX=ev.pageX;pY=ev.pageY;$(ob).bind("mousemove",track);if(ob.hoverIntent_s!=1){ob.hoverIntent_t=setTimeout(function(){compare(ev,ob)},cfg.interval)}}else{$(ob).unbind("mousemove",track);if(ob.hoverIntent_s==1){ob.hoverIntent_t=setTimeout(function(){delay(ev,ob)},cfg.timeout)}}};return this.bind('mouseenter',handleHover).bind('mouseleave',handleHover)}})(jQuery);
/* Autocomplete 1.2.1 // https://github.com/agarzola/jQueryAutocompletePlugin */
(function($){$.fn.extend({autocomplete:function(urlOrData,options){var isUrl=typeof urlOrData=="string";options=$.extend({},$.Autocompleter.defaults,{url:isUrl?urlOrData:null,data:isUrl?null:urlOrData,delay:isUrl?$.Autocompleter.defaults.delay:10,max:options&&!options.scroll?10:150},options);options.highlight=options.highlight||function(value){return value};options.formatMatch=options.formatMatch||options.formatItem;return this.each(function(){new $.Autocompleter(this,options)})},result:function(handler){return this.bind("result",handler)},search:function(handler){return this.trigger("search",[handler])},flushCache:function(){return this.trigger("flushCache")},setOptions:function(options){return this.trigger("setOptions",[options])},unautocomplete:function(){return this.trigger("unautocomplete")}});$.Autocompleter=function(input,options){var KEY={UP:38,DOWN:40,DEL:46,TAB:9,RETURN:13,ESC:27,COMMA:188,PAGEUP:33,PAGEDOWN:34,BACKSPACE:8};var $input=$(input).attr("autocomplete","off").addClass(options.inputClass);var timeout;var previousValue="";var cache=$.Autocompleter.Cache(options);var hasFocus=0;var lastKeyPressCode;var config={mouseDownOnSelect:false};var select=$.Autocompleter.Select(options,input,selectCurrent,config);var blockSubmit;$.browser.opera&&$(input.form).bind("submit.autocomplete",function(){if(blockSubmit){blockSubmit=false;return false}});$input.bind(($.browser.opera?"keypress":"keydown")+".autocomplete",function(event){hasFocus=1;lastKeyPressCode=event.keyCode;switch(event.keyCode){case KEY.UP:if(select.visible()){event.preventDefault();select.prev()}else{onChange(0,true)}break;case KEY.DOWN:if(select.visible()){event.preventDefault();select.next()}else{onChange(0,true)}break;case KEY.PAGEUP:if(select.visible()){event.preventDefault();select.pageUp()}else{onChange(0,true)}break;case KEY.PAGEDOWN:if(select.visible()){event.preventDefault();select.pageDown()}else{onChange(0,true)}break;case options.multiple&&$.trim(options.multipleSeparator)==","&&KEY.COMMA:case KEY.TAB:case KEY.RETURN:if(selectCurrent()){event.preventDefault();blockSubmit=true;return false}break;case KEY.ESC:select.hide();break;default:clearTimeout(timeout);timeout=setTimeout(onChange,options.delay);break}}).focus(function(){hasFocus++}).blur(function(){hasFocus=0;if(!config.mouseDownOnSelect){hideResults()}}).click(function(){if(options.clickFire){if(!select.visible()){onChange(0,true)}}else{if(hasFocus++>1&&!select.visible()){onChange(0,true)}}}).bind("search",function(){var fn=(arguments.length>1)?arguments[1]:null;function findValueCallback(q,data){var result;if(data&&data.length){for(var i=0;i<data.length;i++){if(data[i].result.toLowerCase()==q.toLowerCase()){result=data[i];break}}}if(typeof fn=="function")fn(result);else $input.trigger("result",result&&[result.data,result.value])}$.each(trimWords($input.val()),function(i,value){request(value,findValueCallback,findValueCallback)})}).bind("flushCache",function(){cache.flush()}).bind("setOptions",function(){$.extend(true,options,arguments[1]);if("data"in arguments[1])cache.populate()}).bind("unautocomplete",function(){select.unbind();$input.unbind();$(input.form).unbind(".autocomplete")});function selectCurrent(){var selected=select.selected();if(!selected)return false;var v=selected.result;previousValue=v;if(options.multiple){var words=trimWords($input.val());if(words.length>1){var seperator=options.multipleSeparator.length;var cursorAt=$(input).selection().start;var wordAt,progress=0;$.each(words,function(i,word){progress+=word.length;if(cursorAt<=progress){wordAt=i;return false}progress+=seperator});words[wordAt]=v;v=words.join(options.multipleSeparator)}v+=options.multipleSeparator}$input.val(v);hideResultsNow();$input.trigger("result",[selected.data,selected.value]);return true}function onChange(crap,skipPrevCheck){if(lastKeyPressCode==KEY.DEL){select.hide();return}var currentValue=$input.val();if(!skipPrevCheck&&currentValue==previousValue)return;previousValue=currentValue;currentValue=lastWord(currentValue);if(currentValue.length>=options.minChars){$input.addClass(options.loadingClass);if(!options.matchCase)currentValue=currentValue.toLowerCase();request(currentValue,receiveData,hideResultsNow)}else{stopLoading();select.hide()}};function trimWords(value){if(!value)return[""];if(!options.multiple)return[$.trim(value)];return $.map(value.split(options.multipleSeparator),function(word){return $.trim(value).length?$.trim(word):null})}function lastWord(value){if(!options.multiple)return value;var words=trimWords(value);if(words.length==1)return words[0];var cursorAt=$(input).selection().start;if(cursorAt==value.length){words=trimWords(value)}else{words=trimWords(value.replace(value.substring(cursorAt),""))}return words[words.length-1]}function autoFill(q,sValue){if(options.autoFill&&(lastWord($input.val()).toLowerCase()==q.toLowerCase())&&lastKeyPressCode!=KEY.BACKSPACE){$input.val($input.val()+sValue.substring(lastWord(previousValue).length));$(input).selection(previousValue.length,previousValue.length+sValue.length)}};function hideResults(){clearTimeout(timeout);timeout=setTimeout(hideResultsNow,200)};function hideResultsNow(){var wasVisible=select.visible();select.hide();clearTimeout(timeout);stopLoading();if(options.mustMatch){$input.search(function(result){if(!result){if(options.multiple){var words=trimWords($input.val()).slice(0,-1);$input.val(words.join(options.multipleSeparator)+(words.length?options.multipleSeparator:""))}else{$input.val("");$input.trigger("result",null)}}})}};function receiveData(q,data){if(data&&data.length&&hasFocus){stopLoading();select.display(data,q);autoFill(q,data[0].value);select.show()}else{hideResultsNow()}};function request(term,success,failure){if(!options.matchCase)term=term.toLowerCase();var data=cache.load(term);if(data&&data.length){success(term,data)}else if((typeof options.url=="string")&&(options.url.length>0)){var extraParams={timestamp:+new Date()};$.each(options.extraParams,function(key,param){extraParams[key]=typeof param=="function"?param():param});$.ajax({mode:"abort",port:"autocomplete"+input.name,dataType:options.dataType,url:options.url,data:$.extend({q:lastWord(term),limit:options.max},extraParams),success:function(data){var parsed=options.parse&&options.parse(data)||parse(data);cache.add(term,parsed);success(term,parsed)}})}else{select.emptyList();failure(term)}};function parse(data){var parsed=[];var rows=data.split("\n");for(var i=0;i<rows.length;i++){var row=$.trim(rows[i]);if(row){row=row.split("|");parsed[parsed.length]={data:row,value:row[0],result:options.formatResult&&options.formatResult(row,row[0])||row[0]}}}return parsed};function stopLoading(){$input.removeClass(options.loadingClass)}};$.Autocompleter.defaults={inputClass:"ac_input",resultsClass:"ac_results",loadingClass:"ac_loading",minChars:1,delay:400,matchCase:false,matchSubset:true,matchContains:false,cacheLength:100,max:1000,mustMatch:false,extraParams:{},selectFirst:true,formatItem:function(row){return row[0]},formatMatch:null,autoFill:false,width:0,multiple:false,multipleSeparator:" ",inputFocus:true,clickFire:false,highlight:function(value,term){return value.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)("+term.replace(/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi,"\\$1")+")(?![^<>]*>)(?![^&;]+;)","gi"),"<strong>$1</strong>")},scroll:true,scrollHeight:180};$.Autocompleter.Cache=function(options){var data={};var length=0;function matchSubset(s,sub){if(!options.matchCase)s=s.toLowerCase();var i=s.indexOf(sub);if(options.matchContains=="word"){i=s.toLowerCase().search("\\b"+sub.toLowerCase())}if(i==-1)return false;return i==0||options.matchContains};function add(q,value){if(length>options.cacheLength){flush()}if(!data[q]){length++}data[q]=value}function populate(){if(!options.data)return false;var stMatchSets={},nullData=0;if(!options.url)options.cacheLength=1;stMatchSets[""]=[];for(var i=0,ol=options.data.length;i<ol;i++){var rawValue=options.data[i];rawValue=(typeof rawValue=="string")?[rawValue]:rawValue;var value=options.formatMatch(rawValue,i+1,options.data.length);if(value===false)continue;var firstChar=value.charAt(0).toLowerCase();if(!stMatchSets[firstChar])stMatchSets[firstChar]=[];var row={value:value,data:rawValue,result:options.formatResult&&options.formatResult(rawValue)||value};stMatchSets[firstChar].push(row);if(nullData++<options.max){stMatchSets[""].push(row)}};$.each(stMatchSets,function(i,value){options.cacheLength++;add(i,value)})}setTimeout(populate,25);function flush(){data={};length=0}return{flush:flush,add:add,populate:populate,load:function(q){if(!options.cacheLength||!length)return null;if(!options.url&&options.matchContains){var csub=[];for(var k in data){if(k.length>0){var c=data[k];$.each(c,function(i,x){if(matchSubset(x.value,q)){csub.push(x)}})}}return csub}else if(data[q]){return data[q]}else if(options.matchSubset){for(var i=q.length-1;i>=options.minChars;i--){var c=data[q.substr(0,i)];if(c){var csub=[];$.each(c,function(i,x){if(matchSubset(x.value,q)){csub[csub.length]=x}});return csub}}}return null}}};$.Autocompleter.Select=function(options,input,select,config){var CLASSES={ACTIVE:"ac_over"};var listItems,active=-1,data,term="",needsInit=true,element,list;function init(){if(!needsInit)return;element=$("<div/>").hide().addClass(options.resultsClass).css("position","absolute").appendTo(document.body).hover(function(event){if($(this).is(":visible")){input.focus()}config.mouseDownOnSelect=false;console.debug(config.mouseDownOnSelect)});list=$("<ul/>").appendTo(element).mouseover(function(event){if(target(event).nodeName&&target(event).nodeName.toUpperCase()=='LI'){active=$("li",list).removeClass(CLASSES.ACTIVE).index(target(event));$(target(event)).addClass(CLASSES.ACTIVE)}}).click(function(event){$(target(event)).addClass(CLASSES.ACTIVE);select();if(options.inputFocus)input.focus();return false}).mousedown(function(){config.mouseDownOnSelect=true}).mouseup(function(){config.mouseDownOnSelect=false});if(options.width>0)element.css("width",options.width);needsInit=false}function target(event){var element=event.target;while(element&&element.tagName!="LI")element=element.parentNode;if(!element)return[];return element}function moveSelect(step){listItems.slice(active,active+1).removeClass(CLASSES.ACTIVE);movePosition(step);var activeItem=listItems.slice(active,active+1).addClass(CLASSES.ACTIVE);if(options.scroll){var offset=0;listItems.slice(0,active).each(function(){offset+=this.offsetHeight});if((offset+activeItem[0].offsetHeight-list.scrollTop())>list[0].clientHeight){list.scrollTop(offset+activeItem[0].offsetHeight-list.innerHeight())}else if(offset<list.scrollTop()){list.scrollTop(offset)}}};function movePosition(step){active+=step;if(active<0){active=listItems.size()-1}else if(active>=listItems.size()){active=0}}function limitNumberOfItems(available){return options.max&&options.max<available?options.max:available}function fillList(){list.empty();var max=limitNumberOfItems(data.length);for(var i=0;i<max;i++){if(!data[i])continue;var formatted=options.formatItem(data[i].data,i+1,max,data[i].value,term);if(formatted===false)continue;var li=$("<li/>").html(options.highlight(formatted,term)).addClass(i%2==0?"ac_even":"ac_odd").appendTo(list)[0];$.data(li,"ac_data",data[i])}listItems=list.find("li");if(options.selectFirst){listItems.slice(0,1).addClass(CLASSES.ACTIVE);active=0}if($.fn.bgiframe)list.bgiframe()}return{display:function(d,q){init();data=d;term=q;fillList()},next:function(){moveSelect(1)},prev:function(){moveSelect(-1)},pageUp:function(){if(active!=0&&active-8<0){moveSelect(-active)}else{moveSelect(-8)}},pageDown:function(){if(active!=listItems.size()-1&&active+8>listItems.size()){moveSelect(listItems.size()-1-active)}else{moveSelect(8)}},hide:function(){element&&element.hide();listItems&&listItems.removeClass(CLASSES.ACTIVE);active=-1},visible:function(){return element&&element.is(":visible")},current:function(){return this.visible()&&(listItems.filter("."+CLASSES.ACTIVE)[0]||options.selectFirst&&listItems[0])},show:function(){var offset=$(input).offset();element.css({width:typeof options.width=="string"||options.width>0?options.width:$(input).width(),top:offset.top+input.offsetHeight,left:offset.left}).show();if(options.scroll){list.scrollTop(0);list.css({maxHeight:options.scrollHeight,overflow:'auto'});if($.browser.msie&&typeof document.body.style.maxHeight==="undefined"){var listHeight=0;listItems.each(function(){listHeight+=this.offsetHeight});var scrollbarsVisible=listHeight>options.scrollHeight;list.css('height',scrollbarsVisible?options.scrollHeight:listHeight);if(!scrollbarsVisible){listItems.width(list.width()-parseInt(listItems.css("padding-left"))-parseInt(listItems.css("padding-right")))}}}},selected:function(){var selected=listItems&&listItems.filter("."+CLASSES.ACTIVE).removeClass(CLASSES.ACTIVE);return selected&&selected.length&&$.data(selected[0],"ac_data")},emptyList:function(){list&&list.empty()},unbind:function(){element&&element.remove()}}};$.fn.selection=function(start,end){if(start!==undefined){return this.each(function(){if(this.createTextRange){var selRange=this.createTextRange();if(end===undefined||start==end){selRange.move("character",start);selRange.select()}else{selRange.collapse(true);selRange.moveStart("character",start);selRange.moveEnd("character",end);selRange.select()}}else if(this.setSelectionRange){this.setSelectionRange(start,end)}else if(this.selectionStart){this.selectionStart=start;this.selectionEnd=end}})}var field=this[0];if(field.createTextRange){var range=document.selection.createRange(),orig=field.value,teststring="<->",textLength=range.text.length;range.text=teststring;var caretAt=field.value.indexOf(teststring);field.value=orig;this.selection(caretAt,caretAt+textLength);return{start:caretAt,end:caretAt+textLength}}else if(field.selectionStart!==undefined){return{start:field.selectionStart,end:field.selectionEnd}}}})(jQuery);
/* http://github.com/davist11/jQuery-Simple-Validate */
(function(a,b,c,d){var e=function(b,c){this.elem=b;this.$elem=a(b);this.options=c;this.metadata=this.$elem.data("plugin-options");this.$requiredInputs=this.$elem.find(":input.required")};e.prototype={defaults:{errorClass:"error",errorText:"{label} is a required field.",emailErrorText:"Please enter a valid {label}",errorElement:"strong",removeLabelChar:"*",inputErrorClass:"input-error",completeCallback:"",ajaxRequest:false},init:function(){var b=this;b.config=a.extend({},b.defaults,b.options,b.metadata);b.errorMsgType=b.config.errorText.search(/{label}/);b.emailErrorMsgType=b.config.emailErrorText.search(/{label}/);b.$elem.on("submit.simpleValidate",a.proxy(b.handleSubmit,b));return this},checkField:function(b){var c=this;var d=c.$requiredInputs.eq(b);var e=a.trim(d.val());var f=d.siblings("label").text().replace(c.config.removeLabelChar,"");var g="";if(e===""){g=c.formatErrorMsg(c.config.errorText,f,c.errorMsgType);c.hasError=true}else if(d.hasClass("email")){if(!/^([_a-z0-9-]+)(\.[_a-z0-9-]+)*@([a-z0-9-]+)(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/.test(e.toLowerCase())){g=c.formatErrorMsg(c.config.emailErrorText,f,c.emailErrorMsgType);c.hasError=true}}if(g!==""){d.addClass(c.config.inputErrorClass)}},formatErrorMsg:function(a,b,c){return c>-1?a.replace("{label}",b):a},handleSubmit:function(b){var c=this;this.hasError=false;c.$elem.find(c.config.errorElement+"."+c.config.errorClass).remove();c.$elem.find(":input."+c.config.inputErrorClass).removeClass(c.config.inputErrorClass);c.$requiredInputs.each(a.proxy(c.checkField,c));if(c.hasError){b.preventDefault()}else if(c.config.completeCallback!==""){c.config.completeCallback(c.$elem);if(c.config.ajaxRequest){b.preventDefault()}}}};e.defaults=e.prototype.defaults;a.fn.simpleValidate=function(a){return this.each(function(){(new e(this,a)).init()})}})(jQuery,window,document)