package Server;

import Client.*;
import Common.*;
import java.util.*;

public class BusinessOperations {
    private final HashSet<Account> accounts;
    private final HashSet<Phone> phones;

    public BusinessOperations() {
        // создание базы счетов
        accounts = new HashSet<>();
        accounts.add(new Account("00001", 100.00, 1, Currency.RUB));
        accounts.add(new Account("00002", 200.00, 1, Currency.USD));
        accounts.add(new Account("00003", 300.00, 1, Currency.EUR));
        // создание базы телефонов
        phones = new HashSet<>();
        phones.add(new Phone("81234567890",0.0,Currency.RUB));
    }

    public Optional<Double> getClientAccountBalance(String accountNumber, Client client) {
        double balance = 0.0;
        boolean isFound = false;
        for (Account account : accounts) {
            if ((account.getClientContractID() == client.getContractID()) && (account.getAccountNumber().equals(accountNumber))) {
                balance = account.getAccountBalance();
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            return Optional.empty();
        } else {
            return Optional.of(balance);
        }
    }

//    public Optional getClientAccountCurrency(String accountNumber, Client client) {
//        Currency currency = Currency.RUB;
//        boolean isFound = false;
//        for (Account account : accounts) {
//            if ((account.getClientContractID() == client.getContractID()) && (account.getAccountNumber().equals(accountNumber))) {
//                currency = account.getCurrency();
//                isFound = true;
//                break;
//            }
//        }
//        if (!isFound) {
//            return Optional.ofNullable(null);
//        } else {
//            Optional.ofNullable(currency);
//        }
//    }

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

    public Optional<Double> getPhoneBalance (String phoneNumber) {
        double balance = 0.0;
        boolean isFound = false;
        for (Phone phone : phones) {
            if (phone.getPhoneNumber().equals(phoneNumber)) {
                balance = phone.getPhoneBalance();
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            return Optional.empty();
        } else {
            return Optional.of(balance);
        }
//        if (phones.stream().filter(phones -> phones.equals(phoneNumber)).count() > 1) {
//            return Optional.empty();
//        }
//        return Optional.ofNullable(phones).stream().findFirst(phones -> phones.equals(phoneNumber));
    }

    public HashSet<Account> getClientAccounts (Client client) {
        HashSet<Account> result = new HashSet<>();
        for (Account account : accounts) {
            if (account.getClientContractID() == client.getContractID()) {
                result.add(account);
            }
        }
        return result;
    }

    public PayResult payForPhone (Client client, String accountNumber, double sum, String phoneNumber) {
        if (!Check.checkPhone(phoneNumber)) {
            return PayResult.IncorrectPhone;
        }
        if (!isPhoneExist(phoneNumber)) {
            return PayResult.PhoneNotFound;
        }
        if (!isClientAccount(accountNumber,client)) {
            return PayResult.AccountNotFound;
        }
        if (!Check.checkSum(sum)) {
            return PayResult.IncorrectSum;
        }

        Optional <Double> accountBalanceStart = getClientAccountBalance(accountNumber,client);
        if (accountBalanceStart.isEmpty()) {
            return PayResult.AccountNotFound;
        }
        if (accountBalanceStart.get() < sum) {
            return PayResult.InsufficientFond;
        }

        for (Account account : accounts) {
            if ((account.getClientContractID() == client.getContractID()) && (account.getAccountNumber().equals(accountNumber))) {
                account.changeBalance(-sum);
                break;
            }
        }

        Optional <Double> accountBalanceEnd = getClientAccountBalance(accountNumber,client);
        if (accountBalanceEnd.isEmpty()) {
            return PayResult.AccountNotFound;
        }

        if (accountBalanceStart.get() - accountBalanceEnd.get() != sum) {
            return PayResult.AccountOperationUnavailable;
        }

        // пока что курсы для конвертации валют 1:1
        Optional <Double> phoneBalanceStart = getPhoneBalance(phoneNumber);
        if (phoneBalanceStart.isEmpty()) {
            return PayResult.PhoneNotFound;
        }

        for (Phone phone : phones) {
            if (phone.getPhoneNumber().equals(phoneNumber)) {
                phone.addBalance(sum);
            }
        }

        Optional <Double> phoneBalanceEnd = getPhoneBalance(phoneNumber);
        if (phoneBalanceEnd.isEmpty()) {
            return PayResult.PhoneNotFound;
        }

        if (phoneBalanceEnd.get() - phoneBalanceStart.get() != sum) {
            return PayResult.PhoneOperationUnavailable;
        }

        return PayResult.OK;
    }
}
