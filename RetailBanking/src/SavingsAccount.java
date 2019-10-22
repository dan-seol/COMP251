import java.math.BigDecimal;
import java.util.Date;

public class SavingsAccount extends Account {
    public final AccountType type = AccountType.SAVINGS;
    protected double interestRate = 0.03;
    public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
    	System.out.println("The interest rate for account " +this.getAccountNumber() +" is set to"+interestRate);

	}
	private SavingsAccount(String owner, double balance, int number) {
        super(owner, balance, number);
    	System.out.println("A new savings account under "+ owner +" was created!");

    }
    private SavingsAccount(String owner, long balance, int number) {
        super(owner, balance, number);
    	System.out.println("A new savings account under "+ owner +" was created!");

    }
    private SavingsAccount(String owner, BigDecimal balance, int number) {
        super(owner, balance, number);
    	System.out.println("A new savings account under "+ owner +" was created!");

    }
    public static SavingsAccount create(String owner, BigDecimal balance, int number){
        SavingsAccount account = new SavingsAccount(owner, balance, number);
        return account;
    }
    public static SavingsAccount create(String owner, double balance, int number){
        SavingsAccount account = new SavingsAccount(owner, balance, number);
        return account;
    }
    public static SavingsAccount create(String owner, long balance, int number){
        SavingsAccount account = new SavingsAccount(owner, balance, number);
        return account;
    }
    @Override
    protected void withdraw(BigDecimal amount) throws BankAccountException{
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal updatedBalance = this.getBalance().subtract(amount);
        if(updatedBalance.compareTo(zero)<0){
            throw new BankAccountException("Overdraft is not allowed for SavingsAccount");
        } else {
            if(amount.compareTo(BigDecimal.ZERO)> 0){
                System.out.println("You are making a withdrawal of $ "+amount.toString()+" to "+this.toString());
                this.setBalance(this.getBalance().subtract(amount));
                System.out.println("The transaction was successfully done");
            } else if(amount.compareTo(BigDecimal.ZERO)< 0){
                System.out.println("You should make a deposit");
                throw new BankAccountException("The transaction is not processed");
            } else{
                System.out.println("You are withdrawing no fund");
                System.out.println("but the transaction was successfully done");
            }
        }
    }
    @Override
    protected void withdraw(double amount) throws BankAccountException{
        this.withdraw(BigDecimal.valueOf(amount));
    }
    @Override
    protected void withdraw(long amount) throws BankAccountException{
        this.withdraw(BigDecimal.valueOf(amount));
    }

    public void collectInterest(Date date) throws BankAccountException{
        //compound interest, annual rate compounded daily
    	long diff = date.getTime()-this.getOpenDate().getTime();
    	if(diff<0){
    		throw new BankAccountException("The date you inserted is before the open date.");
    	}
    	int days = (int)(diff/(1000*60*60*24));
    	double compounded = Math.pow(1+this.getInterestRate(), (days/365));
    	this.setBalance(this.getBalance().multiply(BigDecimal.valueOf(compounded)));
    	System.out.println("This interest has been successfully paid");
    }
}