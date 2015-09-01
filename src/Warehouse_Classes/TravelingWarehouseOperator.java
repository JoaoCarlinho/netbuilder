package Warehouse_Classes;

import java.sql.*;
import java.util.*;


public class TravelingWarehouseOperator {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/nbgardens_warehouse_database";
	static final String USER = "root";
	static final String PASS = "netbuilder";
	private Connection conn;
	
	ArrayList<CustomerOrderLine> shelfCodes = new ArrayList<CustomerOrderLine>();
	ArrayList<Integer> mapRoute = new ArrayList<Integer>();
	int customerOrderID;
	
	public TravelingWarehouseOperator(int customerOrderID) throws ClassNotFoundException, SQLException{
		shelfCodes = productArray(customerOrderID);
		sortShelves(shelfCodes);
		System.out.println("Pick items in the following order:");
		for(int i = 0; i < mapRoute.size(); i++){
			System.out.println(mapRoute.get(i));
		}
		
	}
	
	
	/************************Access Database in order to retrieve Customer Order Lines from the database*****************/

	public ArrayList<CustomerOrderLine> productArray(int customerOrderID) throws ClassNotFoundException, SQLException{
			shelfCodes = new ArrayList<CustomerOrderLine>();
			this.customerOrderID = customerOrderID;
			Statement stmt = null;
			
			try {
				Class.forName(JDBC_DRIVER);
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();
				// prepare statement
				
				String sql = "SELECT * FROM customer_order_line";
		
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					CustomerOrderLine tempCustomerOrderline = convertRowToCustomerOrderLine(rs);
					shelfCodes.add(tempCustomerOrderline);
				}
				
				for(int i = 0; i < shelfCodes.size(); i++){	
				}

			}
			finally {
				try {
					  if (stmt != null)
					   conn.close();
					  } catch (SQLException se) { }
					  try {
					   if (conn != null)
					    conn.close();
					   } catch (SQLException se) {
					    se.printStackTrace();
					   }

			}
			return shelfCodes;
}
	
	
	
	
/************************Create an array list using retrieved Data to store Customer Order Lines***********************/
	
	private CustomerOrderLine convertRowToCustomerOrderLine(ResultSet rs) throws SQLException {
		int customerOrderID = rs.getInt("customerOrderID");
		int productID = rs.getInt("productID");
     	int quantity = rs.getInt("quantity");
     	int productStatusCode = rs.getInt("productStatusCode");
     	int porousCount = rs.getInt("porousCount");
     	int handlerID = rs.getInt("handlerID");
     	int orderStatusCode = rs.getInt("orderStatusCode");
		
		CustomerOrderLine tempCustomerOrderLine= new CustomerOrderLine(customerOrderID, productID, quantity, productStatusCode, porousCount, handlerID, orderStatusCode);
		return tempCustomerOrderLine;
	}
	
	
	
	/*****************Create array lists containing products grouped by aisle     (1-5, 6-10, 11-15, 16-20, 21-25)******/	
	
	public ArrayList<Integer> sortShelves(ArrayList<CustomerOrderLine> Shelves){
		mapRoute = new ArrayList<Integer>();
		ArrayList<Integer> aisleOne = new ArrayList<Integer>();
		ArrayList<Integer> aisleTwo = new ArrayList<Integer>();
		ArrayList<Integer> aisleThree = new ArrayList<Integer>();
		ArrayList<Integer> aisleFour = new ArrayList<Integer>();
		ArrayList<Integer> aisleFive = new ArrayList<Integer>();

		
/************	assign products to an aisle based on productID them to an ArrayList********************************/
 		for(int s = 0; s < Shelves.size(); s++){
 			if(Shelves.get(s).getProductID() <= 5){
			aisleOne.add(Shelves.get(s).getProductID());
			}
 			
 			else if(Shelves.get(s).getProductID() <= 10){
 				aisleTwo.add(Shelves.get(s).getProductID());		
			}
 	
 			else if(Shelves.get(s).getProductID() <= 15){
 				aisleThree.add(Shelves.get(s).getProductID());
			}
 			else if(Shelves.get(s).getProductID() <= 20){
 				aisleFour.add(Shelves.get(s).getProductID());
			}
 			else if(Shelves.get(s).getProductID() <= 25){
 				aisleFive.add(Shelves.get(s).getProductID());
			}
	
 		}
 		
 		/***********************	sort products in their aisles based on productID them to an ArrayList***************/
 		Collections.sort(aisleOne);
 		Collections.sort(aisleTwo);
 		Collections.sort(aisleThree);
 		Collections.sort(aisleFour);
 		Collections.sort(aisleFive);
 		int aisleOneModulus = (aisleOne.get(aisleOne.size() - 1) % 5);
 		int aisleTwoModulus = (aisleOne.get(aisleOne.size() - 1) % 5);
 		int aisleThreeModulus = (aisleOne.get(aisleOne.size() - 1) % 5);
 		int aisleFourModulus = (aisleOne.get(aisleOne.size() - 1) % 5);
 		int aisleFiveModulus = (aisleOne.get(aisleOne.size() - 1) % 5);
 		
/***********************	Mapping if there are products in the first aisle*********************************************/	
 		if(aisleOne.size()>0){

 				for(int i = 0; i < aisleOne.size(); i++){
 					mapRoute.add(aisleOne.get(i));
 				}
 	/***********************	Mapping if there are products in the first and second aisle**********************************************************************/	
 				if(aisleTwo.size() > 0){
 					
 						if (((aisleOneModulus + aisleTwoModulus) > 6) || aisleOneModulus == 0 ){
 							Collections.sort(aisleTwo, Collections.reverseOrder());
 							for(int i = 0; i < aisleTwo.size(); i++){
 		 						mapRoute.add(aisleTwo.get(i));
 		 					}
 						}
              		else{
                				for(int i = 0; i < aisleTwo.size(); i++){
 		 							mapRoute.add(aisleTwo.get(i));
 		 							}
            			 }
            
/***********************	Mapping if there are products in the first, second and third second aisle***************************************************/	
              		if(aisleThree.size() > 0){
                    if (((aisleTwoModulus + aisleThreeModulus) > 6) || aisleTwoModulus == 0 ){
 								Collections.sort(aisleThree, Collections.reverseOrder());
 								for(int i = 0; i < aisleThree.size(); i++){
 		 							mapRoute.add(aisleThree.get(i));
 		 						}
 							}
              			else{
                				for(int i = 0; i < aisleThree.size(); i++){
 		 							mapRoute.add(aisleThree.get(i));
 		 							}
            			 }
/***********************	Mapping if there are products in the first, second, third and fourth aisle***************************************************/	                    
                   	if(aisleFour.size() > 0){
                        if (((aisleThreeModulus + aisleFourModulus) > 6) || aisleThreeModulus == 0 ){
 									Collections.sort(aisleFour, Collections.reverseOrder());
 									for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
 								}	
              				else{
                				for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
            			 	}
                       
/***********************	Mapping if there are products in the first, second, third, fourth and fifth aisle***************************************************/	                                           
                      		if(aisleFive.size() > 0){
                             	if (((aisleFourModulus + aisleFiveModulus) > 6)|| aisleFourModulus == 0 ){
 											Collections.sort(aisleFive, Collections.reverseOrder());
 											for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 									}
 										}	
              						else{
                						for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 									}
            			 			}
                             
                           
                           	return mapRoute;
                      		}
/*********Mapping if there are products in the first, second, third, and fourth but NOT fifth aisle***************************************************/	                                        
                       
                       		else{
                             	return mapRoute;
                           }
                     }
                    
/**********************   Complete mapping for sunny day path***************************************************/	                                        
                    
                    
                    
                    
/***********************	Mapping if there are products in the first, second, third but NOT fourth aisle***************************************************/	                                        
                  	else if(aisleFive.size() > 0){
                    			if (((aisleThreeModulus + aisleFiveModulus) > 6) || aisleThreeModulus == 0 ){
 											Collections.sort(aisleFive, Collections.reverseOrder());
 											for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 									}
 								}	
              						
                       		else{
                					for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 								}
                       		}	
 							return mapRoute;
 							}
                  }
              
             		
 /***********************	Mapping if there are no products in third but yes to the first, second and fourth**************************************************/	
             
              		else if(aisleFour.size() > 0){
                   		if (((aisleTwoModulus + aisleFourModulus) > 6) || aisleTwoModulus == 0 ){
 									Collections.sort(aisleFour, Collections.reverseOrder());
 									for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
 								}
              				else{
                				for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
            			 	}               
 /***********************	Mapping if there are no products in third but yes to the first, second, fourth and fifth**************************************************/	              
                  	if(aisleFive.size() > 0){
                       		if (((aisleFourModulus + aisleFiveModulus) > 6) || aisleFourModulus == 0 ){
 									Collections.sort(aisleFive, Collections.reverseOrder());
 										for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 								}
 									}
              					else{
                						for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 								}
            					}
                       		return mapRoute;
                     }
 /*******	Mapping if there are no products in third or fifth, but yes to the first, second, fourth******************/	                    
                    	else{
                       		return mapRoute;
                     }
 						}
              
 /********	Mapping if there are no products in third or fourth but yes to the first, second, and fifth*************/	
              
 						else if(aisleFive.size() > 0){
                    		if (((aisleTwoModulus + aisleFiveModulus) > 6) || aisleTwoModulus == 0 ){
 									Collections.sort(aisleFive, Collections.reverseOrder());
 									for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 							}
 								}
              				else{
                				for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 							}
            			 	}              
 								return mapRoute;
 						}
 /********	Mapping if there are no products in third, fourth or fifth but yes to the first, and second*************/	
              		else{
                    		return mapRoute;
                  }
/***********************	End of first and second aisle case**********************************************************************/	  
            }
        
/***********************	Mapping if there are no products second but yes to the first and third aisle**********************************************************************/	
 				else if(aisleThree.size() > 0){
 						if (((aisleOneModulus + aisleThreeModulus) > 6) || aisleOneModulus == 0 ){
 							Collections.sort(aisleThree, Collections.reverseOrder());
 							for(int i = 0; i < aisleThree.size(); i++){
 		 						mapRoute.add(aisleThree.get(i));
 		 					}
 						}
              		else{
                				for(int i = 0; i < aisleThree.size(); i++){
 		 							mapRoute.add(aisleThree.get(i));
 		 							}
            			 }
/******Mapping if there are no products second but yes to the first, third and fourth aisle**********************/	
              
              			if(aisleFour.size() > 0){
                       if (((aisleThreeModulus + aisleFourModulus) > 6) || aisleThreeModulus == 0 ){
 									Collections.sort(aisleFour, Collections.reverseOrder());
 									for(int i = 0; i < aisleFour.size(); i++){
 		 								mapRoute.add(aisleFour.get(i));
 		 							}
 								}
              				else{
                				for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
            			 	}
                       
/******Mapping if there are no products second but yes to the first, third, fourth  and fifth aisle**********************/	
                     
                       if(aisleFive.size()>0){
                         	if (((aisleFourModulus + aisleFiveModulus) > 6)|| aisleFourModulus == 0 ){
 										Collections.sort(aisleFive, Collections.reverseOrder());
 										for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 								}
 									}
              					else{
                					for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 								}
            			 		}
                         		return mapRoute;
								}
/******Mapping if there are no products in second or fifth but yes to the first, third, fourth  and fifth aisle**********************/	
                       
                       else{
                         		return mapRoute;
                       }
                       	
                     }
/******Mapping if there are no products second or fourth but yes to the first, third and fourth aisle**********************/	
              
              			else if(aisleFive.size()>0){
                       	if (((aisleThreeModulus + aisleFiveModulus) > 6)|| aisleThreeModulus == 0 ){
 									Collections.sort(aisleFive, Collections.reverseOrder());
 									for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 							}
 								}
              				else{
                				for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 							}
            			 	}
                       return mapRoute;             
                     }
/******Mapping if there are no products second,fourth or fifth but yes to the first, third and fourth aisle**********************/	
              
              			else{
                       return mapRoute;
                     }
 				}
/**************************************	End of first and third  aisle case****************************************/	
        
/***Mapping if there are no products second or third but yes to the first and fourth aisle**********************************************************************/	        
 				else if(aisleFour.size() > 0){
 						if (((aisleOneModulus + aisleFourModulus) > 6)|| aisleOneModulus == 0 ){
 							Collections.sort(aisleFour, Collections.reverseOrder());
 							for(int i = 0; i < aisleFour.size(); i++){
 		 						mapRoute.add(aisleFour.get(i));
 		 					}
 						}
              		else{
                				for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
            			 }
/***Mapping if there are no products second or third but yes to the first and fourth and fifth aisle**********************************************************************/	                      
           		if(aisleFive.size() > 0){
                 	if (((aisleFourModulus + aisleFiveModulus) > 6) || aisleFourModulus == 0 ){
 							Collections.sort(aisleFive, Collections.reverseOrder());
 							for(int i = 0; i < aisleFive.size(); i++){
 		 						mapRoute.add(aisleFive.get(i));
 		 					}
 						}
              		else{
                				for(int i = 0; i < aisleFive.size(); i++){
 		 							mapRoute.add(aisleFive.get(i));
 		 							}
            			 }
                 		return mapRoute;
               }
/***Mapping if there are no products second, third or fifth but yes to the first and fourth aisle**********************************************************************/	                      
              else{
                		return mapRoute;
              }
 				}
/***Mapping if there are no products second, third or fourth but in yes to the first and fifth aisle**********************************************************************/	        
        
 				else if(aisleFive.size() > 0){
           			if (((aisleOneModulus + aisleFiveModulus) > 6)|| aisleOneModulus == 0 ){
 							Collections.sort(aisleFive, Collections.reverseOrder());
 							for(int i = 0; i < aisleFive.size(); i++){
 		 						mapRoute.add(aisleFive.get(i));
 		 					}
 						}
              		else{
                				for(int i = 0; i < aisleFive.size(); i++){
 		 							mapRoute.add(aisleFive.get(i));
 		 							}
            			 }   
 						return mapRoute;
 				}
/***Mapping if there are no products second, third, fourth, or fifth but in yes to the first aisle**********************************************************************/	                
        		else{
              		return mapRoute;
            }
   	}
        
/***********************	End of first aisle case**********************************************************************/	
        
        
        
        
 /***********************	Mapping if products start in second aisle****************************************************/	
 		else if(aisleTwo.size() > 0){		
                				for(int i = 0; i < aisleTwo.size(); i++){
 		 							mapRoute.add(aisleTwo.get(i));
 		 							}
            
/***********************	Mapping if there are products in the second and third second aisle***************************************************/	
              		if(aisleThree.size() > 0){
                    if (((aisleTwoModulus + aisleThreeModulus) > 6)|| aisleTwoModulus == 0 ){
 								Collections.sort(aisleThree, Collections.reverseOrder());
 								for(int i = 0; i < aisleThree.size(); i++){
 		 							mapRoute.add(aisleThree.get(i));
 		 						}
 							}
              			else{
                				for(int i = 0; i < aisleTwo.size(); i++){
 		 							mapRoute.add(aisleThree.get(i));
 		 							}
            			 }
/***********************	Mapping if there are products in the second, third and fourth aisle***************************************************/	                    
                   	if(aisleFour.size() > 0){
                        if (((aisleThreeModulus + aisleFourModulus) > 6)|| aisleThreeModulus == 0 ){
 									Collections.sort(aisleFour, Collections.reverseOrder());
 									for(int i = 0; i < aisleFour.size(); i++){
 		 								mapRoute.add(aisleFour.get(i));
 		 							}
 								}	
              				else{
                				for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
            			 	}                       
/***********************	Mapping if there are products in the second, third, fourth and fifth aisle***************************************************/	                                           
                      		if(aisleFive.size() > 0){
                             	if (((aisleFourModulus + aisleFiveModulus) > 6) || aisleFourModulus == 0 ){
 											Collections.sort(aisleFive, Collections.reverseOrder());
 											for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 									}
 										}	
              						else{
                						for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 									}
            			 			}                     
                           	return mapRoute;
                      		}
/*********Mapping if there are products in the second, third, and fourth but NOT fifth aisle***************************************************/	                                        
                       
                       		else{
                             	return mapRoute;
                           }
                     }
                    
/**********************   Complete mapping for sunny day starting at aisle 2***************************************************/	                                        
                    
                                       
/**************Mapping if there are products in the second, third and fifth but NOT fourth aisle***************************************************/	                                        
                  	else if(aisleFive.size() > 0){
                    			if (((aisleThreeModulus + aisleFiveModulus) > 6) || aisleThreeModulus == 0) {
 											Collections.sort(aisleFive, Collections.reverseOrder());
 											for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 									}
 								}	
              						
                       		else{
                					for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 								}
                       		}	
 							return mapRoute;
 							}
/*******Mapping if there are products in the second and third aisles but NOT fourth and fifth aisle********************/	                                                            
                    else{
                      return mapRoute;
                    }
                  }
                          		
 /***********************	Mapping if there are no products in third but yes to the second and fourth**************************************************/	
             
              		else if(aisleFour.size() > 0){
                   		if (((aisleTwoModulus + aisleFourModulus) > 6) || aisleTwoModulus == 0 ){
 									Collections.sort(aisleFour, Collections.reverseOrder());
 									for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
 								}
              				else{
                				for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
            			 	}               
 /***********************	Mapping if there are no products in third but yes to the second, fourth and fifth**************************************************/	              
                  	if(aisleFive.size() > 0){
                       		if (((aisleFourModulus + aisleFiveModulus) > 6) || aisleFourModulus == 0 ){
 									Collections.sort(aisleFive, Collections.reverseOrder());
 										for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 								}
 									}
              					else{
                						for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 								}
            					}
                       		return mapRoute;
                     }
 /*******	Mapping if there are no products in third or fifth, but yes to the first, second, fourth******************/	                    
                    	else{
                       		return mapRoute;
                     }
 						}
              
 /********	Mapping if there are no products in third or fourth but yes to the first, second, and fifth*************/	
              
 						else if(aisleFive.size() > 0){
                    		if (((aisleTwoModulus + aisleFiveModulus) > 6) || aisleTwoModulus == 0 ){
 									Collections.sort(aisleFive, Collections.reverseOrder());
 									for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 							}
 								}
              				else{
                				for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 							}
            			 	}              
 								return mapRoute;
 						}
 /********	Mapping if there are no products in third, fourth or fifth but yes to the first, and second*************/	
              		else{
                    		return mapRoute;
                  }
     }
 /***********************	End second aisle start case****************************************************************/	  
       
     
     
     
/***********************	Mapping if products start in third aisle****************************************************/	
 				else if(aisleThree.size() > 0){
                   for(int i = 0; i < aisleThree.size(); i++){
 		 							mapRoute.add(aisleThree.get(i));
 		 				}
            			             
              		if(aisleFour.size() > 0){
                       if (((aisleThreeModulus + aisleFourModulus) > 6) || aisleThreeModulus == 0 ){
 									Collections.sort(aisleFour, Collections.reverseOrder());
 									for(int i = 0; i < aisleFour.size(); i++){
 		 								mapRoute.add(aisleFour.get(i));
 		 							}
 								}
              				else{
                				for(int i = 0; i < aisleFour.size(); i++){
 		 							mapRoute.add(aisleFour.get(i));
 		 							}
            			 	}
                       
/******Mapping if there are products in third, fourth  and fifth aisle**********************/	
                     
                       if(aisleFive.size()>0){
                         	if (((aisleFourModulus + aisleFiveModulus) > 6) || aisleFourModulus == 0 ){
 										Collections.sort(aisleFive, Collections.reverseOrder());
 										for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 								}
 									}
              					else{
                					for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 								}
            			 		}
                         		return mapRoute;
								}
/******Mapping if there are no products in fifth but yes to the third, fourth  and fifth aisle****************/	
                       
                       else{
                         		return mapRoute;
                       }
                       	
                  }
/******Mapping if there are no products fourth but yes to the third and fifth aisle**********************/	              
              		else if(aisleFive.size()>0){
                  	     	if (((aisleThreeModulus + aisleFiveModulus) > 6) || aisleThreeModulus == 0 ){
 										Collections.sort(aisleFive, Collections.reverseOrder());
 										for(int i = 0; i < aisleFive.size(); i++){
 		 									mapRoute.add(aisleFive.get(i));
 		 								}
 									}
              				else{
                				for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 							}
            			 	}
                       return mapRoute;             
                     }
/******Mapping if there are no products fourth or fifth but yes to the third   aisle**********************/	   
              			else{
                       return mapRoute;
                     }
 				}
/**************************************	End of third  aisle case****************************************/	
        
     
     
     
/***Mapping if products start in fourth aisle**********************************************************************/	        
 				else if(aisleFour.size() > 0){
 						for(int i = 0; i < aisleFour.size(); i++){
 		 						mapRoute.add(aisleFour.get(i));
 		 				}
/***Mapping if there are products in fourth and fifth aisle*******************************************************/	                      
           		if(aisleFive.size() > 0){
                 	if (((aisleFourModulus + aisleFiveModulus) > 6) || aisleFourModulus == 0 ){
 							Collections.sort(aisleFive, Collections.reverseOrder());
 							for(int i = 0; i < aisleFive.size(); i++){
 		 						mapRoute.add(aisleFive.get(i));
 		 					}
 						}                 
              		else{
                			for(int i = 0; i < aisleFive.size(); i++){
 		 							mapRoute.add(aisleFive.get(i));
 		 						}
            			 }
                 		return mapRoute;
               }
/***Mapping if there are only products in fourth aisle**************************************************/	                      
              else{
                		return mapRoute;
              }
 				}
/***Mapping if there are only products fifth aisle*******/	        
        
 				else if(aisleFive.size() > 0){
                				for(int i = 0; i < aisleFive.size(); i++){
 		 								mapRoute.add(aisleFive.get(i));
 		 							}
            			 }  
     
 			return mapRoute;
 		}

     
 /***********************	End of second aisle start cases*********************************************/	


}
