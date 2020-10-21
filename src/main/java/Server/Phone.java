package Server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class Phone {
    private final String phoneNumber;
    private double phoneBalance;
    private final Currency currency;

    public void addBalance(double sum) {
        phoneBalance += sum;
    }
}
