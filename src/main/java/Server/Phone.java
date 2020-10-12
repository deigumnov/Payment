package Server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Phone {
    private String phoneNumber;
    private double phoneBalance;
    private Currency currency;

    public void addBalance(double sum) {
        phoneBalance += sum;
    }
}
