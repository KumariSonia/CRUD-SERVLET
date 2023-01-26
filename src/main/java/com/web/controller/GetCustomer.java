package com.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dao.CustomerDao;
import com.web.model.Customer;

@WebServlet("/getCustomer")
public class GetCustomer extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int customerID = Integer.parseInt(req.getParameter("customerID"));
		Customer customer = null;

		customer = CustomerDao.getCustomerById(customerID);
		req.setAttribute("customer", customer);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("viewCustomer.jsp");
		requestDispatcher.forward(req, resp);
	}

}
