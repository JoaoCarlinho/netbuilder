package Warehouse_Classes;

//This is my product class where I set a constructor method for use in GUI*********************************************************************************************************************

public class Product {

	private int productID;
	private String productName;
	private int shelvedQuantity;
  	private int reservedQuantity;
  	private int porousAvailable;
  	private int minPorous;
  	private int nonPorousAvailable;
  	private int porousReserved;
  	private int nonPorousReserved;
  	
  	public Product(){};
	public Product(int productID, String productName,  int shelvedQuantity, int reservedQuantity,  int porousAvailable, int nonPorousAvailable) {
		super();
		
		this.productID = productID;
		this.productName = productName;
     	this.shelvedQuantity = shelvedQuantity;
     	this.reservedQuantity = reservedQuantity;
     	this.porousAvailable = porousAvailable;
      	this.nonPorousAvailable = nonPorousAvailable;
	}

	public int getProductID() {
		return productID;
	}
  
  public void setProductID(int productID) {
		this.productID = productID;
	}
  
  	public String getProductName() {
		return productName;
	}
  
  	public void setProductName(String productName) {
		this.productName = productName;
	}
  
  	public int getShelvedQuantity() {
		return shelvedQuantity;
   }
	
   public void setShelvedQuantity(int shelvedQuantity) {
		this.shelvedQuantity = shelvedQuantity;
	}
  
  	public int getReservedQuantity() {
		return reservedQuantity;
	}
     
   public void setReservedQuantity(int reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}
  
  	public int getPorousAvailable() {
		return porousAvailable;
	}
     
   public void setPorousAvailable(int porousAvailable) {
		this.porousAvailable = porousAvailable;
	}

   public int getMinPorous() {
	   return minPorous;
   }
   
   public void setMinPorous(int minPorous) {
	   this.minPorous = minPorous;
   }
   
   public int getNonPorousAvailable() {
	   return nonPorousAvailable;
   }
   
   public void setNonPorousAvailable(int nonPorousAvailable) {
	   this.nonPorousAvailable = nonPorousAvailable;
   }
 
   public int getPorousReserved() {
	   return porousReserved;
   }
   
   public void setPorousReserved(int porousReserved) {
	   this.porousReserved = porousReserved;
   }
   
   public int getNonPorousReserved() {
	   return nonPorousReserved;
   }
   
   public void setNonPorousReserved(int nonPorousReserved) {
	   this.nonPorousReserved = nonPorousReserved;
   }
 	
	@Override
	public String toString() {
		return String
				.format("Product [productID=%s, productName=%s, shelvedQuantity=%s,reservedQuantity=%s, porousAvailable=%s, minPorous=%s, nonPorousAvailable=%s, porousReserved=%s, nonPorousReserved=%s ]",
						productID, productName, shelvedQuantity, reservedQuantity, porousAvailable, minPorous, nonPorousAvailable, porousReserved, nonPorousReserved);
	}		
		
}
