package Server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Currency {
    RUB(810), USD(840), EUR(978);
    @Getter private int code;
}
