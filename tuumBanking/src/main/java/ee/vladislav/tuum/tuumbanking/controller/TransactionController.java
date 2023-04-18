package ee.vladislav.tuum.tuumbanking.controller;

import ee.vladislav.tuum.tuumbanking.dto.TransactionDTO;
import ee.vladislav.tuum.tuumbanking.exception.CustomNotFoundException;
import ee.vladislav.tuum.tuumbanking.exception.InvalidRequestBodyException;
import ee.vladislav.tuum.tuumbanking.model.Currency;
import ee.vladislav.tuum.tuumbanking.model.Transaction;
import ee.vladislav.tuum.tuumbanking.model.TransactionDirection;
import ee.vladislav.tuum.tuumbanking.requestStructure.CreateTransactionRequest;
import ee.vladislav.tuum.tuumbanking.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts/{accountId}/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final AccountService accountService;

    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@PathVariable Long accountId,
                                               @RequestBody CreateTransactionRequest request)
            throws CustomNotFoundException, InvalidRequestBodyException {

        Transaction draftTransaction;
        try {
            draftTransaction = new Transaction(accountId,
                    request.getAmount(),
                    Currency.valueOf(request.getCurrency()),
                    TransactionDirection.valueOf(request.getDirection()),
                    request.getDescription());

            if (draftTransaction.getAmount() == null || draftTransaction.getDescription() == null) {
                throw new InvalidRequestBodyException("Request body invalid");
            }

        } catch (Exception e) {
            throw new InvalidRequestBodyException("Request body invalid");
        }

        Transaction transaction = accountService.createTransaction(accountId, draftTransaction);

        return ResponseEntity.ok(new TransactionDTO(
                transaction.getAccountId(),
                transaction.getId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getDirection(),
                transaction.getDescription(),
                transaction.getBalanceAfterTransaction()));
    }

    @GetMapping("")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable Long accountId) throws CustomNotFoundException {
        List<Transaction> transactions = accountService.getTransactions(accountId);

        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        transactions.forEach(transaction -> transactionDTOS.add(new TransactionDTO(
                transaction.getAccountId(),
                transaction.getId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getDirection(),
                transaction.getDescription(),
                transaction.getBalanceAfterTransaction())));

        return ResponseEntity.ok(transactionDTOS);
    }
}
