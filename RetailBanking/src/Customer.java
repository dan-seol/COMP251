import java.math.BigDecimal;
import java.util.*;
abstract class Customer {
    private UUID uuid;
    private String password;
    private String id;
    protected Map<Integer, Account> accountDict;

    public String getID() {
        return this.id;
    }
    public UUID getUUID() {
        return this.uuid;
    }
    public String getPassword(){
        return this.password;
    }
    
    protected void setPassword(String pw) {
    	this.password =  pw;
    }
    protected Customer(String pw) {
    	this.setPassword(pw);
        this.uuid = UUID.randomUUID();
        this.id = uuid.toString();
        int chequeNum = (int)(Math.random()*9000000)+1000000;
        int saveNum = (int)(Math.random()*9000000)+1000000;
        Account initialChequing = ChequingAccount.create(this.getID(), 0, chequeNum);
        Account initialSavings = SavingsAccount.create(this.getID(), 0, saveNum);
    }
    protected Customer(String pw, BigDecimal chequeBalance, BigDecimal savingsBalance) throws BankAccountException {
        if(savingsBalance.compareTo(BigDecimal.ZERO)<0){
            throw new BankAccountException("The balance for the savings account is negative");
        }
    	this.setPassword(pw);
        this.uuid = UUID.randomUUID();
        this.id = uuid.toString();
        int chequeNum = (int)(Math.random()*9000000)+1000000;
        int saveNum = (int)(Math.random()*9000000)+1000000;
        Account initialChequing = ChequingAccount.create(this.getID(), chequeBalance, chequeNum);
        Account initialSavings = SavingsAccount.create(this.getID(), savingsBalance, saveNum);
        this.accountDict.put(chequeNum, initialChequing);
        this.accountDict.put(saveNum, initialSavings);
    }
    protected Customer(String pw, double chequeBalance, double savingsBalance) throws BankAccountException{
        if(savingsBalance<0){
            throw new BankAccountException("The balance for the savings account is negative");
        }
    	this.setPassword(pw);
        this.uuid = UUID.randomUUID();
        this.id = uuid.toString();
        Set<Integer> accountNums = this.accountDict.keySet();
        int chequeNum = (int)(Math.random()*9000000)+1000000;
        int saveNum = (int)(Math.random()*9000000)+1000000;
        //Make sure there are no duplicate accountNumber
        while(accountNums.contains(chequeNum)||accountNums.contains(saveNum)||chequeNum==saveNum){
            chequeNum = (int)(Math.random()*9000000)+1000000;;
            saveNum = (int)(Math.random()*9000000)+1000000;;
        }
        Account initialChequing = ChequingAccount.create(this.getID(), chequeBalance, chequeNum);
        Account initialSavings = SavingsAccount.create(this.getID(), savingsBalance, saveNum);
        this.accountDict.put(chequeNum, initialChequing);
        this.accountDict.put(saveNum, initialSavings);
    }
    protected Customer(String pw, long chequeBalance, long savingsBalance) throws BankAccountException{
        if(savingsBalance<0){
            throw new BankAccountException("The balance for the savings account is negative");
        }
        this.uuid = UUID.randomUUID();
        this.id = uuid.toString();
    	this.setPassword(pw);
        int chequeNum = (int)(Math.random()*9000000)+1000000;
        int saveNum = (int)(Math.random()*9000000)+1000000;
        Account initialChequing = ChequingAccount.create(this.getID(), chequeBalance, chequeNum);
        Account initialSavings = SavingsAccount.create(this.getID(), savingsBalance, saveNum);
        this.accountDict.put(chequeNum, initialChequing);
        this.accountDict.put(saveNum, initialSavings);
    }

    //Instance factory methods
   /*
    public static Customer create(String pw, BigDecimal chequeBalance, BigDecimal savingsBalance) throws BankAccountException{
        return new Customer(pw, chequeBalance, savingsBalance);
    }
    public static Customer create(String pw, BigDecimal chequeBalance, BigDecimal savingsBalance) {
        return new Customer(pw,chequeBalance, savingsBalance);
    }
    *.
    */
    public void openNewChequeAccount(String pw, BigDecimal balance) throws BankAccountException{
        if(!pw.equals(this.getPassword())){
            throw new BankAccountException("The password does not match");
        }
        Set<Integer> accountNums = this.accountDict.keySet();
            int chequeNum = (int)(Math.random()*9000000)+1000000;
            while(accountNums.contains(chequeNum)){
               chequeNum = (int)(Math.random()*9000000)+1000000;
            }
            ChequingAccount newAccount = ChequingAccount.create(id, balance, chequeNum);
            this.accountDict.put(chequeNum, newAccount);
            System.out.println("The new chequing account " + chequeNum + " has been created");
    }
    public void openNewSavingsAccount(String pw, BigDecimal balance) throws BankAccountException{
        if(!pw.equals(this.getPassword())){
            throw new BankAccountException("The password does not match");
        }
        Set<Integer> accountNums = this.accountDict.keySet();
        int saveNum = (int)(Math.random()*9000000)+1000000;
        while(accountNums.contains(saveNum)){
            saveNum = (int)(Math.random()*9000000)+1000000;
        }
        SavingsAccount newAccount = SavingsAccount.create(id, balance, saveNum);
        this.accountDict.put(saveNum, newAccount);
        System.out.println("The new savings account " + saveNum + " has been created");
    }
    public void openNewChequeAccount(String pw, double balance) throws BankAccountException{
        if(!pw.equals(this.getPassword())){
            throw new BankAccountException("The password does not match");
        }
        Set<Integer> accountNums = this.accountDict.keySet();
        int chequeNum = (int)(Math.random()*9000000)+1000000;
        while(accountNums.contains(chequeNum)){
            chequeNum = (int)(Math.random()*9000000)+1000000;
        }
        ChequingAccount newAccount = ChequingAccount.create(id, balance, chequeNum);
        this.accountDict.put(chequeNum, newAccount);
        System.out.println("The new chequing account " + chequeNum + " has been created");
    }
    public void openNewSavingsAccount(String pw, double balance) throws BankAccountException{
        if(!pw.equals(this.getPassword())){
            throw new BankAccountException("The password does not match");
        }
        Set<Integer> accountNums = this.accountDict.keySet();
        int saveNum = (int)(Math.random()*9000000)+1000000;
        while(accountNums.contains(saveNum)){
            saveNum = (int)(Math.random()*9000000)+1000000;
        }
        SavingsAccount newAccount = SavingsAccount.create(id, balance, saveNum);
        this.accountDict.put(saveNum, newAccount);
        System.out.println("The new savings account " + saveNum + " has been created");
    }
    public void openNewChequeAccount(String pw, long balance) throws BankAccountException{
        if(!pw.equals(this.getPassword())){
            throw new BankAccountException("The password does not match");
        }
        Set<Integer> accountNums = this.accountDict.keySet();
        int chequeNum = (int)(Math.random()*9000000)+1000000;
        while(accountNums.contains(chequeNum)){
            chequeNum = (int)(Math.random()*9000000)+1000000;
        }
        ChequingAccount newAccount = ChequingAccount.create(id, balance, chequeNum);
        this.accountDict.put(chequeNum, newAccount);
        System.out.println("The new chequing account " + chequeNum + " has been created");
    }
    public void openNewSavingsAccount(String pw, long balance) throws BankAccountException{
        if(!pw.equals(this.getPassword())){
            throw new BankAccountException("The password does not match");
        }
        Set<Integer> accountNums = this.accountDict.keySet();
        int saveNum = (int)(Math.random()*9000000)+1000000;
        while(accountNums.contains(saveNum)){
            saveNum = (int)(Math.random()*9000000)+1000000;
        }
        SavingsAccount newAccount = SavingsAccount.create(id, balance, saveNum);
        this.accountDict.put(saveNum, newAccount);
        System.out.println("The new savings account " + saveNum + " has been created");
    }

    public Account closeAccount(String pw, Integer accountNum) throws BankAccountException{
        if(!pw.equals(this.getPassword())){
            throw new BankAccountException("The password does not match");
        }
        Set<Integer> accountNums = this.accountDict.keySet();
        if(accountNums.contains(accountNum)){
            Account acc = accountDict.get(accountNum);
            accountDict.remove(accountNum);
            return acc;
        }else{
            throw new BankAccountException("The account you want to delete does not exist");
        }
    }
}
