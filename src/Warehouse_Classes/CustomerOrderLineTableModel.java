package Warehouse_Classes;
import Warehouse_Classes.CustomerOrderLine;

import java.util.List;

import javax.swing.table.AbstractTableModel;

/*********Class to create GUI table structure for to display customer order lines for account creation***********/

class CustomerOrderLineTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
  	private static final int ORDER_ID_COL = 0;
	private static final int PRODUCT_ID_COL = 1;
	private static final int QUANTITY_COL = 2;
	private static final int PRODUCT_STATUS_CODE_COL = 3;
	private static final int POROUS_COUNT_COL = 4;
	private static final int HANDLER_ID_COL = 5;
  	private static final int ORDER_STATUS_CODE_COL = 6;
 
  	private int orderStatusCode;
  	
/******************************Decide names for customer order lines GUI table columns **************************/

	public static String[] columnNames = { "customerOrderID",  "productID", "quantity", "productStatusCode", "porousCount", "handlerID", "orderStatusCode" };
	private List<CustomerOrderLine> customerOrderLines;
  
  
/******************************Decide names for customer order lines GUI table columns **************************/
	public CustomerOrderLineTableModel(List<CustomerOrderLine> theCustomerOrderLines) {
		customerOrderLines = theCustomerOrderLines;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return customerOrderLines.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
/**********************put values into customer order lines GUI table columns by column *****************/

	@Override
	public Object getValueAt(int row, int col) {

		CustomerOrderLine tempCustomerOrderLine = customerOrderLines.get(row);

		switch (col) {
      case ORDER_ID_COL:
        	return tempCustomerOrderLine.getCustomerOrderID();
		case PRODUCT_ID_COL:
			return tempCustomerOrderLine.getProductID();
		case QUANTITY_COL:
			return tempCustomerOrderLine.getQuantity();
		case PRODUCT_STATUS_CODE_COL:
			return tempCustomerOrderLine.getProductStatusCode();
		case POROUS_COUNT_COL:
			return tempCustomerOrderLine.getPorousCount();
		case HANDLER_ID_COL:
			return tempCustomerOrderLine.getHandlerID();
        case ORDER_STATUS_CODE_COL:
			return tempCustomerOrderLine.getOrderStatusCode();
		default:
			return tempCustomerOrderLine.getCustomerOrderID();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}