package com.qsp.gsm.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import org.postgresql.Driver;

import com.qsp.gsm.model.Product;

public class Controller {
	public int addProduct(int id, String name, int price, int quantity, boolean availability) {
		Connection connection = null;
		int rowsAffected=0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			 connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES (?,?,?,?,?);");
			
			prepareStatement.setInt(1, id);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(3, price);
			prepareStatement.setInt(4, quantity);
			prepareStatement.setBoolean(5, availability);
			rowsAffected = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return rowsAffected;
	}
	public void addMultipleProducts(ArrayList<Product> products) {
		Connection connection=null;
		
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			 connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES (?,?,?,?,?);");
			
			for (Product product : products) {
				prepareStatement.setInt(1, product.getP_id());
				prepareStatement.setString(2, product.getP_name());
				prepareStatement.setInt(3, product.getP_price());
				prepareStatement.setInt(4, product.getP_quantity());
				prepareStatement.setBoolean(5, product.isP_availability());
				prepareStatement.addBatch();
			}
				prepareStatement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	public ResultSet fetchProduct(int id) {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			 connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			 
			 PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM product WHERE p_id=?");
			 
			 prepareStatement.setInt(1, id);
			 resultSet = prepareStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}
	
	public int removeProduct(int id) {
		int executeUpdate = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			 
			 PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM product WHERE p_id=?");
			 
			 prepareStatement.setInt(1, id);
			 executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return executeUpdate;
	}
	public int updateName(int id, String name) {
		int executeUpdate = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			
			 PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_name=? WHERE p_id=? ;");
			 
			 prepareStatement.setString(1, name);
			 prepareStatement.setInt(2, id);
			 executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return executeUpdate;
		
	}
	public int updatePrice(int id, int price) {
		int executeUpdate = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			
			 PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_price=? WHERE p_id=? ;");
			 
			 prepareStatement.setInt(1, price);
			 prepareStatement.setInt(2, id);
			 executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return executeUpdate;
		
	}
	public int updateQunatity(int id, int quantity) {
		int executeUpdate = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			
			 PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_quantity=? WHERE p_id=? ;");
			 
			 prepareStatement.setInt(1, quantity);
			 prepareStatement.setInt(2, id);
			 executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return executeUpdate;
		
	}
	public int updateAvailablity(int id, int quantity) {
		int executeUpdate = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			
			 PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_availability=? WHERE p_id=? ;");
			 
			 if (quantity > 0) {
				prepareStatement.setBoolean(1, true);
			} else {
				prepareStatement.setBoolean(1, false);
			}
			 prepareStatement.setInt(2, id);
			 executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return executeUpdate;
		
	}
}
