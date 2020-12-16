package Server;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Server implements java.io.Serializable {
    private int serverPort;
    private String protocol;
    private String ipAddress;
    private final BusinessOperations database = new BusinessOperations();

}