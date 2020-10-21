package Server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class Server {
    private final int serverPort;
    private final String protocol;
    private final String ipAddress;
    private final BusinessOperations database = new BusinessOperations();

}