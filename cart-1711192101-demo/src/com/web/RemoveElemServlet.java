package com.web;

import gla.cart.Cart;
import gla.cart.util.EntityUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//remove
public class RemoveElemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text;charset=UTF-8");
		
		Integer elemId = Integer.parseInt(request.getParameter("elemId"));
		//System.out.println(elemId);
		//---------------------------------------------//
		String userId = (String) request.getSession().getAttribute("userId");
		Cart cart = Cart.getCart(userId);
		cart.removeElem(elemId);
		//---------------------------------------------//
		cart.showCart();
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
