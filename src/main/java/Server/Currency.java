package Server;

import lombok.Getter;

public enum Currency {
    RUB(810), USD(840), EUR(978);
    @Getter private int code;

    Currency(int code) {
        this.code = code;
    }
}
