package com.spring.shopping.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.spring.shopping.controller.constants.ControllerConstants;
import com.spring.shopping.model.Category;
import com.spring.shopping.model.Customer;
import com.spring.shopping.model.Product;
import com.spring.shopping.model.ReviewForm;
import com.spring.shopping.model.SubCategory;
import com.spring.shopping.service.CartData;
import com.spring.shopping.service.CategoryConfigService;
import com.spring.shopping.service.CustomerService;
import com.spring.shopping.service.ProductConfigService;
import com.spring.shopping.service.ReviewService;
import com.spring.shopping.util.SessionUtils;

@Controller
public class ProductController {

//	@Autowired
//	private CartService cartService;
	@Autowired
	private ProductConfigService productConfigurationService;
	@Autowired
	private CategoryConfigService categoryConfigurationService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private CustomerService customerService;
	
	private HttpSession session;

	/**
	 * Product Controller method which retrieves the information required in the
	 * product page
	 * 
	 * 
	 * @param Model
	 * @param ProductId
	 * @return Product Page View
	 * @throws ParseException
	 */
	@RequestMapping(value = "/product", method = RequestMethod.GET, params = { "productId" })
	public String handleRequest(Model model,
			@RequestParam(value = "productId") Long productId,
			HttpServletRequest request) throws ParseException {
		Product product = productConfigurationService.getProductById(productId);
		model.addAttribute("product", product);

		CartData cartData = SessionUtils.getSessionVariables(request,ControllerConstants.CART);	
		if(cartData != null)
			model.addAttribute(ControllerConstants.NUMBER_OF_ITEMS, cartData.getNumberOfItems());

		Customer customer = SessionUtils.getSessionVariables(request,
				ControllerConstants.CUSTOMER);
		// Retrieve Reviews of a particular product
		Map<Customer, ReviewForm> reviewMap = new HashMap<Customer, ReviewForm>();
		if (customer != null) {
			List<ReviewForm> reviewsList = reviewService
					.getProductReviews(productId);
			for (ReviewForm reviewForm : reviewsList) {
				Long customerId = reviewForm.getCustomerId();
				reviewMap.put(customerService.getCustomerById(customerId),
						reviewForm);
			}
			model.addAttribute("reviewsList", reviewMap);
		}
		return "product";
	}
	

	
	@RequestMapping(value = "/createnewproduct", method = RequestMethod.GET)
	public String createNewProduct( Model model){
		//Category c = new Category().getcategoryId();c.getCategoryName()
		/*SubCategory sc = new SubCategory();
		sc.getSubCategoryId();
		sc.getSubCategoryName();
		sc.getCategoryId();*/
		
		List<Category> allCategories = categoryConfigurationService.getAllCategories();
		List<SubCategory> allSubCategories = categoryConfigurationService.getAllSubCategories();
		model.addAttribute("categoryList",allCategories);
		model.addAttribute("subcategoryList",allSubCategories);
		return "createProduct";
	}
	
	@RequestMapping(value = "/createnewproduct", method = RequestMethod.POST)
	public String handleNewProduct(@ModelAttribute("Product") Product product,@RequestParam("image") MultipartFile image,@RequestParam("productzip") MultipartFile productzip, BindingResult bindingResult, HttpServletRequest request, Model model){
		
	
		
		session = SessionUtils.createSession(request);
		Long customerId = ((Customer) session.getAttribute("customer"))
				    .getCustomerId();
		product.setCustomerId(customerId);
		product.setFeatured(1);
		product.setAvailable(1);
		long saveNewProduct = productConfigurationService.saveNewProduct(product, product.getCategory_Id(), product.getSubCategory_Id());
		
		handleUpload(saveNewProduct,image,productzip);
		
		String successfull = saveNewProduct == 1? "true": "false";
		model.addAttribute("successful", successfull);
		model.addAttribute("messsage", saveNewProduct == 1? "Successfully saved product!" : "Error adding product!");
		return "createProduct";
	}
	
	void handleUpload(long id,MultipartFile image,MultipartFile productzip){
                byte[] bytes;
                byte[] bytesZip;
				try {
				//String path = request.getRealPath("") + "/images/home/"+ image.getOriginalFilename();
				bytes = image.getBytes();
				
				//image.transferTo(new File("slikahack"+id + ".jpg"));
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "images");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + id+".jpg");
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                
                
                bytesZip = image.getBytes();
				
                // Creating the directory to store file
              
                 dir = new File(rootPath + File.separator + "zip");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                 serverFile = new File(dir.getAbsolutePath()
                        + File.separator + id+".zip");
                 stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytesZip);
                stream.close();
 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             
   
	}
			
}
