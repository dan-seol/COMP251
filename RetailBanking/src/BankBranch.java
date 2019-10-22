import java.math.BigDecimal;
import java.util.*;
public class BankBranch {
private Map<String,Customer> clientList = new HashMap<String,Customer>();
private Map<String, String> passwordBook = new HashMap<String, String>(); 
public Map<String, String> getPasswordBook() {
	return passwordBook;
}
public Map<String, Customer> getClientList() {
	return clientList;
}

private String branchName;
private String bankName;

public String getBranchName() {
	return branchName;
}
public void setBranchName(String branchName) {
	this.branchName = branchName;
}
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}

private BankBranch(){
	
}

private BankBranch(String branchName, String bankName){
	this.branchName = branchName;
	this.bankName = bankName;
}

public static BankBranch create() {
	return new BankBranch();
}

public static BankBranch create(String branchName, String bankName) {
	return new BankBranch(branchName, bankName);
	
}

public void newPersonalCustomer(String pw) {
	PersonalCustomer customer = PersonalCustomer.create(pw);
	this.getClientList().put(customer.getID(), customer);
	System.out.println("Let's welcome a new personal customer!");
}
public void newPersonalCustomer(String pw, BigDecimal chqBal, BigDecimal savBal) throws BankAccountException {
	PersonalCustomer customer = PersonalCustomer.create(pw, chqBal, savBal);
	this.getClientList().put(customer.getID(), customer);
	System.out.println("Let's welcome a new personal customer!");
}
public void newPersonalCustomer(String pw, long chqBal, long savBal) throws BankAccountException {
	PersonalCustomer customer = PersonalCustomer.create(pw, chqBal, savBal);
	this.getClientList().put(customer.getID(), customer);
	System.out.println("Let's welcome a new personal customer!");
}
public void newPersonalCustomer(String pw, double chqBal, double savBal) throws BankAccountException {
	PersonalCustomer customer = PersonalCustomer.create(pw, chqBal, savBal);
	this.getClientList().put(customer.getID(), customer);
	System.out.println("Let's welcome a new personal customer!");
}
public void newPersonalCustomer(PersonalCustomer customer) {
	this.getClientList().put(customer.getID(), customer);
	this.getPasswordBook().put(customer.getID(), customer.getPassword());
	System.out.println("Let's welcome a new personal customer!");
}

public void newCommercialCustomer(String pw, BigDecimal chqBal, BigDecimal savBal) throws BankAccountException {
	CommercialCustomer customer = CommercialCustomer.create(pw, chqBal, savBal);
	this.getClientList().put(customer.getID(), customer);
	System.out.println("Let's welcome a new commercial customer!");
}
public void newCommercialCustomer(String pw, long chqBal, long savBal) throws BankAccountException {
	CommercialCustomer customer = CommercialCustomer.create(pw, chqBal, savBal);
	this.getClientList().put(customer.getID(), customer);
	System.out.println("Let's welcome a new commercial customer!");
}
public void newCommercialCustomer(String pw, double chqBal, double savBal) throws BankAccountException {
	CommercialCustomer customer = CommercialCustomer.create(pw, chqBal, savBal);
	this.getClientList().put(customer.getID(), customer);
	System.out.println("Let's welcome a new commercial customer!");
}
public void newCommercialCustomer(CommercialCustomer customer) {
	this.getClientList().put(customer.getID(), customer);
	this.getPasswordBook().put(customer.getID(), customer.getPassword());
	System.out.println("Let's welcome a new commercial customer!");
}

public Customer removeCustomer(String id, String pw) throws CustomerException {
	if(this.getPasswordBook().get(id).equals(pw)) {
		Customer acc = this.getClientList().get(id);
		this.getClientList().remove(id);
		return acc;
	} else {
		throw new CustomerException("The id and the password do not match");
	}
}




}
