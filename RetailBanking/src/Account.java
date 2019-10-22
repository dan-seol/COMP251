import java.util.*;
import java.math.*;
abstract class Account {
    private String owner;
    private BigDecimal balance;
    private int accountNumber;
    private Date openDate;

    public Date getOpenDate() {
        return openDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                " owner= '" + owner + '\'' +
                ", accountNumber= " + accountNumber + ", balance = " + balance +
                '}';
    }



    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public BigDecimal getBalance() {
        return this.balance;
    }
    public String getOwner() {
        return this.owner;
    }
    public int getAccountNumber(){return this.accountNumber;}
    protected Account(String owner, double balance, int number){
        this.owner=owner;
        this.balance=BigDecimal.valueOf(balance);
        this.accountNumber = number;
    }
    protected Account(String owner, long balance, int number){
        this.owner=owner;
        this.balance=BigDecimal.valueOf(balance);
        this.accountNumber = number;
    }
    protected Account(String owner, BigDecimal balance, int number) {
        this.owner = owner;
        this.balance = balance;
        this.accountNumber = number;
    }
    protected void deposit(BigDecimal amount) throws BankAccountException{
        if(amount.compareTo(BigDecimal.ZERO)> 0){
            System.out.println("You are making a deposit of $ "+amount.toString()+" to "+this.toString());
            this.balance=this.balance.add(amount);
            System.out.println("The transaction was successfully done");
        } else if(amount.compareTo(BigDecimal.ZERO)< 0){
            System.out.println("You should make a withdrawal");
            throw new BankAccountException("The transaction is not processed");
        } else{
            System.out.println("You are adding no fund");
            System.out.println("but the transaction was successfully done");
        }
    }
    protected void deposit(double amount) throws BankAccountException{
        this.deposit(BigDecimal.valueOf(amount));
    }
    protected void deposit(long amount) throws BankAccountException{
        this.deposit(BigDecimal.valueOf(amount));
    }
    protected void withdraw(BigDecimal amount) throws BankAccountException{
        if(amount.compareTo(BigDecimal.ZERO)> 0){
            System.out.println("You are making a withdrawal of $ "+amount.toString()+" to "+this.toString());
            this.balance=this.balance.subtract(amount);
            System.out.println("The transaction was successfully done");
        } else if(amount.compareTo(BigDecimal.ZERO)< 0){
            System.out.println("You should make a deposit");
            throw new BankAccountException("The transaction is not processed");
        } else{
            System.out.println("You are withdrawing no fund");
            System.out.println("but the transaction was successfully done");
        }
    }
    protected void withdraw(double amount) throws BankAccountException{
        this.withdraw(BigDecimal.valueOf(amount));
    }
    protected void withdraw(long amount) throws BankAccountException{
        this.withdraw(BigDecimal.valueOf(amount));
    }

}
