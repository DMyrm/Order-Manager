package model;

public class Order
{
	private int OrderID;
	private String ClientName;
	private String ProductName;
	private int ProductAmount;
	private float OrderPrice;

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public String getClientName() {
		return ClientName;
	}

	public void setClientName(String clientName) {
		ClientName = clientName;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public int getProductAmount() {
		return ProductAmount;
	}

	public void setProductAmount(int productAmount) {
		ProductAmount = productAmount;
	}

	public float getOrderPrice() {
		return OrderPrice;
	}

	public void setOrderPrice(float orderPrice) {
		OrderPrice = orderPrice;
	}
}
