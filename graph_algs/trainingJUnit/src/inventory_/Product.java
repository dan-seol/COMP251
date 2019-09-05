package inventory_;

public class Product {
private String ID;
private String productName;
private double productPrice;
public String getID() {
	return ID;
}

public void setID(String id) {
	ID = id;
}

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

@Override
public String toString() {
	return "Product [ID=" + ID + ", productName=" + productName + ", productPrice=" + productPrice + "]";
}

public double getProductPrice() {
	return productPrice;
}

public void setProductPrice(double productPrice) {
	this.productPrice = productPrice;
}



public Product(String id, String name, double price) {
	this.ID=id;
	this.productName=name;
	this.productPrice=price;
} 
}
