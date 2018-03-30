package com.onlinestore.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.onlinestore.authentication.JwtToken;
import com.onlinestore.model.JWTToken;
import com.onlinestore.model.LoginCredential;
import com.onlinestore.model.ProductId;
import com.onlinestore.model.Products;
import com.onlinestore.model.Response;
import com.onlinestore.service.AddService;
import com.onlinestore.service.DeleteService;
import com.onlinestore.service.EditService;
import com.onlinestore.service.LoginService;
import com.onlinestore.util.OnlineStroreUtil;
import com.onlinestore.validator.Vallidation;

@Controller
@CrossOrigin
public class OnlineStoreController {
	private static final Logger LOGGER = LogManager.getLogger(OnlineStoreController.class);

	@Autowired
	Products products;
	Map<String, Products> productMap = new ConcurrentHashMap<String, Products>();
	List<Products> productList = null;
	List<String> responseList = null;

	@GetMapping("/welcome")
	public String welcome() {
		return "index";
	}

	@PostMapping(value = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@ResponseBody
	public String login(@RequestBody LoginCredential loginCredential) {
		Response response = new Response();
		try {
		String result = loginService.authenticate(loginCredential);
		if ("success".equalsIgnoreCase(result)) {
			String token=JwtToken.addAuthentication(loginCredential.getUserid());
			response.setToken(token);
			response.setMessage("success");
			response.setCode("0");
			response.setNextPage("homepage");
		} else {
			response.setMessage("fail");
			response.setCode("99");
			response.setNextPage("loginErrorPage");
		}
		}catch (Exception e) {
			response.setMessage("fail");
			response.setCode("99");
			response.setNextPage("loginErrorPage");
		}
		JSONObject jsonObj = new JSONObject(response);
		return jsonObj.toString();

	}
	@PostMapping(value = "/api/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@ResponseBody
	public String tokenValidation(@RequestBody JWTToken jwtToken) {
		Response response = new Response();
		String json =null;
		String token=jwtToken.getToken();
		if (null==token) {
			
			response.setMessage("fail");
			response.setNextPage("/welcome");
			}else {
				response.setMessage("success");
			}
			JSONObject jsonObj = new JSONObject(response);
			json=jsonObj.toString();	
		return json;
	}

	@GetMapping("/homepage")

	public String home(Model model) {
		LOGGER.debug("Home Page Start");
		model.addAttribute("name", "Abhik");
		return "homePage";
	}

	@PostMapping(value = "/add")
	@CrossOrigin
	@ResponseBody
	public String add(@RequestBody Products products) {
		Response response = new Response();
		try {
		if (Vallidation.Validate(products)) {
			addService.addProducts(productMap, products);
			String id = products.getId();
			response.setId(id);
			response.setMessage("success");
			response.setCode("0");
			response.setNextPage("addedItemMsgPage");
		} else {
			response.setMessage("fail");
			response.setCode("99");
			response.setNextPage("homepage");
		}
		}catch (Exception e) {
			response.setMessage("fail");
			response.setCode("99");
			response.setNextPage("loginErrorPage");
		}
		JSONObject jsonObj = new JSONObject(response);
		return jsonObj.toString();

	}

	@GetMapping(value = "/edit/{id}")
	public String edit(@ModelAttribute("Products") Products prd, Model model,@PathVariable String id) {
		
		Products products = editService.editProduct(productMap, id);
		if (Vallidation.Validate(products)) {
		model.addAttribute("id", id);
		model.addAttribute("oldProducts", products);
		return "UpdateItemInput";
		}
		else {
			return "homePage";
		}
		// productList=onlineStroreUtil.populateProducts(productMap);
		// String json = new Gson().toJson(productList );
		// return json;

	}

	@PostMapping(value = "/updateItemForm")
	public String productUpdate(@ModelAttribute Products products, Model model) {
		addService.addProducts(productMap, products);
		model.addAttribute("id", products.getId());
		return "UpdatedIteamMsg";

	}

	@PostMapping(value = "/delete")
	@CrossOrigin
	@ResponseBody
	public String delete(Model model, @RequestBody ProductId productId) {
		Response response = new Response();
		try {
		String id = productId.getId();
		productMap = deleteService.deleteProduct(productMap, id);
		
		response.setId(id);
		response.setMessage("success");
		response.setCode("0");
		response.setNextPage("deleteItemMsgPage");
		}catch (Exception e) {
			response.setMessage("fail");
			response.setCode("99");
			response.setNextPage("loginErrorPage");
		}
	JSONObject jsonObj = new JSONObject(response);
	return jsonObj.toString();

}

	@PostMapping("/fetchProductList")
	@CrossOrigin
	@ResponseBody
	public String getProductList(@RequestBody JWTToken jwtToken) {
		Response response = new Response();
		String json =null;
		JSONObject jsonObj;
		String token=jwtToken.getToken();
		try {
		if (null==token) {
			
			response.setMessage("fail");
			response.setNextPage("/welcome");
			jsonObj= new JSONObject(response);
			json=jsonObj.toString();
		}else {
		productList = onlineStroreUtil.populateProducts(productMap);
		 json = new Gson().toJson(productList);
		
		}
		}catch (Exception e) {
			response.setMessage("fail");
			response.setCode("99");
			response.setNextPage("loginErrorPage");		
			jsonObj = new JSONObject(response);
			json=jsonObj.toString();
		}
		return json;
	}

	@GetMapping("/loginErrorPage")
	public String error() {

		return "loginError";
	}

	@GetMapping("/addedItemMsgPage/{id}")
	public String ddedItemMsg(Model model,@PathVariable String id) {
		model.addAttribute("id", id);
		return "addedItemMsg";
	}

	@GetMapping("/deleteItemMsgPage/{id}")
	public String deleteItemMsg(Model model,@PathVariable String id) {
		model.addAttribute("id", id);
		return "deleteItemMsg";
	}

	@GetMapping("/UpdatedIteamMsgPage")
	public String UpdatedIteamMsg() {

		return "UpdatedIteamMsg";
	}

	private LoginService loginService;
	private AddService addService;

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Autowired
	public void setAddService(AddService addService) {
		this.addService = addService;
	}

	private OnlineStroreUtil onlineStroreUtil;

	@Autowired
	public void SetOnlineStroreUtil(OnlineStroreUtil onlineStroreUtil) {
		this.onlineStroreUtil = onlineStroreUtil;
	}

	private DeleteService deleteService;

	@Autowired
	public void SetDeleteService(DeleteService deleteService) {
		this.deleteService = deleteService;
	}

	private EditService editService;

	@Autowired
	public void SetEditService(EditService editService) {
		this.editService = editService;
	}
}
