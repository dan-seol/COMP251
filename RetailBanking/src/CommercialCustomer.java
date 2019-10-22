import java.math.BigDecimal;

public class CommercialCustomer extends Customer {
	private final CustomerType type = CustomerType.Commercial;
	public static final double minBal = 10000;
	public CustomerType getType() {
		return type;
	}
	
	private CommercialCustomer(String pw, BigDecimal chqBal, BigDecimal savBal) throws BankAccountException {
		super(pw,chqBal,savBal);
		
	}

	public static CommercialCustomer create(String pw, BigDecimal chqBal, BigDecimal savBal) throws BankAccountException {
		if(chqBal.compareTo(BigDecimal.valueOf(minBal))<0&&savBal.compareTo(BigDecimal.valueOf(minBal))<0) {
			throw new BankAccountException("Minimum balance required : $10,000");
		}
		return new CommercialCustomer(pw, chqBal, savBal);
	}

	public static CommercialCustomer create(String pw, long chqBal, long savBal) throws BankAccountException {
		if(chqBal<minBal&&savBal<minBal) {
			throw new BankAccountException("Minimum balance required : $10,000");
		}
		return new CommercialCustomer(pw, BigDecimal.valueOf(chqBal), BigDecimal.valueOf(savBal));
	}

	public static CommercialCustomer create(String pw, double chqBal, double savBal) throws BankAccountException {
		if(chqBal<minBal&&savBal<minBal) {
			throw new BankAccountException("Minimum balance required : $10,000");
		}
		return new CommercialCustomer(pw, BigDecimal.valueOf(chqBal), BigDecimal.valueOf(savBal));
	}
}
