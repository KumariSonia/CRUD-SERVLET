package com.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.model.Customer;

public class CustomerDao {

	public static Connection getConnection() {
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/demo";
		String userName = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

	public static int save(Customer customer) {
		int status = 0;
		String query = "INSERT INTO Customer (CustomerName, ContactName, Address, City, PostalCode, Country) VALUES (?, ?, ?, ?, ?, ?)";
		try {

			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getContactName());
			preparedStatement.setString(3, customer.getAddress());
			preparedStatement.setString(4, customer.getCity());
			preparedStatement.setString(5, customer.getPostalCode());
			preparedStatement.setString(6, customer.getCountry());

			status = preparedStatement.executeUpdate();

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int update(Customer customer) {
		int status = 0;
		String query = "update Customer set CustomerName=?, ContactName=?, Address=?, City=?, PostalCode=?, Country=? where CustomerID=?";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getContactName());
			preparedStatement.setString(3, customer.getAddress());
			preparedStatement.setString(4, customer.getCity());
			preparedStatement.setString(5, customer.getPostalCode());
			preparedStatement.setString(6, customer.getCountry());
			preparedStatement.setInt(7, customer.getCustomerID());

			status = preparedStatement.executeUpdate();

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int delete(int customerID) {
		int status = 0;
		String query = "delete from Customer where CustomerID=?";
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, customerID);
			status = ps.executeUpdate();

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static Customer getCustomerById(int customerID) {
		Customer customer = new Customer();
		String query = "select * from Customer where CustomerID=?";

		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customerID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer.setCustomerID(resultSet.getInt(1));
				customer.setCustomerName(resultSet.getString(2));
				customer.setContactName(resultSet.getString(3));
				customer.setAddress(resultSet.getString(4));
				customer.setCity(resultSet.getString(5));
				customer.setPostalCode(resultSet.getString(6));
				customer.setCountry(resultSet.getString(7));

			}
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customer;
	}

	public static List<Customer> getAllCustomer() {
		List<Customer> list = new ArrayList<Customer>();
		String query = "select * from Customer";

		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getInt(1));
				customer.setCustomerName(resultSet.getString(2));
				customer.setContactName(resultSet.getString(3));
				customer.setAddress(resultSet.getString(4));
				customer.setCity(resultSet.getString(5));
				customer.setPostalCode(resultSet.getString(6));
				customer.setCountry(resultSet.getString(7));

				list.add(customer);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
