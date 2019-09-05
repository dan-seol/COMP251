package inventory_;

import java.util.HashMap;

public class Clerk {
	public Inventory inventory=new Inventory();
	public HashMap<String, Integer> backOrder = new HashMap<String, Integer>();
	
	public Clerk() {
		
		System.out.println("May I help you?");
	}
	
	public void processOrder(Product product, int amount) {
		String id = product.getID();
		if(this.inventory.getContainer().containsKey(id)) {
			System.out.println("Additional order for " + product);
		} else {
			System.out.println("A new order for " + product);
		}
			this.inventory.putProduct(product, amount);
			
			int balance = (-1)*this.inventory.getContainer().get(id);
			if(balance>0) {
				System.out.println(product + " is out of stock at the moment. Will add this order to backorder");
				if(backOrder.containsKey(id)) {
					backOrder.replace(id, backOrder.get(id)+balance);
				} else {
					backOrder.put(id, balance);
				}
				System.out.println(product + " is now placed at the backorder with amount" + backOrder.get(id));
			}
			System.out.println(product + " now has a stock of" + this.inventory.getContainer().get(id));

	}
	
	public Product retrieveProduct(String id) {
			Product result = new Product("", "", 0);
			try{
				result =  this.inventory.getProduct(id);
				return result;
			}
			catch(InventoryException e){
				System.out.println(e.getMessage());
				return result;
			}
		
			
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product notebook = new Product("nb001", "notebooks", 2.43);
		Clerk James = new Clerk();
		James.processOrder(notebook, 100);
		James.processOrder(notebook, -120);
		James.retrieveProduct(notebook.getID());
		James.retrieveProduct(new Product("pc002", "pencils", 0.99).getID());

	}

}
