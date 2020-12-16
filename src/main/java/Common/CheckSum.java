package Common;

import lombok.*;
import java.util.function.*;

@NoArgsConstructor

public class CheckSum implements Predicate<Double> {
    // проверка на наличие не отрицательной суммы со значением до двух знаков
    @Override
    public boolean test(Double sum) {
        boolean result = true;
        if (sum <= 0.0 || Math.round(sum*100)/100.0 != sum) {
            result = false;
        }
        return result;
    }
}