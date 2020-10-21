package Server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class Account {
    private final String accountNumber;
    private double accountBalance;
    private final long clientContractID;
    private final Currency currency;

    public void changeBalance (double sum) {
        accountBalance += sum;
    }
}
