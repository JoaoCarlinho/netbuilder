package Warehouse_Classes;

import java.util.List;

import javax.swing.table.AbstractTableModel;


class ProductTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int PRODUCT_ID_COL = 0;
	private static final int PRODUCT_NAME_COL = 1;
	private static final int SHELVED_QUANTITY_COL = 2;
	private static final int RESERVED_QUANTITY_COL = 3;
	private static final int POROUS_AVAILABLE_COL = 4;
	private static final int NONPOROUS_AVAILABLE_COL = 5;

	public static String[] columnNames = { "Product ID", "Product Name", "Shelved Quantity", "reserved Quantity", "porousAvailable", "nonPorousAvailable" };
	private List<Product> products;

	public ProductTableModel(List<Product> theProducts) {
		products = theProducts;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return products.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Product tempProduct = products.get(row);

		switch (col) {
		
		case PRODUCT_ID_COL:
			return tempProduct.getProductID();
		case PRODUCT_NAME_COL:
			return tempProduct.getProductName();
		case SHELVED_QUANTITY_COL:
			return tempProduct.getShelvedQuantity();
		case RESERVED_QUANTITY_COL:
			return tempProduct.getReservedQuantity();
		case POROUS_AVAILABLE_COL:
			return tempProduct.getPorousAvailable();
		case NONPOROUS_AVAILABLE_COL:
			return tempProduct.getNonPorousAvailable();
		default:
			return tempProduct.getProductName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
