package Server;

public enum Currency {
    RUB(810), USD(840), EUR(978);

    private int code;

    Currency(int code) {
        this.code = code;
    }
}
