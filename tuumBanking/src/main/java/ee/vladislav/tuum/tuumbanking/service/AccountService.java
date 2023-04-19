package ee.vladislav.tuum.tuumbanking.service;

import ee.vladislav.tuum.tuumbanking.exception.CustomNotFoundException;
import ee.vladislav.tuum.tuumbanking.exception.AccountHasNoSuchCurrencyException;
import ee.vladislav.tuum.tuumbanking.exception.InsufficientFundsException;
import ee.vladislav.tuum.tuumbanking.exception.InvalidAmountException;
import ee.vladislav.tuum.tuumbanking.mapper.AccountMapper;
import ee.vladislav.tuum.tuumbanking.mapper.BalanceMapper;
import ee.vladislav.tuum.tuumbanking.mapper.TransactionMapper;
import ee.vladislav.tuum.tuumbanking.model.*;
import ee.vladislav.tuum.tuumbanking.rabbitMQ.RabbitMQSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    private final AccountMapper accountMapper;
    private final BalanceMapper balanceMapper;
    private final TransactionMapper transactionMapper;
    private final RabbitMQSender rabbitMQSender;

    public AccountService(AccountMapper accountMapper, BalanceMapper balanceMapper,
                          TransactionMapper transactionMapper, RabbitMQSender rabbitMQSender) {
        this.accountMapper = accountMapper;
        this.balanceMapper = balanceMapper;
        this.transactionMapper = transactionMapper;
        this.rabbitMQSender = rabbitMQSender;

    }

    private final List<String> allowedCurrencies = Arrays.asList("EUR", "SEK", "GBP", "USD");
    public Account createAccount(Long customerId, String country, List<String> currencies) {

        for (String currency : currencies) {
            if (!allowedCurrencies.contains(currency)) {
                throw new IllegalArgumentException("Invalid currency: " + currency);
            }
        }

        Account account = new Account(customerId, country);
        Integer savedAccountId = accountMapper.createAccount(account);

        for (String currency : currencies) {
            Balance balance = new Balance(Long.valueOf(savedAccountId), BigDecimal.ZERO, Currency.valueOf(currency));
            balanceMapper.createBalance(balance);

            rabbitMQSender.publishMessage("Balance created for account " + account.getId() + " with currency " + currency);
        }

        rabbitMQSender.publishMessage("Account created with ID " + account.getId());

        return accountMapper.findAccountById(Long.valueOf(savedAccountId));
    }

    public Account getAccount(Long id) throws CustomNotFoundException {
        Account account = accountMapper.findAccountById(id);
        if (account == null) {
            throw new CustomNotFoundException("Account not found");
        }

        return account;
    }

    public List<Transaction> getTransactions(Long accountId) throws CustomNotFoundException {
        Account account = accountMapper.findAccountById(accountId);
        if (account == null) {
            throw new CustomNotFoundException("Account not found");
        }

        List<Transaction> transactions = transactionMapper.findTransactionsByAccountId(accountId);

        return transactions;
    }

    public Transaction createTransaction(Long accountId,
            Transaction transaction) throws CustomNotFoundException {

        Account account = accountMapper.findAccountById(accountId);
        if (account == null) {
            throw new CustomNotFoundException("Account not found");
        }

        Map<Currency, BigDecimal> balancesMap = new HashMap<>();
        for (Balance balance : account.getBalances()) {
            balancesMap.put(balance.getCurrency(), balance.getAvailableAmount());
        }

        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Invalid amount: " + transaction.getAmount());
        }

        if (!balancesMap.containsKey(transaction.getCurrency())) {
                throw new AccountHasNoSuchCurrencyException("The account has no needed currency: " + transaction.getCurrency());
        }

        if (transaction.getDirection() == TransactionDirection.IN) {
            BigDecimal money = balancesMap.get(transaction.getCurrency()).add(transaction.getAmount());
            transaction.setBalanceAfterTransaction(money);
        }

        if (transaction.getDirection() == TransactionDirection.OUT) {
            if (balancesMap.get(transaction.getCurrency()).compareTo(transaction.getAmount()) < 0) {
                throw new InsufficientFundsException("Insufficient funds: " + balancesMap.get(transaction.getCurrency()));
            }
            BigDecimal money = balancesMap.get(transaction.getCurrency()).subtract(transaction.getAmount());
            transaction.setBalanceAfterTransaction(money);
        }

        int savedTransactionId = transactionMapper.createTransaction(transaction);
        transaction.setId(Long.valueOf(savedTransactionId));

        List<Balance> balances = account.getBalances()
                                            .stream()
                                            .filter(bal -> Currency.valueOf(bal.getCurrency().getValue())
                                                    == Currency.valueOf(transaction.getCurrency().value))
                                            .toList();

        balances.get(0).setAvailableAmount(transaction.getBalanceAfterTransaction());
        balanceMapper.updateBalanceAmount(balances.get(0));

        rabbitMQSender.publishMessage(transaction.toString());

        return transaction;
    }




}
