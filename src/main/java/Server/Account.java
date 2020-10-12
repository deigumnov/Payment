package Server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Account {
    private String accountNumber;
    private double accountBalance;
    private long clientContractID;
    private Currency currency;

    public void changeBalance (double sum) {
        accountBalance += sum;
    }
}
