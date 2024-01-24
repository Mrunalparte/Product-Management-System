package com.qsp.gsm.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.qsp.gsm.controller.Controller;
import com.qsp.gsm.model.Product;

public class Shop_View {

	static Scanner myInput = new Scanner(System.in);
    static Product product = new Product();
    static Controller controller = new Controller();
   	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
   		do {
   	   		System.out.println("Select operation to perform:");
   			System.out.println("1.Add product\n2.Remove product\n3.Update Product\n4.Fetch product\n0.Eixt");
   			System.out.print("Enter values of desired option : ");
   			int userInput = myInput.nextInt();
   			myInput.nextLine();
   			
   			switch (userInput) {
   			
   			case 0:
   				myInput.close();
   				System.out.println("----Exited----");
   				System.exit(0);
   				break;
   				
   			case 1:
   				System.out.println("How many products you want to add ?\n1. Single product\n2. Multiple products");
   				int productsCount = myInput.nextInt();
   				myInput.nextLine();
   				if(productsCount == 1) {
   					System.out.println("Enter product id : ");
   					int i_p_id = myInput.nextInt();
   					myInput.nextLine();
   					System.out.println("Enter product name : ");
   					String i_p_name = myInput.nextLine();
   					System.out.println("Enter product price : ");
   					int i_p_price = myInput.nextInt();
   					myInput.nextLine();
   					System.out.println("Enter product quantity : ");
   					int i_p_quantity = myInput.nextInt();
   					myInput.nextLine();
   					boolean i_p_availability = false;
   					if (i_p_quantity > 0) {
   						i_p_availability = true;
   					}
   						if ((controller.addProduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_availability)) !=0) {
   							System.out.println("Product Added");
   						} else {
   							System.out.println("Product Not Added");
   						}
   					}
   				else {
   						boolean toContinue = true;
   						ArrayList<Product> products = new ArrayList<Product>();
   						do {
   							Product product = new Product();
   							System.out.println("Enter Product id : ");
   							product.setP_id(myInput.nextInt());
   							myInput.nextLine();
   							System.out.println("Enter Product name : ");
   							product.setP_name(myInput.nextLine());
   							System.out.println("Enter Product price : ");
   							product.setP_price(myInput.nextInt());
   							myInput.nextLine();
   							System.out.println("Enter Product quantity : ");
   							int quantity = myInput.nextInt();
   							product.setP_quantity(quantity);
   							myInput.nextLine();
   							boolean i_p_availability = false;
   							if (quantity > 0) {
   								i_p_availability=true;
   							}
   							product.setP_availability(i_p_availability);
   							products.add(product);
   							System.out.println("Press 1 to continue adding products, Press 0 to stop adding products : ");
   							int toAdd = myInput.nextInt();
   							myInput.nextLine();
   							if (toAdd == 0) {
   								toContinue = false;
   							}
   						} while (toContinue);
   						controller.addMultipleProducts(products);
   					}
   				break;
   			case 2:
   				System.out.println("Enter Product id to remove : ");
   				int productIdToRemove = myInput.nextInt();
   				myInput.nextLine();
   				if (controller.removeProduct(productIdToRemove) != 0) {
					System.out.println("Ha delete ho gaya");
					System.out.println();
				} else {
					System.out.println("Product with given id does not exist, No remove operation perform");
				}
   				System.out.println();
   				break;
   			case 3:
   				System.out.println("Enter Product id to update :");
   				int ProdutIdToUpdate = myInput.nextInt();
   				myInput.nextLine();
   				ResultSet product = controller.fetchProduct(ProdutIdToUpdate);
   				if (product.next()) {
					System.out.println("What you want to update ?");
					System.out.println("1.Name\n2.Price\n3.Qunatity");
					System.out.println("Enter number respective to desired option : ");
					byte updateOption = myInput.nextByte();
					myInput.nextLine();
					switch (updateOption) {
					case 1:
						System.out.println("Enter name to update : ");
						String nameToUpdate = myInput.nextLine();
						int executeUpdate = controller.updateName(ProdutIdToUpdate, nameToUpdate);
						System.out.println(executeUpdate+ "Product updated successfully");
					
						break;

					case 2:
						System.out.println("Enter price to updtae : ");
						int priceToUpadte = myInput.nextInt();
						int updatePrice = controller.updatePrice(ProdutIdToUpdate, priceToUpadte);
						System.out.println(updatePrice+ "Product updated successfully");
						
						break;
						
					case 3:
						System.out.println("Enter quantity to update : ");
						int quantityToUpdate = myInput.nextInt();
						int updateQuantity = controller.updateQunatity(ProdutIdToUpdate, quantityToUpdate);
						int updateAvailablity = controller.updateAvailablity(ProdutIdToUpdate, updateQuantity);
						System.out.println(updateQuantity+ "Product updated successfully");
						break;
					default:
						System.out.println("INVALID SELECTION");
						break;
					}
				} else {
					System.out.println("Product with given id does not exist, Update operation cannot be performed");
				}
   				break;
   			case 4:
   				System.out.println("Enter Product id to fetch : ");
   				int productIdToFind = myInput.nextInt();
   				myInput.nextLine();
   				ResultSet fetchProduct = controller.fetchProduct(productIdToFind);
//   				System.out.println(fetchProduct);
   				boolean next = fetchProduct.next();
   				if (next) {
   					System.out.println("PRODUCT DETAILS");
   					System.out.println("Id : "+fetchProduct.getInt(1));
   					System.out.println("Name :"+fetchProduct.getString(2));
   					System.out.println("Price :"+fetchProduct.getInt(3));
   					System.out.println("Quantity :"+fetchProduct.getInt(4));
   					if (fetchProduct.getBoolean(5)) {
   						System.out.println("Availablity : Availabel");
   					} else {
   						System.out.println("Availablity : Not Available");
   					}
   					System.out.println();
   				} else {
   					System.out.println("Product with id :"+ productIdToFind +" does not exist." );
   					System.out.println();
   				}
   				break;

   			default:
   				System.out.println("- - - - INVALID SELECTION- - - - ");
   				break;
   			}
		} while (true);


	}
}

		
	


