package com.web;

import gla.cart.Cart;
import gla.cart.CartItem;
import gla.cart.util.EntityUtils;
import gla.cart.util.SerializationUtils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Product;


//do not delete this class! 17/10/15
//newadd
public class NewAddElemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text;charset=UTF-8");

		/**********	用beanUtils自动封装到实体 ********/
		Product productEntity = EntityUtils.autoBean(request, Product.class);
		System.out.println("[auto id =  "+productEntity.getId()+"\tauto price = "+productEntity.getPrice()+"]");
		/******************/
		
		String userId = (String) request.getSession().getAttribute("userId");
		
		
		Cart cart = Cart.getCart(userId);		//获取用户id
		cart.addElem(productEntity);	//传谁就是谁
		request.getSession().setAttribute("cart", cart);
		System.out.println("-------------------------------");
		Map<Integer, CartItem<?>> map = cart.getCartMapping();
		for (Integer elem : map.keySet()) {
			Product pro = (Product) map.get(elem).getT();
			System.out.println(pro.getId()+"\t"+pro.getName()+"\t"+pro.getPrice()+"\t"+map.get(elem).getCount()+"\t"+map.get(elem).getTotal());
		}
		System.out.println("商品总件数= "+cart.getSumCount());
		System.out.println("商品总价= "+cart.getSumPrice());
		
		//序列化试验
		//将购物车实体保存在硬盘上
		SerializationUtils.saveCart(userId, cart);
	
	}

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
