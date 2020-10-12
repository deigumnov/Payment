package Server;

import lombok.Getter;

@Getter
public class Phone {
    private String phoneNumber;
    private double phoneBalance;
    private Currency currency;

    public Phone (String phoneNumber, double phoneBalance) {
        this.phoneNumber = phoneNumber;
        this.phoneBalance = phoneBalance;
        this.currency = Currency.RUB;
    }

    public void addBalance(double sum) {
        phoneBalance += sum;
    }
}
