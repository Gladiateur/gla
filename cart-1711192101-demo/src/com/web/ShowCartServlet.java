package com.web;

import gla.cart.Cart;
import gla.cart.CartItem;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Product;

//do not delete this class! 17/10/30
//showCart
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text;charset=UTF-8");
		
		//这里对于cart来讲无所谓获取的是用户实体还是用户id,只要能得到用户id
		//并且把它传到获取购物车对象的方法中即可
		String userId = (String) request.getSession().getAttribute("userId");
		
		Cart cart = Cart.getCart(userId);
		Map<Integer, CartItem<?>> cartMapping = cart.getCartMapping();
		
		for (Integer elem : cartMapping.keySet()) {
			Product pro = (Product) cart.getCartMapping().get(elem).getT();
			System.out.println(pro.getId()+"\t"+pro.getName()+"\t"+pro.getPrice()+"\t"+cartMapping.get(elem).getCount()+"\t"+cartMapping.get(elem).getTotal());
		}
		System.out.println("商品总件数= "+cart.getSumCount());
		System.out.println("商品总价= "+cart.getSumPrice());
		
		request.getSession().setAttribute("cart", cart);
		request.getRequestDispatcher("/cart2.jsp").forward(request, response);
	}

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
