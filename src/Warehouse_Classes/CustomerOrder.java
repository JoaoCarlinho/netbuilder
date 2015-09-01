package Warehouse_Classes;

public class CustomerOrder {

	private int CustomerOrderID;
	private int handlerID;
  	
  	public CustomerOrder(){};
	public CustomerOrder(int CustomerOrderID, int handlerID) {
		super();
		
		this.CustomerOrderID = CustomerOrderID;
		this.handlerID = handlerID;

	}

	public int getCustomerOrderID() {
		return CustomerOrderID;
	}
  
  public void setCustomerOrderID(int CustomerOrderID) {
		this.CustomerOrderID = CustomerOrderID;
	}
  
  	public int gethandlerID() {
		return handlerID;
	}
  
  	public void sethandlerID(int handlerID) {
		this.handlerID = handlerID;
	}
 	
	@Override
	public String toString() {
		return String
				.format("CustomerOrder [CustomerOrderID=%s, handlerID=%s]",
						CustomerOrderID, handlerID);
	}	
}
