package Server;

import lombok.*;

@AllArgsConstructor
@Getter

public enum Currency {
    RUB(643),
    RUR(810),
    USD(840),
    EUR(978);

    private final int code;
}
