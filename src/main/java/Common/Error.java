package Common;

import lombok.Getter;

public enum Error {
    OK(0), IncorrectPhone(-1), PhoneNotFound(-2), AccountNotFound(-3),
    IncorrectSum(-4), InsufficientFond(-5), AccountOperationUnavailable(-6),
    PhoneOperationUnavailable(-7);
    @Getter private int errorCode;

    Error(int errorCode) {
        this.errorCode = errorCode;
    }
}
