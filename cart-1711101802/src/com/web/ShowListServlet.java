package com.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//do not delete this class! 17/10/15
public class ShowListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("UTF-8");
		response.setContentType("html/text;charset=UTF-8");
//		List<TestBean> list = new ArrayList<TestBean>();
//		list = (List<TestBean>) request.getAttribute("list");
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
