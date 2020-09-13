package Client;

public class Client {
    private long contractID;

    public Client (long contractID) {
        this.contractID = contractID;
    }

    public long getContractID () { return this.contractID; }
}
