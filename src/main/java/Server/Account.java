package Server;

public class Account {
    private String accountNumber;
    private double accountBalance;
    private long clientContractID;
    private Currency currency;

    public Account(String accountNumber, double accountBalance, long clientContractID, Currency currency) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.clientContractID = clientContractID;
        this.currency = currency;
    }

    public void changeBalance (double sum) {
        accountBalance += sum;
    }

    public String getAccountNumber () { return accountNumber; }
    public double getAccountBalance () { return accountBalance; }
    public long getClientContractID () { return clientContractID; }
    public Currency getCurrency () { return currency; }

}
