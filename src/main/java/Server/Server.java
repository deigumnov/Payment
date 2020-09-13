package Server;

import Client.Client;
import Common.Check;

import java.util.ArrayList;

public class Server {
    private int serverPort;
    private String protocol;
    private String ipAddress;
    private ArrayList<Account> accounts;
    private ArrayList<Phone> phones;

    public Server (int port, String protocol, String ip) {
        this.serverPort = port;
        this.protocol = protocol;
        this.ipAddress = ip;

        initializeAccounts();
        initializePhones();
    }

    private void initializeAccounts () {
        accounts = new ArrayList<>();
        accounts.add(new Account("00001", 100.00, 1, Currency.RUB));
        accounts.add(new Account("00002", 200.00, 1, Currency.USD));
        accounts.add(new Account("00003", 300.00, 1, Currency.EUR));
    }

    private void initializePhones () {
        phones= new ArrayList<>();
        phones.add(new Phone("1234567890",0.0));
    }

    public double getClientAccountBalance(String accountNumber, Client client) {
        double balance = 0.0;
        for (Account account : accounts) {
            if ((account.getClientContractID() == client.getContractID()) && (account.getAccountNumber().equals(accountNumber))) {
                balance = account.getAccountBalance();
                break;
            }
        }
        return balance;
    }

    public Currency getClientAccountCurrency(String accountNumber, Client client) {
        Currency currency = Currency.RUB;
        for (Account account : accounts) {
            if ((account.getClientContractID() == client.getContractID()) && (account.getAccountNumber().equals(accountNumber))) {
                currency = account.getCurrency();
                break;
            }
        }
        return currency;
    }

    public boolean isClientAccount(String accountNumber, Client client) {
        boolean result = false;
        for (Account account : accounts) {
            if ((account.getClientContractID() == client.getContractID()) && (account.getAccountNumber().equals(accountNumber))) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isPhoneExist(String phoneNumber) {
        boolean result = false;
        for (Phone phone : phones) {
            if (phone.getPhoneNumber().equals(phoneNumber)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public double getPhoneBalance (String phoneNumber) {
        double balance = 0.0;
        for (Phone phone : phones) {
            if (phone.getPhoneNumber().equals(phoneNumber)) {
                balance = phone.getPhoneBalance();
                break;
            }
        }
        return balance;
    }

    public ArrayList<Account> getClientAccounts (Client client) {
        ArrayList<Account> result = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getClientContractID() == client.getContractID()) {
                result.add(account);
            }
        }
        return result;
    }

    public int payForPhone (Client client, String accountNumber, double sum, String phoneNumber) {
        // если функция возвращает нулевой результат, значит все прошло успешно
        int result = 0;
        if (Check.checkPhone(phoneNumber) == null) {
            // не пройдена проверка на корректность ввода номера телефона
            result = -1;
            return result;
        } else {
            phoneNumber = Check.checkPhone(phoneNumber);
        }
        if (!isPhoneExist(phoneNumber)) {
        // номер телефона не найден
            result = -2;
            return result;
        }
        if (!isClientAccount(accountNumber,client)) {
        // не найден счет у клиента
            result = -3;
            return result;
        }
        sum = Check.checkSum(sum);
        if (sum == 0) {
        // введена некорректная сумма
            result = -4;
            return result;
        }
        if (getClientAccountBalance(accountNumber,client) < sum) {
        // недостаточно средств для выполнения операции
            result = -5;
            return result;
        }

        double accountBalanceStart = getClientAccountBalance(accountNumber,client);
        for (Account account : accounts) {
            if ((account.getClientContractID() == client.getContractID()) && (account.getAccountNumber().equals(accountNumber))) {
                account.changeBalance(-sum);
                break;
            }
        }
        if (accountBalanceStart - getClientAccountBalance(accountNumber,client) != sum) {
            // операция списания со счета не прошла
            result = -6;
            return result;
        }

        // пока что курсы для конвертации валют 1:1
        double phoneBalanceStart = getPhoneBalance(phoneNumber);
        for (Phone phone : phones) {
            if (phone.getPhoneNumber().equals(phoneNumber)) {
                phone.addBalance(sum);
            }
        }
        if (getPhoneBalance(phoneNumber) - phoneBalanceStart != sum) {
        // операция пополнения баланса телефона не прошла
            result = -7;
            return result;
        }

        return result;
    }

    public int getServerPort() { return serverPort; }
    public String getIpAddress() { return ipAddress; }
    public String getPROTOCOL() { return protocol; }
}
