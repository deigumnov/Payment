import Client.Client;
import Common.Error;
import Server.Server;
import Server.Account;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Инициализация клиентов в приложении..");
        Client client = new Client(1);
        TimeUnit.SECONDS.sleep(1);

        System.out.println("Инициализирован клиент с номером договора " + client.getContractID());
        TimeUnit.SECONDS.sleep(1);

        System.out.println("Выполняется вход клиента в приложение..");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("Выполняется инициализация сервера + клиентов со счетами + номеров телефонов с балансами на стороне сервера..");
        Server server = new Server(50_001, "TCP", "127.0.0.1");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("Выполняется запрос счетов с балансом с сервера для клиента с номером договора " + client.getContractID());
        ArrayList<Account> accounts = server.getClientAccounts(client);
        TimeUnit.SECONDS.sleep(1);

        System.out.println("У клиента найдено " + accounts.size() + " счетов");
        for (Account account : accounts) {
            System.out.println("Счет: " + account.getAccountNumber() + ", Валюта: " + account.getCurrency() + ", Баланс: " + account.getAccountBalance());
        }
        TimeUnit.SECONDS.sleep(1);

        String phone = "1234567890";
        System.out.println("Текущий баланс по номеру телефона : " + server.getPhoneBalance(phone));
        TimeUnit.SECONDS.sleep(1);

        String accountNumber = "00001";
        double sum = 50.0;
        System.out.println("Клиент выбрал операцию пополнение баланса по номеру телефона " + phone + " со счета " + accountNumber + " на сумму " + sum + " в валюте счета списания");
        System.out.println("Курс конвертации валюты 1:1");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("Выполнение операции..");
        Error payResult = server.payForPhone(client,accountNumber,sum,phone);
        System.out.println(payResult);

        System.out.println("У клиента найдено " + accounts.size() + " счетов");
        for (Account account : accounts) {
            System.out.println("Счет: " + account.getAccountNumber() + ", Валюта: " + account.getCurrency() + ", Баланс: " + account.getAccountBalance());
        }
        System.out.println("Текущий баланс по номеру телефона : " + server.getPhoneBalance(phone));
        TimeUnit.SECONDS.sleep(1);
    }
}
