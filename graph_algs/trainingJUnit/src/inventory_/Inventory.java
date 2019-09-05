package inventory_;
import java.util.*;
public class Inventory {
private HashMap<String, Integer> container = new HashMap<String, Integer>();
private HashMap<String, Product> catalogue = new HashMap<String, Product>();



public HashMap<String, Integer> getContainer() {
	return container;
}

public void setContainer(HashMap<String, Integer> container) {
	this.container = container;
}

public HashMap<String, Product> getCatalogue() {
	return catalogue;
}

public void setCatalogue(HashMap<String, Product> catalogue) {
	this.catalogue = catalogue;
}

public void putProduct(Product product, int amount) {
	String id = product.getID();
	if(container.containsKey(id)) {
		container.replace(id, amount+container.get(id));
		System.out.println("You have successfully added Product + " + this.getCatalogue().get(id) );

	} else {
		container.put(id, amount);
		catalogue.put(id, product);
		System.out.println("You have successfully put Product + " + this.getCatalogue().get(id) );

	}
}

public void putProduct(String id, String name, double price, int amount) {
	this.putProduct(new Product(id, name, price), amount);
	System.out.println("You have successfully put Product + " + this.getCatalogue().get(id) );
}

public Product getProduct(String id) throws InventoryException {
	
	if(!container.containsKey(id)) {
		String msg = "You are finding Product " +  this.getCatalogue().get(id) + ", which does not exist";
		throw new InventoryException(msg);
	}
	return catalogue.get(id);

}

public void removeProduct(String id) throws InventoryException {
	if(!container.containsKey(id)) {
		String msg = "You are deleting Product  " + this.getCatalogue().get(id) + ", which does not exist";
		throw new InventoryException(msg);
	}
	container.remove(id);
	catalogue.remove(id);
	
}



}
