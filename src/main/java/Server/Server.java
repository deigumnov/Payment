package Server;

import lombok.*;

@AllArgsConstructor
@Getter

public class Server {
    private final int serverPort;
    private final String protocol;
    private final String ipAddress;
    private final BusinessOperations database = new BusinessOperations();

}