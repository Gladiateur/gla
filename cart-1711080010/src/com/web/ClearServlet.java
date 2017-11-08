package com.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gla.cart.Cart;

//do not delete this class! 17/10/15
public class ClearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text;charset=UTF-8");
		
		
		/***********************************/
		String userId = (String) request.getSession().getAttribute("userId");
		Cart.getCart(userId).clearCart();
		/***********************************/
	}

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
