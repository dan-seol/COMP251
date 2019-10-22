import java.math.BigDecimal;
public class PersonalCustomer extends Customer {
private final CustomerType type = CustomerType.Personal;

public CustomerType getType() {
	return type;
}

private PersonalCustomer(String pw) {
	super(pw);
}

private PersonalCustomer(String pw, BigDecimal chqBal, BigDecimal savBal) throws BankAccountException {
	super(pw, chqBal, savBal);
}

public static PersonalCustomer create(String pw) {
	return new PersonalCustomer(pw);
}

public static PersonalCustomer create(String pw, BigDecimal chqBal, BigDecimal savBal) throws BankAccountException {
	return new PersonalCustomer(pw, chqBal, savBal);
}

public static PersonalCustomer create(String pw, long chqBal, long savBal) throws BankAccountException {
	return new PersonalCustomer(pw, BigDecimal.valueOf(chqBal), BigDecimal.valueOf(savBal));
}

public static PersonalCustomer create(String pw, double chqBal, double savBal) throws BankAccountException {
	return new PersonalCustomer(pw, BigDecimal.valueOf(chqBal), BigDecimal.valueOf(savBal));
}


}
