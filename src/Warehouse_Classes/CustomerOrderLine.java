package Warehouse_Classes;

//This is my orderLine class for use in 	q`creation of array lists for populating GUI tables*********************************************************************************************************************

public class CustomerOrderLine {

	/***************************************************************Declare class variables*****************************/

	private int customerOrderID;
	private int productID;
	private int quantity;
	private int productStatusCode;
  	private int porousCount;
	private int handlerID;
  	private int orderStatusCode;
  	
  
  
  	/****************************************************Empty constructor for use when creating new products*****************************/

  	public CustomerOrderLine() {
	}
  	
  
  	/******************************************************Initialize instance variables in constructor*****************************/

	public CustomerOrderLine( int customerOrderID,  int productID, int quantity, int productStatusCode, int porousCount,int handlerID, int orderStatusCode) {
		super();
		this.customerOrderID = customerOrderID;
		this.productID = productID;
     	this.quantity = quantity;
     	this.productStatusCode = productStatusCode;
     	this.porousCount = porousCount;
     	this.handlerID = handlerID;
     	this.orderStatusCode = orderStatusCode;
      	
	}
  
  
  	/*****************************************************Getter and Setter methods*****************************/

  
  public int getCustomerOrderID() {
		return customerOrderID;
	}
  
  	public void setCustomerOrderID(int customerOrderID) {
		this.customerOrderID = customerOrderID;
	}

	public int getProductID() {
		return productID;
	}
  
  public void setProductID(int productID) {
		this.productID = productID;
	}
  
  	public int getQuantity() {
		return quantity;
   }
	
     
   public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
  
  	public int getProductStatusCode() {
		return productStatusCode;
	}
     
   public void setProductStatusCode(int productStatusCode) {
		this.productStatusCode = productStatusCode;
	}
  
  	public int getPorousCount() {
		return porousCount;
	}
     
   public void setPorousCount(int porousCount) {
		this.porousCount = porousCount;
	}

   public int getHandlerID() {
	   return handlerID;
   }
   
   public void setHandlerID(int handlerID) {
	   this.handlerID = handlerID;
   }
   
   public int getOrderStatusCode() {
	   return orderStatusCode;
   }
   
   public void setOrderStatusCode(int orderStatusCode) {
	   this.orderStatusCode = orderStatusCode;
   }
 

  
  
	

	@Override
	public String toString() {
		return String
				.format("CustomerOrderLine [customerOrderID=%s,  productID=%s, quantity=%s, productStatusCode=%s, porousCount=%s, handlerID=%s, orderStatusCode=%s ]",
						customerOrderID,  productID, quantity, productStatusCode, porousCount, handlerID, orderStatusCode);
	}	
	
	
		
}