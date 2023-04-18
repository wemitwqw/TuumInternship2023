package ee.vladislav.tuum.tuumbanking.controller;

import ee.vladislav.tuum.tuumbanking.dto.AccountDTO;
import ee.vladislav.tuum.tuumbanking.exception.CustomNotFoundException;
import ee.vladislav.tuum.tuumbanking.model.Account;
import ee.vladislav.tuum.tuumbanking.requestStructure.CreateAccountRequest;
import ee.vladislav.tuum.tuumbanking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountRequest request) {
        Account account = accountService.createAccount(request.getCustomerId(), request.getCountry(),
                request.getCurrencies());

        AccountDTO accountDto = new AccountDTO(account.getId(), account.getCustomerId(), account.getCountry(), account.getBalances());

        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccount(@PathVariable Long accountId) throws CustomNotFoundException {
        Account account = accountService.getAccount(accountId);

        AccountDTO accountDto = new AccountDTO(account.getId(), account.getCustomerId(), account.getCountry(), account.getBalances());

        return ResponseEntity.ok(accountDto);
    }
}
