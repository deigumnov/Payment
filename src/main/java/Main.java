import Client.Client;
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
        int payResult = server.payForPhone(client,accountNumber,sum,phone);
        switch (payResult) {
            case 0 :
                System.out.println("Операция прошла успешно");
                break;
            case -1 :
                System.out.println("Не пройдена проверка на корректность ввода номера телефона");
                break;
            case -2 :
                System.out.println("Номер телефона не найден");
                break;
            case -3 :
                System.out.println("Счет списания не найден у клиента");
                break;
            case -4 :
                System.out.println("Введена некорректная сумма");
                break;
            case -5 :
                System.out.println("Недостаточно средств для выполнения операции");
                break;
            case -6 :
                System.out.println("Операция списания со счета не прошла");
                break;
            case -7 :
                System.out.println("Операция пополнения баланса телефона не прошла");
                break;
            default :
                System.out.println("Неизвестная ошибка");
        }

        System.out.println("У клиента найдено " + accounts.size() + " счетов");
        for (Account account : accounts) {
            System.out.println("Счет: " + account.getAccountNumber() + ", Валюта: " + account.getCurrency() + ", Баланс: " + account.getAccountBalance());
        }
        System.out.println("Текущий баланс по номеру телефона : " + server.getPhoneBalance(phone));
        TimeUnit.SECONDS.sleep(1);
    }
}
