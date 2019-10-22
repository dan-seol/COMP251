import java.math.BigDecimal;

public class ChequingAccount extends Account {
    public final AccountType type = AccountType.CHEQUING;
    private ChequingAccount(String owner, double balance, int number) {
        super(owner, balance, number);
    }
    private ChequingAccount(String owner, long balance, int number) {
        super(owner, balance, number);
    }
    private ChequingAccount(String owner, BigDecimal balance, int number) {
        super(owner, balance, number);
    }
    public static ChequingAccount create(String owner, BigDecimal balance, int number){
        ChequingAccount account = new ChequingAccount(owner, balance, number);
    	System.out.println("A new chequing account under "+ owner +" was created!");
        return account;
    }
    public static ChequingAccount create(String owner, double balance, int number){
        ChequingAccount account = new ChequingAccount(owner, balance, number);
    	System.out.println("A new chequing account under "+ owner +" was created!");
        return account;

    }
    public static ChequingAccount create(String owner, long balance, int number){
        ChequingAccount account = new ChequingAccount(owner, balance, number);
    	System.out.println("A new chequing account under "+ owner +" was created!");
        return account;
    }
}
