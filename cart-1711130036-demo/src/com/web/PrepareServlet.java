package com.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Product;

//do not delete this class! 17/10/15
public class PrepareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 17.6.9 Modified template by Gladiateur
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text;charset=UTF-8");
		
		String userId = request.getParameter("userId");
		System.out.println("Get userId = "+userId);
		request.getSession().setAttribute("userId", userId);//实际上这里是User对象
		////////////////////////////////
		List<Product> list = new ArrayList<Product>();
		Product tb0 = new Product();
		tb0.setId(1);
		tb0.setName("红米3");
		tb0.setPrice(100.0);
		list.add(tb0);
		Product tb1 = new Product();
		tb1.setId(2);
		tb1.setName("三星");
		tb1.setPrice(250.0);
		list.add(tb1);
		Product tb2 = new Product();
		tb2.setId(3);
		tb2.setName("vivo");
		tb2.setPrice(500.0);
		list.add(tb2);
		Product tb3 = new Product();
		tb3.setId(4);
		tb3.setName("iPhone");
		tb3.setPrice(1000.0);
		list.add(tb3);
		Product tb4 = new Product();
		tb4.setId(5);
		tb4.setName("华为");
		tb4.setPrice(1500.0);
		list.add(tb4);
		Product tb5 = new Product();
		tb5.setId(6);
		tb5.setName("联想");
		tb5.setPrice(1800.0);
		list.add(tb5);
		Product tb6 = new Product();
		tb6.setId(7);
		tb6.setName("ipad");
		tb6.setPrice(2500.0);
		list.add(tb6);
		
		request.getSession().setAttribute("list", list);
		System.out.println("已做好数据的准备！");
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
