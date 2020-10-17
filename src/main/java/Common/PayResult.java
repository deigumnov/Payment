package Common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum PayResult {
    OK(0),
    IncorrectPhone(-1),
    PhoneNotFound(-2),
    AccountNotFound(-3),
    IncorrectSum(-4),
    InsufficientFond(-5),
    AccountOperationUnavailable(-6),
    PhoneOperationUnavailable(-7);

    private int errorCode;
}
