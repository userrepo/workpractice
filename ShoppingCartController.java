package com.mss.web;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mss.app.entity.CartDetails;
import com.mss.app.entity.Product;
import com.mss.dao.IproductDao;



//adds items to shoppingcart  and get the total price of the cart

@Controller
/*@RequestMapping(value="/shoppingcart")*/
@SessionAttributes({"productdatas","productids","productprice","totalprice","cartdetails","id"})
public class ShoppingCartController {

	HttpSession httpsession;
	HttpServletRequest request;
	HttpServletResponse response;
	SessionFactory sessionfactory;
	double totalprice=0,price;
	String name;
	List<CartDetails> listofcartitems=new ArrayList<CartDetails>();
	@Autowired
	private IproductDao productDao;
     


	@RequestMapping(value="/sum1",method=RequestMethod.GET)
	public String  mapShoppingCartItems(ModelMap modelandview){
		
		List<Product>productdata=productDao.getProductData();
		modelandview.addAttribute("productdetails",productdata);
		return "welcome";	
	}




	@RequestMapping(value="/sum1",method=RequestMethod.POST)
	public String  addShoppingCartItems(HttpServletRequest request,HttpSession session,Model modelMap,Map map)
	{
		//int proquant=1;		
		
		
		int productspecificid=Integer.parseInt(request.getParameter("id"));
		int prodquant=Integer.parseInt(request.getParameter("prodquant"));

		List<Object[]>list=productDao.getProductPriceName(productspecificid);
		for(Object object:list)
		{
			Object[] ar=(Object[])object;
			name=(String)ar[0];
			price=(Double)ar[1];        	
		}
		System.out.println("hi");
		
		CartDetails cartdetails=new CartDetails(prodquant,productspecificid,name,price);
        System.out.println(cartdetails);
		totalprice+=price*prodquant;
		listofcartitems.add(cartdetails);
		System.out.println(listofcartitems);
		modelMap.addAttribute("id",productspecificid);
		modelMap.addAttribute("cartdetails",listofcartitems);		
		modelMap.addAttribute("totalprice",totalprice);
		if(productspecificid<3)
		return "welcome";
		else if(productspecificid==3)
		return  "redirect:/cart";
		
		return null;
	}




}

