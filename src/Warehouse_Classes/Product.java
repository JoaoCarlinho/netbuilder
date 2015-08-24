package Warehouse_Classes;

//This is my product class where I set a constructor method for use in GUI*********************************************************************************************************************

public class Product {


	private int productID;
	private String productName;
	private int supplierID;
	private double wholesalePrice;
  	private double retailPrice;
	private int shelvedQuantity;
  	private int reservedQuantity;
  	private int porousAvailable;
  	private int minPorous;
  	private int nonPorousAvailable;
  	private int porousReserved;
  	private int nonPorousReserved;
	
	public Product(int productID, String productName, int supplierID, double wholesalePrice, double retailPrice, int shelvedQuantity, int reservedQuantity, int porousAvailable, int minPorous, 
					int nonPorousAvailable, int porousReserved, int nonPorousReserved) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.supplierID = supplierID;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
     	this.shelvedQuantity = shelvedQuantity;
     	this.reservedQuantity = reservedQuantity;
     	this.porousAvailable = porousAvailable;
     	this.minPorous = minPorous;
      	this.nonPorousAvailable = nonPorousAvailable;
      	this.porousReserved = porousReserved;
      	this.nonPorousReserved = nonPorousReserved;
	}

	public int getproductID() {
		return productID;
	}
  
  public void setproductID(int productID) {
		this.productID = productID;
	}
  
  	public String getproductName() {
		return productName;
	}
  
  	public void setproductName(String productName) {
		this.productName = productName;
	}
  
  	public int getsupplierID() {
		return supplierID;
	}
  
  	public void setsupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
  
  	public double getwholesalePrice() {
		return wholesalePrice;
	}
  
  	public void setwholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
  
  	public double getretailPrice() {
		return retailPrice;
	}
  
  	public void setretailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
  
  	public int getshelvedQuantity() {
		return shelvedQuantity;
   }
	
     
   public void setshelvedQuantity(int shelvedQuantity) {
		this.shelvedQuantity = shelvedQuantity;
	}
  
  	public int getreservedQuantity() {
		return reservedQuantity;
	}
     
   public void setreservedQuantity(int reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}
  
  	public int getporousAvailable() {
		return porousAvailable;
	}
     
   public void setporousAvailable(int porousAvailable) {
		this.porousAvailable = porousAvailable;
	}

   public int getminPorous() {
	   return minPorous;
   }
   
   public void setminPorous(int minPorous) {
	   this.minPorous = minPorous;
   }
   
   public int getnonPorousAvailable() {
	   return nonPorousAvailable;
   }
   
   public void nonPorousAvailable(int nonPorousAvailable) {
	   this.nonPorousAvailable = nonPorousAvailable;
   }
 
   public int getnPorousReserved() {
	   return porousReserved;
   }
   
   public void porousReserved(int porousReserved) {
	   this.porousReserved = porousReserved;
   }
   
   public int nonPorousReserved() {
	   return nonPorousReserved;
   }
   
   public void nonPorousReserved(int nonPorousReserved) {
	   this.nonPorousReserved = nonPorousReserved;
   }
 	
 	
   

	

	@Override
	public String toString() {
		return String
				.format("Product [productID=%s, productName=%s, supplierID=%s, wholesalePrice=%s, retailPrice=%s, shelvedQuantity=%s,reservedQuantity=%s, porousAvailable=%s, minPorous=%s, nonPorousAvailable=%s, porousReserved=%s, nonPorousReserved=%s ]",
						productID, productName, supplierID, wholesalePrice, retailPrice, shelvedQuantity, reservedQuantity, porousAvailable, minPorous, nonPorousAvailable, porousReserved, nonPorousReserved);
	}
	
	
		
}
